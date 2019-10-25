package com.purbon.kafka

import java.io.IOException

import com.purbon.kafka.readers.ChangeRequestReader
import com.purbon.kafka.services.{CleanService, MigrationService, Service}

object ActionService {

  def apply(config: Config,
            fileStatusKeeper: FileStatusKeeper,
            changeRequestReader: ChangeRequestReader): Service = {

    config.action match {
      case Some(action) => {
        action match {
          case "migrate" => {
            new MigrationService(
              changeRequestReader,
              fileStatusKeeper)
          }
          case "migrate:up" => {
            throw new NotImplementedError("Not yet implemented")
          }
          case "migrate:down" => {
            throw new NotImplementedError("Not yet implemented")
          }
          case "clean" => new CleanService
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
