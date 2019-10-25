package com.purbon.kafka.readers

import com.purbon.kafka.SchemaRegistryClient
import com.purbon.kafka.parsers.ScalaChangeRequestParser
import org.mockito.{ArgumentMatchersSugar, MockitoSugar}
import org.scalatest.{FunSpec, Matchers}

class ScalaDSLChangeRequestParserTest  extends FunSpec
  with Matchers
  with MockitoSugar
  with ArgumentMatchersSugar {

  describe("A change request parser") {

    val mockClient = mock[SchemaRegistryClient]
    val migrationParser = new ScalaChangeRequestParser(mockClient)

    it("should be able to extract request types from migration description files") {

      val data =
        """
          |import com.purbon.kafka.SchemaRegistryClient
          |import com.purbon.kafka.parsers.SchemaMigration
          |
          |class SchemaSetupMigration(client: SchemaRegistryClient) extends SchemaMigration(client) {
          |
          |  def up(): Unit = {
          |    val schema = Map( "schema" -> Map( "type" -> "string ") )
          |    register("kafka-key2", schema)
          |  }
          |
          |  def down(): Unit = {
          |    remove("kafka-key2", "1")
          |  }
          |}
          |scala.reflect.classTag[SchemaSetupMigration].runtimeClass
         """.stripMargin

      when(mockClient.addSchema(eqTo("kafka-key2"), any[String])) thenReturn "foo"
      migrationParser.parse(data).up
      verify(mockClient, times(1)).addSchema(eqTo("kafka-key2"), any[String])

    }

    describe("A broker change request reader") {

      it ("should parse configuration parameters") {

      }

    }
  }



}