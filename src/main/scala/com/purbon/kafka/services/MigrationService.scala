package com.purbon.kafka.services

import com.purbon.kafka.executors.{KafkaExecutor, SchemaRegistryExecutor}
import com.purbon.kafka.readers.{BrokerChangeRequest, ChangeRequestReader, SchemaRegistrySingleChangeRequest}
import com.purbon.kafka.{FileStatusKeeper, SchemaRegistryClient}
import org.apache.kafka.clients.admin.AdminClient

class MigrationService(schemaRegistryClient: SchemaRegistryClient,
                       adminClient: AdminClient,
                       changeRequestReader: ChangeRequestReader,
                       fileStatusKeeper: FileStatusKeeper) extends Service {

  override def run : Unit = {
    println(s"Running the Migration service with: ${schemaRegistryClient.url}")
    changeRequestReader.load.foreach { changeRequest =>
      if (!changeRequest.name.startsWith("#")) {
        println(changeRequest.name)
        changeRequest match {
          case scr: SchemaRegistrySingleChangeRequest => {
            SchemaRegistryExecutor(schemaRegistryClient, fileStatusKeeper).execute(scr)
          }
          case bcr: BrokerChangeRequest => KafkaExecutor(adminClient).execute(bcr)
        }

      }
    }
  }

}
