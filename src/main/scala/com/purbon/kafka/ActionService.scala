package com.purbon.kafka

import java.io.IOException
import java.util.Properties

import org.apache.kafka.clients.admin.{AdminClient, AdminClientConfig}
import com.purbon.kafka.readers.ChangeRequestReader
import com.purbon.kafka.services.{CleanService, MigrationService, Service}

object ActionService {

  private def props(config: Config): Properties = {
    val props = new Properties()
    props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, config.brokersUrl)
    props
  }

  def apply(config: Config,
            fileStatusKeeper: FileStatusKeeper,
            changeRequestReader: ChangeRequestReader): Service = {
    val adminClient: AdminClient = AdminClient.create(props(config))

    config.action match {
      case Some(action) => {
        action match {
          case "migrate" => {
            new MigrationService(
              adminClient,
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
