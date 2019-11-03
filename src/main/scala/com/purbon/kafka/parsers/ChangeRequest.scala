package com.purbon.kafka.parsers

import java.util.concurrent.ExecutionException

import com.purbon.kafka.SchemaRegistryClient
import com.purbon.kafka.clients.MigrationAdminClient
import org.apache.kafka.common.errors.TopicExistsException
import org.json4s.DefaultFormats
import org.json4s.jackson.Serialization.write

import scala.language.dynamics

trait ChangeRequest {

  var name: String = "default"

  def up(): Unit
  def down(): Unit
}

trait Migration extends ChangeRequest {

}

class MigrationClients(var schemaRegistry: SchemaRegistryClient, var adminClient: MigrationAdminClient) {

}

trait NiceSchemaMigrationOperations {

  implicit val formats = DefaultFormats

  def register(subject: String, schema: Map[String, Any])(implicit client: SchemaRegistryClient): Unit = {
    register(subject, write(schema))
  }

  def register(subject: String, schema: String)(implicit client: SchemaRegistryClient): Unit = {
    println(s"register ${subject}")
    client.addSchema(subject, schema)
  }

  def remove(subject: String, version: String)(implicit client: SchemaRegistryClient): Unit = {
    println(s"remove ${subject}")
    client.deleteSchema(subject, version)
  }
}

abstract class SchemaMigration(clients: MigrationClients)
  extends Migration
  with NiceSchemaMigrationOperations {

  implicit val client = clients.schemaRegistry

}



abstract class TopicMigration(clients: MigrationClients) extends Migration {

  var adminClient = clients.adminClient

  def createTopic(topicName: String, numPartitions: Int, replicationFactor: Short): Unit = {
    try {
      adminClient.createTopic(topicName, numPartitions, replicationFactor)
    } catch {
      case e: ExecutionException => {
        e.getCause match {
          case cause: TopicExistsException => println(cause.getMessage)
          case _: Exception => throw e
        }
      }
    }
  }

  def updateConfig(topicName: String, configs: Map[String, String]): Unit = {
    adminClient.updateConfigs(topicName, configs)
  }

  def deleteTopic(topicName: String): Unit = {
    adminClient
      .deleteTopic(topicName)
  }
}

abstract class RolesMigration(clients: MigrationClients) extends Migration {

  var adminClient = clients.adminClient

  def createAclsForConsumers(principal: String, topics: List[String]): Unit = {
    topics.foreach(topic => createAclsForConsumer(principal, topic))
  }

  def createAclsForConsumer(principal: String, topic: String): Unit = {
    adminClient
      .setAclsForConsumer(principal, topic)
  }

  def createAclsForProducers(principal: String, topics: List[String]): Unit = {
    topics.foreach(topic => createAclsForProducer(principal, topic))
  }

  def createAclsForProducer(principal: String, topic: String): Unit = {
    adminClient
      .setAclsForProducer(principal, topic)
  }

}