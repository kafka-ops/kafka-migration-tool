package com.purbon.kafka

import java.io.IOException

import com.purbon.kafka.readers.ChangeRequestReader
import com.purbon.kafka.services.{MigrationCleanupService, MigrationService, Service}

object ActionService {

  def apply(config: Config,
            fileStatusKeeper: FileStatusKeeper,
            changeRequestReader: ChangeRequestReader): Service = {

    config.action match {
      case Some(action) => {
        action match {
          case "migrate" => {
            new MigrationService(changeRequestReader, fileStatusKeeper)
          }
          case "migrate:up" => {
            new MigrationService(changeRequestReader, fileStatusKeeper)
          }
          case "migrate:down" => {
            new MigrationCleanupService(changeRequestReader, fileStatusKeeper)
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
