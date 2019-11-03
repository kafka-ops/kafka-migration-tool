package com.purbon.kafka.generator

import java.io.IOException

object MigrationGenerator {

  val schemaMigration = "schemaMigration"
  val topicMigration = "topicMigration"
  val accessMigration = "accessMigration"

  def generate(migrationType: String, writer: MigrationWriter): String = {

    if (migrationType.equals(schemaMigration)) {
      doSchemaMigration(writer)
    }
    else if (migrationType.equals(topicMigration)) {
      doTopicMigration(writer)
    }
    else if (migrationType.equals(accessMigration)) {
      doAccessMigration(writer)
    }
    else {
      throw new IOException("Incorrect migration type")
    }
  }

  private def doSchemaMigration(writer: MigrationWriter): String = {
    val currentTime = System.currentTimeMillis()
    val data =
      s""" import com.purbon.kafka.parsers.{MigrationClients, SchemaMigration}
         |
                  | class SchemaSetupMigration${currentTime}(clients: MigrationClients) extends SchemaMigration(clients) {
         |
                  |  def up(): Unit = {
         |    //TODO fill up with code
         |  }
         |
                  |  def down(): Unit = {
         | //TODO fill up with code
         |  }
         |}""".stripMargin

    val filename  = s"${currentTime}_SchemaMigration.scala"
    writer.flush(filename, data)
  }

  private def doTopicMigration(writer: MigrationWriter): String = {
    val currentTime = System.currentTimeMillis()
    val data =
      s"""import com.purbon.kafka.parsers.{MigrationClients, TopicMigration}
         |
                 |class CreateTopicMigration${currentTime}(clients: MigrationClients) extends TopicMigration(clients) {
         |
                 |  def up(): Unit = {
         |    //TODO fill up with code
         |  }
         |
                 |  def down(): Unit = {
         |    //TODO fill up with code
         |  }
         |}""".stripMargin
    val filename  = s"${currentTime}_TopicMigration.scala"
    writer.flush(filename, data)
  }

  private def doAccessMigration(writer: MigrationWriter): String = {
    val currentTime = System.currentTimeMillis()
    val data =
      s"""import com.purbon.kafka.parsers.{MigrationClients, RolesMigration}
         |
                  | class CreateRoleMigration${currentTime}(clients: MigrationClients) extends RolesMigration(clients) {
         |
                  |  def up(): Unit = {
         |    //TODO fill up with code
         |  }
         |
                  |  def down(): Unit = {
         |    //TODO fill up with code
         |  }
         |}""".stripMargin
    val filename  = s"${currentTime}_AccessMigration.scala"
    writer.flush(filename, data)

  }


}
