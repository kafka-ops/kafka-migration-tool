package com.purbon.kafka.readers

import com.purbon.kafka.SchemaRegistryClient
import com.purbon.kafka.clients.MigrationAdminClient
import com.purbon.kafka.parsers.{Antlr4ChangeRequestParser, ScalaChangeRequestParser}
import org.mockito.{ArgumentMatchersSugar, MockitoSugar}
import org.scalatest.{FunSpec, Matchers}

class AntlrDSLChangeRequestParserTest  extends FunSpec
  with Matchers
  with MockitoSugar
  with ArgumentMatchersSugar {

  val mockClient = mock[SchemaRegistryClient]
  val mockAdminClient = mock[MigrationAdminClient]
  val parser = new Antlr4ChangeRequestParser(mockClient, mockAdminClient)

  describe("A change request parser") {

    it("should parse proper input files") {
      val migration =
        """
          |SchemaMigration;
          |
          |def up {
          | register;
          |};
          |def down {
          | delete;
          | update;
          |};
          |""".stripMargin

      parser.parse(migration)

    }
  }
}