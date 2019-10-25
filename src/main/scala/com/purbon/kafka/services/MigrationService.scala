package com.purbon.kafka.services

import com.purbon.kafka.FileStatusKeeper
import com.purbon.kafka.readers.ChangeRequestReader
import org.apache.kafka.clients.admin.AdminClient

class MigrationService(adminClient: AdminClient,
                       changeRequestReader: ChangeRequestReader,
                       fileStatusKeeper: FileStatusKeeper) extends Service {

  override def run : Unit = {
    println(s"Running the Migration service")
    changeRequestReader.foreach { changeRequest =>
      if (!changeRequest.name.startsWith("#")) {
        println(changeRequest.name)
        changeRequest.up
      }
    }
  }

}
