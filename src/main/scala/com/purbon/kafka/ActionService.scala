package com.purbon.kafka

import java.io.IOException

import com.purbon.kafka.readers.ChangeRequestReader
import com.purbon.kafka.services.{MigrationCleanupService, MigrationGenerationService, MigrationService, Service}

object ActionService {

  def apply(config: Config,
            stateManager: StateManager,
            changeRequestReader: ChangeRequestReader): Service = {

    config.action match {
      case Some(action) => {
        action match {
          case "migrate" => {
            new MigrationService(changeRequestReader, stateManager)
          }
          case "migrate:up" => {
            new MigrationService(changeRequestReader, stateManager)
          }
          case "migrate:down" => {
            new MigrationCleanupService(changeRequestReader, stateManager)
          }
          case "generate" => {
            new MigrationGenerationService(config.migrationsURI, config.migrationType)
          }
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
