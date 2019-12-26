package com.purbon.kafka.parsers

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

}
class MigrationParserApp extends MigrationsParserAppBase {

  private val LOGGER = LogManager.getLogger(classOf[MigrationParserApp])

  val stack = new mutable.HashMap[String, String]
  val variables = new mutable.HashMap[String, String]()

  def asChangeRequest: ChangeRequest = {
    new DummyChangeRequest
  }

  private class DummyChangeRequest extends ChangeRequest {
    override def up(): Unit = println("up")
    override def down(): Unit = println("down")
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
  }

  override def enterApply_function(ctx: KafkaMigrationsParser.Apply_functionContext): Unit = ???
  override def exitApply_function(ctx: KafkaMigrationsParser.Apply_functionContext): Unit = ???

  override def enterRevert_function(ctx: KafkaMigrationsParser.Revert_functionContext): Unit = ???
  override def exitRevert_function(ctx: KafkaMigrationsParser.Revert_functionContext): Unit = ???

  override def enterParams_with_comma(ctx: KafkaMigrationsParser.Params_with_commaContext): Unit = ???
  override def exitParams_with_comma(ctx: KafkaMigrationsParser.Params_with_commaContext): Unit = ???
  override def enterLast_param(ctx: KafkaMigrationsParser.Last_paramContext): Unit = ???
  override def exitLast_param(ctx: KafkaMigrationsParser.Last_paramContext): Unit = ???
  override def enterParam(ctx: KafkaMigrationsParser.ParamContext): Unit = ???
  override def exitParam(ctx: KafkaMigrationsParser.ParamContext): Unit = ???

  override def enterVariable(ctx: KafkaMigrationsParser.VariableContext): Unit = {
    val exprText = ctx.getText
    LOGGER.debug(s"Expression after tokenization = $exprText")
    val key = ctx.ID(0).getText
    val value = ctx.ID(1).getText
    variables.put(key, value)
  }
  override def exitVariable(ctx: KafkaMigrationsParser.VariableContext): Unit = ???
}
