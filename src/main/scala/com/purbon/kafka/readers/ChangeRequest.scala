package com.purbon.kafka.readers


import scala.language.dynamics

trait ChangeRequest {

  var name: String = ""

  def up(): Unit
  def down(): Unit
}

trait Migration extends ChangeRequest {


}

abstract class TopicMigration extends Migration {

}

abstract class SchemaMigration extends Migration {

  def register(subject: String, schema: Map[String, Any]): Unit = {
    println("register "+subject)
  }
  def remove(subject: String): Unit = {
    println("remove "+subject);
  }
}
