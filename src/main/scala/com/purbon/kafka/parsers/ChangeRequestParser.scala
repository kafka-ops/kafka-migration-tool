package com.purbon.kafka.parsers

import com.purbon.kafka.SchemaRegistryClient
import com.purbon.kafka.clients.MigrationAdminClient
import com.purbon.kafka.migrations.grammar.{KafkaMigrationsLexer, KafkaMigrationsParser}
import org.antlr.v4.runtime.{CharStreams, CodePointCharStream, CommonTokenStream}
import org.apache.logging.log4j.LogManager

import scala.reflect.runtime.universe
import scala.tools.reflect.ToolBox
import scala.util.matching.Regex

trait ChangeRequestParser {

  def parse(data: String): ChangeRequest
}


class ScalaChangeRequestParser(client: SchemaRegistryClient,
                               adminClient: MigrationAdminClient) extends ChangeRequestParser {

  val tb = universe.runtimeMirror(getClass.getClassLoader).mkToolBox()

  override def parse(data: String): ChangeRequest = {

    val fullMigrationString = registerClassToRuntime(data)
    val parsed: tb.u.Tree = tb.parse(fullMigrationString)
    tb.compile(parsed).apply()
      .asInstanceOf[Class[Migration]]
      .getConstructor(classOf[MigrationClients])
      .newInstance(new MigrationClients(client, adminClient))
  }

  // pure scala migrations require to be registered in the runtime, we do that like this.
  // scala.reflect.classTag[CreateTopicMigration].runtimeClass
  val classPattern: Regex = "class ([0-9a-zA-Z ]+)\\(".r

  private def registerClassToRuntime(data: String): String = {

    classPattern.findFirstMatchIn(data) match {
      case Some(exp) => {
        val className = exp.group(1)
        s"${data} \n scala.reflect.classTag[${className}].runtimeClass "
      }
      case None => {
        throw new MigrationParsingException("Error parsing a migration, no class found")
      }
    }

  }
}

class Antlr4ChangeRequestParser(client: SchemaRegistryClient, adminClient: MigrationAdminClient) extends ChangeRequestParser {

  private val LOGGER = LogManager.getLogger(classOf[MigrationParserApp])

  override def parse(data: String): ChangeRequest = {
    LOGGER.debug(s"Parsing $data")

    val errorListener = new KafkaMigrationsErrorListener()

    val charStream: CodePointCharStream = CharStreams.fromString(data)
    val lexer: KafkaMigrationsLexer = new KafkaMigrationsLexer(charStream)
    lexer.addErrorListener(errorListener)

    val tokens: CommonTokenStream = new CommonTokenStream(lexer)
    val parser = new KafkaMigrationsParser(tokens)
    parser.addErrorListener(errorListener)

    val app = new MigrationParserApp(client, adminClient)
    try {
      parser.migration.enterRule(app)
    }
    catch {
      case e: Exception => {
        throw MigrationParsingException(e)
      }
    }

    app.asChangeRequest
  }
}

