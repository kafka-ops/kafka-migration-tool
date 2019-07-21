package com.purbon.kafka.services

import com.purbon.kafka.readers.{ChangeRequest, DirectoryChangeRequestReader, SingleChangeRequest}
import com.purbon.kafka.{FileStatusKeeper, SchemaRegistryClient}
import org.mockito.Mockito
import org.scalatest.{FunSpec, Matchers}
import org.scalatestplus.mockito.MockitoSugar

class MigrationServiceTest  extends FunSpec
  with Matchers
  with MockitoSugar {

  import Mockito._

  describe ("A migration service manager") {

    val mockChangeRequestReader = mock[DirectoryChangeRequestReader]
    val mockFileStatusManager = mock[FileStatusKeeper]


    it ("should process change request with the registry accordingly") {

      val mockSRClient = mock[SchemaRegistryClient]

      val migrationService = new MigrationService( schemaRegistryClient = mockSRClient,
        changeRequestReader = mockChangeRequestReader,
        fileStatusKeeper = mockFileStatusManager)

      when(mockSRClient.url).thenCallRealMethod()

      val changeRequest1 = new SingleChangeRequest
      changeRequest1.subject = "foo"
      changeRequest1.action = "register"
      changeRequest1.data = "{\"schema\": \"{\\\"type\\\": \\\"string\\\"}\"}"

      val changeRequest2 = new SingleChangeRequest
      changeRequest2.subject = "foo"
      changeRequest2.action = "delete"
      changeRequest2.id = 2

      when(mockSRClient.addSchema(changeRequest1.subject,changeRequest1.data)).thenReturn("{\"id\":1}")
      when(mockSRClient.deleteSchema(changeRequest2.subject,changeRequest2.id)).thenReturn("{\"id\":2}")

      val mockChangeRequestIt = new MockChangeRequestIterator(List(changeRequest1, changeRequest2))

      when(mockChangeRequestReader.load).thenReturn(mockChangeRequestIt)

      migrationService.run

      verify(mockSRClient, times(1)).addSchema(changeRequest1.subject, changeRequest1.data)
      verify(mockSRClient, times(1)).deleteSchema(changeRequest2.subject, changeRequest2.id)

    }
  }

}


class MockChangeRequestIterator(requests: List[ChangeRequest]) extends Iterator[ChangeRequest] {
  val it = requests.iterator

  override def hasNext: Boolean = it.hasNext

  override def next(): ChangeRequest = it.next()
}
