package com.purbon.kafka

import com.purbon.kafka.readers.ChangeRequestReader
import scopt.OParser

object KafkaMigrationToolCLI {

  val changeRequestReaderClassName = "com.purbon.kafka.readers.DirectoryChangeRequestReader"

  def main(args: Array[String]): Unit = {

    val fileStatusKeeper = new FileStatusKeeper
    try {
      fileStatusKeeper.load
      OParser.parse(parser, args, Config()) match {
        case Some(config) => {

          val changeRequestReader = Class.forName(changeRequestReaderClassName)
            .getConstructor(classOf[String])
            .newInstance(config.migrationsURI)
            .asInstanceOf[ChangeRequestReader]

          ActionService(config, fileStatusKeeper, changeRequestReader).run
        }
        case _ => {
          //TODO fill if ever necessary
        }
      }
    }
    finally
    {
      fileStatusKeeper.save
    }
  }

  private def parser: OParser[Unit, Config] = {

    val builder = OParser.builder[Config]

    {
      import builder._

      OParser.sequence(
        programName("Kafka Migration Tool"),
        opt[String]('s', "schema-registry url")
          .action((x,c) => c.copy( schemaRegistryUrl = x))
          .text("Schema Registry destination url"),
        opt[String]('m', "migrations url")
          .action((x,c) => c.copy( migrationsURI = x))
          .text("Where to find the stored migrations"),
        cmd("migrate")
          .action( (_,c) => c.copy(action = Some("migrate") ) )
          .text("migrate command"),
        cmd("clean")
          .action( (_,c) => c.copy(action = Some("clean") ) )
          .text("clean command"),

      )
    }
  }
}
