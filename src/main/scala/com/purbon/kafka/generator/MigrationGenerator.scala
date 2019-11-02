package com.purbon.kafka.generator

import java.io.{File, IOException, PrintWriter}
import java.nio.file.Paths

object MigrationGenerator {

  val schemaMigration = "schemaMigration"
  val topicMigration = "topicMigration"
  val accessMigration = "accessMigration"

  def generate(path: String, migrationType: String) : Unit = {

    if (migrationType.equals(schemaMigration)) {
      doSchemaMigration(path)
    }
    else if (migrationType.equals(topicMigration)) {
      doTopicMigration(path)
    }
    else if (migrationType.equals(accessMigration)) {
      doAccessMigration(path)
    }
    else {
      throw new IOException("Incorrect migration type")
    }
  }

  private def doSchemaMigration(path: String): Unit = {
    val currentTime = System.currentTimeMillis()
    val data = s""" import com.purbon.kafka.parsers.{MigrationClients, SchemaMigration}
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

    val filename = Paths.get(path, s"${currentTime}_SchemaMigration.scala")
    flushMigration(filename.toString, data)
  }

  private def doTopicMigration(path: String): Unit = {
    val currentTime = System.currentTimeMillis()
    val data = s"""import com.purbon.kafka.parsers.{MigrationClients, TopicMigration}
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
    val filename = Paths.get(path, s"${currentTime}_TopicMigration.scala")
    flushMigration(filename.toString, data)
  }

  private def doAccessMigration(path: String): Unit = {
    val currentTime = System.currentTimeMillis()
    val data = s"""import com.purbon.kafka.parsers.{MigrationClients, RoleMigration}
                  |
                  | class CreateRoleMigration${currentTime}(clients: MigrationClients) extends RoleMigration(clients) {
                  |
                  |  def up(): Unit = {
                  |    //TODO fill up with code
                  |  }
                  |
                  |  def down(): Unit = {
                  |    //TODO fill up with code
                  |  }
                  |}""".stripMargin
    val filename = Paths.get(path, s"${currentTime}_AccessMigration.scala")
    flushMigration(filename.toString, data)

  }

  private def flushMigration(filename: String, content: String): Unit = {
    val pw = new PrintWriter(new File(filename))
    pw.print(content)
    pw.close()
  }

}
