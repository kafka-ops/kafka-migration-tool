package com.purbon.kafka

import java.io.IOException

import com.purbon.kafka.readers.DirectoryChangeRequestReader
import com.purbon.kafka.services.{CleanService, MigrationService, Service}

object ActionService {

  def apply(config: Config, fileStatusKeeper: FileStatusKeeper): Service = {
    val srClient = new SchemaRegistryClient(config.schemaRegistryUrl)
    config.action match {
      case Some(action) => {
        action match {
          case "migrate" => {
            val migrationChangeRequestReader = new DirectoryChangeRequestReader(directory = config.migrationsURI)
            new MigrationService(srClient,
              migrationChangeRequestReader,
              fileStatusKeeper)
          }
          case "clean" => new CleanService(srClient)
          case _ => {
            throw new IOException("Incorrect action requested")
          }
        }
      }
      case None => {
        throw new IOException("Incorrect action requested")
      }
    }
  }
}
