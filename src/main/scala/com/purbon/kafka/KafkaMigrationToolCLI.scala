package com.purbon.kafka

import java.io.File
import java.nio.channels.{FileChannel, FileLock}
import java.nio.file.{Paths, StandardOpenOption}
import java.util.Properties

import com.purbon.kafka.clients.MigrationAdminClient
import com.purbon.kafka.parsers.{ChangeRequestParser, ScalaChangeRequestParser}
import com.purbon.kafka.readers.ChangeRequestReader
import org.apache.kafka.clients.admin.{AdminClient, AdminClientConfig}
import scopt.OParser

object KafkaMigrationToolCLI {

  val changeRequestReaderClassName = "com.purbon.kafka.readers.DirectoryChangeRequestReader"

  def main(args: Array[String]): Unit = {

    val stateManager = new StateManager(handler = new LocalStateHandler)
    val fileLockChannel = FileChannel.open(defaultFileLockPath, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW)
    val lock = acquireLock(fileLockChannel)
    try {
      stateManager.load
      stateManager.print
      OParser.parse(parser, args, Config()) match {
        case Some(config) => {

          val srClient = new SchemaRegistryClient(config.schemaRegistryUrl)
          val adminClient: AdminClient = AdminClient.create(props(config))
          val scalaChangeRequestParser = new ScalaChangeRequestParser(srClient, new MigrationAdminClient(adminClient))

          val changeRequestReader = Class.forName(changeRequestReaderClassName)
            .getConstructor(classOf[String],
              classOf[ChangeRequestParser])
            .newInstance(config.migrationsURI, scalaChangeRequestParser)
            .asInstanceOf[ChangeRequestReader]

          ActionService(config, stateManager, changeRequestReader).run

        }
        case _ => {
          //TODO fill if ever necessary
        }
      }
    }
    finally
    {
      stateManager.save
      releaseLock(fileLockChannel, lock)
    }
  }

  val defaultFileLockPath = Paths.get(".lock")

  private def acquireLock(fileChannel: FileChannel): FileLock = {
    fileChannel.tryLock();
  }

  private def releaseLock(fileChannel: FileChannel, lock: FileLock): Unit = {
    lock.release();
    fileChannel.close()
    new File(defaultFileLockPath.getFileName.toString).delete()
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
