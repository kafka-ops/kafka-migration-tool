package com.purbon.kafka.readers

import com.purbon.kafka.SchemaRegistryClient
import com.purbon.kafka.clients.MigrationAdminClient
import com.purbon.kafka.parsers.{Antlr4ChangeRequestParser, MigrationParsingException, ScalaChangeRequestParser}
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

    it("should parse proper schema migrations") {
      val migration =
        """
          |SchemaMigration;
          |
          |def up {
          | register( "foo" );
          |};
          |def down {
          | var bar = "123";
          | delete( "foo", bar);
          | update();
          |};
          |""".stripMargin

      parser.parse(migration)
    }

    it("should parse proper topic migrations") {
      val migration =
        """
          |TopicMigration;
          |
          |def up {
          | register( "foo" );
          |};
          |def down {
          | delete( "foo", "bar");
          | update();
          |};
          |""".stripMargin

      parser.parse(migration)
    }


    it("should raise an error for an wrong input") {
      val migration =
        """
          |SchemaMigration;
          |
          |def foo {
          | register();
          |};
          |def down {
          | delete();
          | update;
          |};
          |""".stripMargin

      an [MigrationParsingException] should be thrownBy parser.parse(migration)
    }
  }
}