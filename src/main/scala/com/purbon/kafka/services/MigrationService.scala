package com.purbon.kafka.services

import com.purbon.kafka.{FileStatusKeeper, SchemaRegistryClient}
import com.purbon.kafka.readers.{ChangeRequestReader, GroupChangeRequest, SingleChangeRequest}
import org.json4s._
import org.json4s.native.JsonMethods._

class MigrationService(schemaRegistryClient: SchemaRegistryClient,
                       changeRequestReader: ChangeRequestReader,
                       fileStatusKeeper: FileStatusKeeper) extends Service {

  override def run : Unit = {
    println(s"Running the Migration service with: ${schemaRegistryClient.url}")
    changeRequestReader.load.foreach { changeRequest =>
      if (!changeRequest.name.startsWith("#")) {
        println(changeRequest.name)
        changeRequest match {
          case scr: SingleChangeRequest => execute(scr)
          case gcr: GroupChangeRequest => {
            gcr.actions.foreach(execute)
          }
        }

      }
    }
  }

  private def execute(changeRequest: SingleChangeRequest): Unit = {
    //println(s"${changeRequest.action} ${changeRequest.data} ${changeRequest.subject}")
    val responseJsonText = changeRequest.action match {
      case "register" => {
       schemaRegistryClient.addSchema(changeRequest.subject, changeRequest.data)
      }
      case "delete" => {
        schemaRegistryClient.deleteSchema(changeRequest.subject, changeRequest.id.toString)
      }
      case "set_top_level_compatibility" => {
        schemaRegistryClient.setTopLevelCompatibility(changeRequest.data)
      }
    }
    val responseJSON: JValue = parse(responseJsonText)
    fileStatusKeeper.update(subject = changeRequest.subject,
                            action = changeRequest.action,
                            data = changeRequest.data,
                            responseJSON = responseJSON)
  }
}
