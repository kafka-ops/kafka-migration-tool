package com.purbon.kafka.services

import java.io.File

import com.purbon.kafka.readers._
import com.purbon.kafka.{FileStatusKeeper, SchemaRegistryClient}
import org.apache.kafka.clients.admin.AdminClient
import org.mockito.Mockito
import org.scalatest.{FunSpec, Matchers}
import org.scalatestplus.mockito.MockitoSugar

class MigrationServiceTest  extends FunSpec
  with Matchers
  with MockitoSugar {

  import Mockito._

  describe ("A migration service manager") {


    val mockFileStatusManager = mock[FileStatusKeeper]


    it ("should process change request with the registry accordingly") {

      val mockSRClient = mock[SchemaRegistryClient]
      val mockAdminClient = mock[AdminClient]

      when(mockSRClient.url).thenReturn("http://foo:8082")

      val changeRequest1 = new SchemaRegistrySingleChangeRequest
      changeRequest1.`type` = "schema-registry"
      changeRequest1.subject = "foo"
      changeRequest1.action = "register"
      changeRequest1.data = "{\"schema\": \"{\\\"type\\\": \\\"string\\\"}\"}"

      val changeRequest2 = new SchemaRegistrySingleChangeRequest
      changeRequest2.`type` = "schema-registry"
      changeRequest2.subject = "foo"
      changeRequest2.action = "delete"
      changeRequest2.id = 2

      when(mockSRClient.addSchema(changeRequest1.subject,changeRequest1.data)).thenReturn("{\"id\":1}")
      when(mockSRClient.deleteSchema(changeRequest2.subject,changeRequest2.id.toString)).thenReturn("{\"id\":2}")

      val mockChangeRequestIt = new MockChangeRequestIterator(List(changeRequest1, changeRequest2))
      val mockChangeRequestReader = new DirectoryChangeRequestReader(mockChangeRequestIt)

      val migrationService = new MigrationService(
        schemaRegistryClient = mockSRClient,
        adminClient = mockAdminClient,
        changeRequestReader = mockChangeRequestReader,
        fileStatusKeeper = mockFileStatusManager)

      migrationService.run

      verify(mockSRClient, times(1)).addSchema(changeRequest1.subject, changeRequest1.data)
      verify(mockSRClient, times(1)).deleteSchema(changeRequest2.subject, changeRequest2.id.toString)

    }
  }

}


class MockChangeRequestIterator(requests: List[ChangeRequest])
  extends FSMigrationReaderIterator(Iterator.empty[File], new YAMLChangeRequestParser) {

  val it: Iterator[ChangeRequest] = requests.iterator

  override def hasNext: Boolean = it.hasNext

  override def next(): ChangeRequest = it.next()
}
