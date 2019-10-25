package com.purbon.kafka.readers

import org.scalatest.{FunSpec, Matchers}
import org.scalatestplus.mockito.MockitoSugar

class ScalaDSLChangeRequestParserTest  extends FunSpec
  with Matchers
  with MockitoSugar {

  describe("A change request parser") {


    val migrationParser = new ScalaChangeRequestParser

    it("should be able to extract request types from migration description files") {

      val data =
        """
           import com.purbon.kafka.readers._

           class SchemaSetupMigration extends SchemaMigration {

            def up(): Unit = {
              val schema = Map( "schema" -> Map( "type" -> "string ") )
              register("kafka-key2", schema)
            }

            def down(): Unit = {
              println("down");
              remove("kafka-key2")
            }
          }
          scala.reflect.classTag[SchemaSetupMigration].runtimeClass
         """

      migrationParser.parse(data).up
    }

    describe("A broker change request reader") {

      it ("should parse configuration parameters") {

      }

    }
  }



}