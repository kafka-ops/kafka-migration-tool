package com.purbon.kafka.services

import com.purbon.kafka.FileStatusKeeper
import com.purbon.kafka.readers.ChangeRequestReader

class MigrationService(changeRequestReader: ChangeRequestReader,
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

class MigrationCleanupService(changeRequestReader: ChangeRequestReader,
                       fileStatusKeeper: FileStatusKeeper) extends Service {

  override def run : Unit = {
    println(s"Running the Migration clean up service")
    changeRequestReader.foreach { changeRequest =>
      if (!changeRequest.name.startsWith("#")) {
        println(changeRequest.name)
        changeRequest.down()
      }
    }
  }
}