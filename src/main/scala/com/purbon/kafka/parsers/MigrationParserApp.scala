package com.purbon.kafka.parsers

import com.purbon.kafka.SchemaRegistryClient
import com.purbon.kafka.clients.MigrationAdminClient
import com.purbon.kafka.migrations.grammar.{KafkaMigrationsListener, KafkaMigrationsParser}
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.{ErrorNode, TerminalNode}
import org.apache.logging.log4j.LogManager

import scala.collection.mutable

abstract class MigrationsParserAppBase extends KafkaMigrationsListener {

  // Generic grammar functions
  override def visitTerminal(node: TerminalNode): Unit = ???
  override def visitErrorNode(node: ErrorNode): Unit = println(s"ERROR: $node")
  override def enterEveryRule(ctx: ParserRuleContext): Unit = ???
  override def exitEveryRule(ctx: ParserRuleContext): Unit = ???

  override def exitMigration(ctx: KafkaMigrationsParser.MigrationContext): Unit = ???
  override def exitCode_block(ctx: KafkaMigrationsParser.Code_blockContext): Unit = ???
  override def exitMethod(ctx: KafkaMigrationsParser.MethodContext): Unit = ???
  override def enterParams_with_comma(ctx: KafkaMigrationsParser.Params_with_commaContext): Unit = ???
  override def exitParams_with_comma(ctx: KafkaMigrationsParser.Params_with_commaContext): Unit = ???
  override def enterLast_param(ctx: KafkaMigrationsParser.Last_paramContext): Unit = ???
  override def exitLast_param(ctx: KafkaMigrationsParser.Last_paramContext): Unit = ???
  override def enterParam(ctx: KafkaMigrationsParser.ParamContext): Unit = ???
  override def exitParam(ctx: KafkaMigrationsParser.ParamContext): Unit = ???
  override def exitVariable(ctx: KafkaMigrationsParser.VariableContext): Unit = ???
}

class MigrationParserApp(schemaClient: SchemaRegistryClient, migrationClient: MigrationAdminClient) extends MigrationsParserAppBase {

  import scala.jdk.CollectionConverters._
  import scala.reflect.runtime.{universe => ru}

  private val LOGGER = LogManager.getLogger(classOf[MigrationParserApp])

  val stack        = new mutable.HashMap[String, String]
  val variables    = new mutable.HashMap[String, String]()
  val up_methods   = new mutable.HashMap[String, Map[String, List[String]]]()
  val down_methods = new mutable.HashMap[String, Map[String, List[String]]]()
  var up_method    = false;
  var down_method  = false;

  def asChangeRequest: ChangeRequest = {
    new AntlrBasedChangeRequest(stack, variables, up_methods, down_methods)
  }

  private class AntlrBasedChangeRequest(stack: mutable.HashMap[String, String],
                                   vars: mutable.HashMap[String, String],
                                   up_methods: mutable.HashMap[String, Map[String, List[String]]],
                                   down_methods: mutable.HashMap[String, Map[String, List[String]]]
                                       ) extends ChangeRequest {
    override def up(): Unit = {
      LOGGER.info("up method")
      applyFunction(up_methods)
    }

    override def down(): Unit = {
      LOGGER.info("down method")
      applyFunction(down_methods)
    }

    private def applyFunction( methods : mutable.HashMap[String, Map[String, List[String]]]): Unit = {
      methods
        .keys
        .foreach { methodName =>
          val methodMirror: ru.MethodMirror = {
            try {
              reflectMethod(methodName)
            } catch {
              case e: Exception => {
                reflectMethod(methodName)
              }
            }
          }
          val params: List[String] = up_methods.get(methodName).get("params")
          //TODO: Add variable interpolation later (Pending)
          methodMirror.apply(params)
        }
    }

    private def reflectMethod(methodName: String): ru.MethodMirror = {
      val (im, methodSymbol) = findInstanceMirrorAndMethodSymbol(methodName)
      im.reflectMethod(methodSymbol)
    }

    private def findInstanceMirrorAndMethodSymbol(methodName: String): (ru.InstanceMirror, ru.MethodSymbol) = {
      try {
        val (m, methodX) = findSchemaRegistryMethod(methodName)
        (m.reflect(schemaClient), methodX)
      } catch {
        case e: Exception => {
          val (m, methodX) = findAdminClientMethod(methodName)
          (m.reflect(migrationClient), methodX)
        }
      }
    }

    private def findSchemaRegistryMethod(methodName: String): (ru.Mirror, ru.MethodSymbol) = {
      val m = ru.runtimeMirror(schemaClient.getClass.getClassLoader)
      val methodX = ru.typeOf[SchemaRegistryClient].decl(ru.TermName(methodName)).asMethod
      (m, methodX)
    }

    private def findAdminClientMethod(methodName: String): (ru.Mirror, ru.MethodSymbol) = {
      val m = ru.runtimeMirror(migrationClient.getClass.getClassLoader)
      val methodX = ru.typeOf[MigrationAdminClient].decl(ru.TermName(methodName)).asMethod
      (m, methodX)
    }
  }

  /**
   * Enter a parse tree produced by {@link KafkaMigrationsParser#migration}.
   *
   * @param ctx the parse tree
   */
  override def enterMigration(ctx: KafkaMigrationsParser.MigrationContext): Unit = {
    val exprText = ctx.getText
    LOGGER.debug(s"Expression after tokenization = $exprText")

    val operationLiteral = ctx.OP_LITERAL().getText
    LOGGER.debug(s"operationLiteral = $operationLiteral")
    stack.put("operationLiteral", operationLiteral);
  }

  override def enterCode_block(ctx: KafkaMigrationsParser.Code_blockContext): Unit = ???
  override def enterMethod(ctx: KafkaMigrationsParser.MethodContext): Unit = {
    val exprText = ctx.getText
    LOGGER.debug(s"Expression after tokenization = $exprText")

    val methodName: String = ctx.ID().getText
    val lastParam = ctx.last_param().getText
    val params = ctx.params_with_comma()
      .param()
      .asScala
      .map(_.getText)
      .append(lastParam).toList

    if (up_method) {
      up_methods.put(methodName, Map("params" -> params))
    } else if (down_method) {
      down_methods.put(methodName, Map("params" -> params))
    }
  }

  override def enterApply_function(ctx: KafkaMigrationsParser.Apply_functionContext): Unit = {
    up_method = true
    down_method = false
  }
  override def exitApply_function(ctx: KafkaMigrationsParser.Apply_functionContext): Unit = {
    up_method = false
    down_method = false
  }

  override def enterRevert_function(ctx: KafkaMigrationsParser.Revert_functionContext): Unit = {
    up_method = false
    down_method = true
  }
  override def exitRevert_function(ctx: KafkaMigrationsParser.Revert_functionContext): Unit = {
    up_method = false
    down_method = false
  }

  override def enterVariable(ctx: KafkaMigrationsParser.VariableContext): Unit = {
    val exprText = ctx.getText
    LOGGER.debug(s"Expression after tokenization = $exprText")
    val key = ctx.ID(0).getText
    val value = ctx.ID(1).getText
    variables.put(key, value)
  }
}
