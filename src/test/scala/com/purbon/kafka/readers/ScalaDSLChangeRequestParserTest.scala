package com.purbon.kafka.readers

import com.purbon.kafka.SchemaRegistryClient
import com.purbon.kafka.clients.MigrationAdminClient
import com.purbon.kafka.parsers.ScalaChangeRequestParser
import org.mockito.{ArgumentMatchersSugar, MockitoSugar}
import org.scalatest.{FunSpec, Matchers}

class ScalaDSLChangeRequestParserTest  extends FunSpec
  with Matchers
  with MockitoSugar
  with ArgumentMatchersSugar {

  val mockClient = mock[SchemaRegistryClient]
  val mockAdminClient = mock[MigrationAdminClient]
  val migrationParser = new ScalaChangeRequestParser(mockClient, mockAdminClient)

  describe("A change request parser") {

    it("should be able to perform up actions") {

      val data =
        """
          |import com.purbon.kafka.parsers.{MigrationClients, SchemaMigration}
          |
          |class SchemaSetupMigration(clients: MigrationClients) extends SchemaMigration(clients) {
          |
          |  def up(): Unit = {
          |    val schema = Map( "schema" -> Map( "type" -> "string ") )
          |    register("kafka-key2", schema)
          |  }
          |
          |  def down(): Unit = {
          |    remove("kafka-key2", "latest")
          |  }
          |}
          |scala.reflect.classTag[SchemaSetupMigration].runtimeClass
         """.stripMargin

      when(mockClient.addSchema(eqTo("kafka-key2"), any[String])) thenReturn "foo"
      migrationParser.parse(data).up
      verify(mockClient, times(1)).addSchema(eqTo("kafka-key2"), any[String])

    }

    describe("A broker change request parser") {

      it ("should be able to perform up action") {

        val data =
          """
            |import com.purbon.kafka.parsers.{MigrationClients, TopicMigration}
            |
            |class CreateTopicMigration(clients: MigrationClients) extends TopicMigration(clients) {
            |
            |  def up(): Unit = {
            |    createTopic("foo", 1, 1)
            |  }
            |
            |  def down(): Unit = {
            |    deleteTopic("foo")
            |  }
            |}
            |scala.reflect.classTag[CreateTopicMigration].runtimeClass
          """.stripMargin

        doNothing.when(mockAdminClient).createTopic("foo", 1, 1)
        migrationParser.parse(data).up
        verify(mockAdminClient, times(1)).createTopic("foo", 1, 1)
      }

    }
  }



}