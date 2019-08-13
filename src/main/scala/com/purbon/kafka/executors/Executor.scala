package com.purbon.kafka.executors

import java.util.Collections

import com.purbon.kafka.readers.{BrokerChangeRequest, SchemaRegistrySingleChangeRequest}
import com.purbon.kafka.{FileStatusKeeper, SchemaRegistryClient}
import org.apache.kafka.clients.admin.{AdminClient, NewTopic}
import org.json4s.JValue
import org.json4s.native.JsonMethods.parse


trait Executor {

}

object SchemaRegistryExecutor {

  val REGISTER_SCHEMA_ACTION: String = "register"
  val DELETE_SCHEMA_ACTION: String = "delete"
  val SET_COMPAT_LEVEL_ACTION: String = "set_top_level_compatibility"

  def apply(srClient: SchemaRegistryClient, fileStatusKeeper: FileStatusKeeper): SchemaRegistryExecutor = {
    new SchemaRegistryExecutor(srClient, fileStatusKeeper)
  }

}

class SchemaRegistryExecutor(srClient: SchemaRegistryClient, fileStatusKeeper: FileStatusKeeper ) extends Executor {


  import SchemaRegistryExecutor._

  def execute(changeRequest: SchemaRegistrySingleChangeRequest): Unit = {
    val responseJsonText: String = changeRequest.action match {
      case REGISTER_SCHEMA_ACTION => {
        srClient.addSchema(changeRequest.subject, changeRequest.data)
      }
      case DELETE_SCHEMA_ACTION => {
        srClient.deleteSchema(changeRequest.subject, changeRequest.id.toString)
      }
      case SET_COMPAT_LEVEL_ACTION => {
        srClient.setTopLevelCompatibility(changeRequest.data)
      }
    }

    val responseJSON: JValue = parse(responseJsonText)

    fileStatusKeeper.update(
      subject = changeRequest.subject,
      action = changeRequest.action,
      data = changeRequest.data,
      responseJSON = responseJSON)
  }

}

object KafkaExecutor {

  val CREATE_TOPIC_ACTION: String = "create-topic"
  val DELETE_TOPIC_ACTION: String = "delete-topic"

  def apply(adminClient: AdminClient) = new KafkaExecutor(adminClient)
}

class KafkaExecutor(adminClient: AdminClient) extends Executor {

  import KafkaExecutor._

  def execute(changeRequest: BrokerChangeRequest) : Unit = {

    changeRequest.action match {
      case CREATE_TOPIC_ACTION => {
        val numPartitions = changeRequest.numPartitions
        val replicationFactor = changeRequest.replicationFactor
        val newTopic = new NewTopic(changeRequest.topic,
          numPartitions,
          replicationFactor.toShort)
        adminClient.createTopics(Collections.singleton(newTopic))
      }
      case DELETE_TOPIC_ACTION => {
        adminClient.deleteTopics(Collections.singleton(changeRequest.topic))
      }
    }
  }
}
