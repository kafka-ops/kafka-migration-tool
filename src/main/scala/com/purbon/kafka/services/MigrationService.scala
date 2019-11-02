package com.purbon.kafka.services

import com.purbon.kafka.FileStatusKeeper
import com.purbon.kafka.parsers.ChangeRequest
import com.purbon.kafka.readers.ChangeRequestReader

import scala.collection.mutable.ArrayBuffer

/**
  * Service used to apply migrations to the cluster
  *
  * @param reader a Change Request reader
  * @param fileStatusKeeper the status keeper
  */
class MigrationService(reader: ChangeRequestReader,
                       fileStatusKeeper: FileStatusKeeper) extends Service {

  override def run : Unit = {
    println(s"Running the Migration service")
    var appliedChanges = ArrayBuffer.empty[ChangeRequest]
    try {

      reader.foreach { request =>
        if (!request.name.startsWith("#")) {
          println("Applying ${request.name}")
          appliedChanges += request
          request.up
        }
      }
    } catch {
      case e: Exception => {
        println("Rollback applied changes")
        rollback(appliedChanges)
      }
    }
  }

  def rollback(changes : ArrayBuffer[ChangeRequest]): Unit = {
    reader.foreach { request =>
      println(s"Rollback the request ${request.name}")
      request.down()
    }
  }
}

/**
  * Service used to remove migrations to the cluster
  *
  * @param changeRequestReader a Change Request reader
  * @param fileStatusKeeper the status keeper
  */
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