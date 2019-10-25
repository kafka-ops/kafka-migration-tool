package com.purbon.kafka.parsers

import com.purbon.kafka.SchemaRegistryClient
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

abstract class TopicMigration extends Migration {

}

abstract class SchemaMigration(client: SchemaRegistryClient) extends Migration {

  implicit val formats = DefaultFormats

  def register(subject: String, schema: Map[String, Any]): Unit = {
    println(s"register ${subject}")
    client.addSchema(subject, write(schema))
  }
  def remove(subject: String, version: String): Unit = {
    println(s"remove ${subject}")
    client.deleteSchema(subject, version)
  }
}
