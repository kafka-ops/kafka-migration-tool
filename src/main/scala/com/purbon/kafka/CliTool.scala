package com.purbon.kafka

import java.util.Properties

import com.purbon.kafka.clients.MigrationAdminClient
import com.purbon.kafka.parsers.{ChangeRequestParser, ScalaChangeRequestParser}
import com.purbon.kafka.readers.ChangeRequestReader
import org.apache.kafka.clients.admin.{AdminClient, AdminClientConfig}
import scopt.OParser

object CliTool {

  val changeRequestReaderClassName = "com.purbon.kafka.readers.DirectoryChangeRequestReader"

  def main(args: Array[String]): Unit = {
    val stateManager = new StateManager(handler = new LocalStateHandler)
    val cliLock = ExecutionLock.acquireLock
    try {
      stateManager.load
      OParser.parse(parser, args, Config()) match {
        case Some(config) => {
          val srClient = new SchemaRegistryClient(config.schemaRegistryUrl)
          val migrationAdminClient: MigrationAdminClient = new MigrationAdminClient(AdminClient.create(props(config)))
          val changeRequestParser = new ScalaChangeRequestParser(srClient, migrationAdminClient)
          val changeRequestReader = buildChangeRequestReader(config, changeRequestParser)
          ActionService(config, stateManager, changeRequestReader).run
          ReportService(
            schemaRegistryClient = srClient,
            kafkaAdminClient = migrationAdminClient
          ).run
        }
        case _ => {
          //TODO fill if ever necessary
        }
      }
    }
    finally
    {
      ExecutionLock.releaseLock(cliLock)
      stateManager.save
    }
  }

  private def buildChangeRequestReader(config: Config, changeRequestParser: ChangeRequestParser) : ChangeRequestReader = {
    Class.forName(changeRequestReaderClassName)
      .getConstructor(classOf[String],
        classOf[ChangeRequestParser])
      .newInstance(config.migrationsURI, changeRequestParser)
      .asInstanceOf[ChangeRequestReader]
  }

  private def parser: OParser[Unit, Config] = {

    val builder = OParser.builder[Config]

    {
      import builder._

      OParser.sequence(
        programName("Kafka Migration Tool"),
        opt[String]('b', "brokers url")
          .action((x,c) => c.copy( brokersUrl = x))
          .text("Kafka brokers location"),
        opt[String]('s', "schema-registry url")
          .action((x,c) => c.copy( schemaRegistryUrl = x))
          .text("Schema Registry destination url"),
        opt[String]('m', "migrations url")
          .action((x,c) => c.copy( migrationsURI = x))
          .text("Where to find the stored migrations"),
        cmd("migrate")
          .action( (_,c) => c.copy(action = Some("migrate") ) )
          .text("apply all migrations"),
        cmd("migrate:up")
          .action( (_,c) => c.copy(action = Some("migrate:up") ) )
          .text("apply a selected migration change"),
        cmd("migrate:down")
          .action( (_,c) => c.copy(action = Some("migrate:down") ) )
          .text("remove a selected migration change"),
        cmd("generate")
          .action( (_,c) => c.copy(action = Some("generate") ) )
          .text("Generate a migration"),
        opt[String]('t', "migrationType type")
          .action((x,c) => c.copy( migrationType = Some(x)))
          .text("The Migration Type (schemaMigration, topicMigration, accessMigration)"),
      )
    }
  }

  private def props(config: Config): Properties = {
    val props = new Properties()
    props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, config.brokersUrl)
    props
  }
}
