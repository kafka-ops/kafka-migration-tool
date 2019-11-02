package com.purbon.kafka.services

import java.io.File

import com.purbon.kafka.clients.MigrationAdminClient
import com.purbon.kafka.parsers.{ChangeRequest, MigrationClients, ScalaChangeRequestParser, SchemaMigration}
import com.purbon.kafka.readers._
import com.purbon.kafka.{FileStatusKeeper, SchemaRegistryClient}
import org.mockito.{ArgumentMatchersSugar, MockitoSugar}
import org.scalatest.{FunSpec, Matchers}

class MigrationServiceTest extends FunSpec
  with Matchers
  with MockitoSugar
  with ArgumentMatchersSugar {

  describe ("A migration service manager") {

    val mockFileStatusManager = mock[FileStatusKeeper]

    it ("should process change request with the registry accordingly") {

      val mockSRClient = mock[SchemaRegistryClient]
      val mockAdminClient = mock[MigrationAdminClient]

      when(mockSRClient.url).thenReturn("http://foo:8082")

      class SchemaSetupMigration(clients: MigrationClients) extends SchemaMigration(clients) {

        def up(): Unit = {
          val schema = Map( "schema" -> Map( "type" -> "string ") )
          register("kafka-key2", schema)
        }

        def down(): Unit = {
          remove("kafka-key2", "latest")
        }
      }

      val migrationClients = new MigrationClients(schemaRegistry = mockSRClient, adminClient = mockAdminClient)
      val changeRequest1 = new SchemaSetupMigration(migrationClients);

      when(mockSRClient.addSchema(eqTo("kafka-key2"), any[String])).thenReturn("{\"id\":1}")

      val mockChangeRequestIt = new MockChangeRequestIterator(
        requests = List(changeRequest1),
        client = mockSRClient,
        adminClient = mockAdminClient)
      val mockChangeRequestReader = new DirectoryChangeRequestReader(mockChangeRequestIt)

      val migrationService = new MigrationService(
        reader = mockChangeRequestReader,
        fileStatusKeeper = mockFileStatusManager)

      migrationService.run

      verify(mockSRClient, times(1)).addSchema(eqTo("kafka-key2"), any[String])

    }
  }

}

class MockChangeRequestIterator(requests: List[ChangeRequest], client: SchemaRegistryClient, adminClient: MigrationAdminClient)
  extends FSMigrationReaderIterator(Iterator.empty[File], new ScalaChangeRequestParser(client, adminClient)) {

  val it: Iterator[ChangeRequest] = requests.iterator

  override def hasNext: Boolean = it.hasNext

  override def next(): ChangeRequest = it.next()
}
