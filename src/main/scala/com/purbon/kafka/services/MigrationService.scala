package com.purbon.kafka.services

import com.purbon.kafka.readers.ChangeRequestReader
import com.purbon.kafka.{FileStatusKeeper, SchemaRegistryClient}
import org.apache.kafka.clients.admin.AdminClient

class MigrationService(schemaRegistryClient: SchemaRegistryClient,
                       adminClient: AdminClient,
                       changeRequestReader: ChangeRequestReader,
                       fileStatusKeeper: FileStatusKeeper) extends Service {

  override def run : Unit = {
    println(s"Running the Migration service with: ${schemaRegistryClient.url}")
    changeRequestReader.foreach { changeRequest =>
      if (!changeRequest.name.startsWith("#")) {
        println(changeRequest.name)

      }
    }
  }

}
