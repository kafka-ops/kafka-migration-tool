package com.purbon.kafka.services

import java.util.Collections

import com.purbon.kafka.readers.{BrokerChangeRequest, ChangeRequestReader, SchemaRegistrySingleChangeRequest}
import com.purbon.kafka.{FileStatusKeeper, SchemaRegistryClient}
import org.apache.kafka.clients.admin.{AdminClient, NewTopic}
import org.json4s._
import org.json4s.native.JsonMethods._

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
          case scr: SchemaRegistrySingleChangeRequest => execute(scr)
          case bcr: BrokerChangeRequest => executeKafkaRequest(bcr)
        }

      }
    }
  }

  private def execute(changeRequest: SchemaRegistrySingleChangeRequest): Unit = {
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

  private def executeKafkaRequest(changeRequest: BrokerChangeRequest) : Unit = {
    println(changeRequest.action)
    changeRequest.action match {
      case "create-topic" => {
        val numPartitions: Int = changeRequest.config.numPartitions
        val replicationFactor: Short = changeRequest.config.replicationFactor
        val newTopic = new NewTopic(changeRequest.topic, numPartitions, replicationFactor)
        adminClient.createTopics(Collections.singleton(newTopic))
      }
      case "delete-topic" => {
        adminClient.deleteTopics(Collections.singleton(changeRequest.topic))
      }
    }
  }
}
