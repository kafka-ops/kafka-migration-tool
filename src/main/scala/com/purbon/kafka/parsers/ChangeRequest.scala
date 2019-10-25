package com.purbon.kafka.parsers

import com.purbon.kafka.SchemaRegistryClient
import com.purbon.kafka.clients.MigrationAdminClient
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

abstract class SchemaMigration(clients: MigrationClients) extends Migration {

  implicit val formats = DefaultFormats

  def register(subject: String, schema: Map[String, Any]): Unit = {
    println(s"register ${subject}")
    clients.schemaRegistry.addSchema(subject, write(schema))
  }
  def remove(subject: String, version: String): Unit = {
    println(s"remove ${subject}")
    clients.schemaRegistry.deleteSchema(subject, version)
  }
}



abstract class TopicMigration(clients: MigrationClients) extends Migration {

  var adminClient = clients.adminClient

  def createTopic(topicName: String, numPartitions: Int, replicationFactor: Short): Unit = {
    adminClient.createTopic(topicName, numPartitions, replicationFactor)
  }

  def deleteTopic(topicName: String): Unit = {
    adminClient
      .deleteTopic(topicName)
  }
}