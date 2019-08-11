package com.purbon.kafka

import com.purbon.kafka.clients.HttpClient
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSpec

class SchemaRegistryClientTest extends FunSpec
  with MockFactory {

  describe("a SchemaRegistry client") {
    val httpClient = mock[HttpClient]
    val client = new SchemaRegistryClient(httpClient = httpClient)
    val bodyOfData = "data"
    val schemaSubject = "foo"

    it ("should build a valid POST request for adding a new Schema") {

      val uri = s"${SchemaRegistryClient.DEFAULT_BASE_URL}/subjects/${schemaSubject}/versions"

      (httpClient.post _).expects(uri, SchemaRegistryClient.contentType, bodyOfData).once()
      (httpClient.parseRequest _).expects(*,*).once()

      client.addSchema(subject = schemaSubject, data = bodyOfData)
    }

    it ("should build a valid DELETE request to remove an Schema") {
      val uri = s"${SchemaRegistryClient.DEFAULT_BASE_URL}/subjects/${schemaSubject}/versions/1"

      (httpClient.delete _).expects(uri).once()
      (httpClient.parseRequest _).expects(*,*).once()
      client.deleteSchema(subject = schemaSubject, version = "1")
    }

    it ("should build a valid POST request for testing an Schema compatibility") {

      val uri = s"${SchemaRegistryClient.DEFAULT_BASE_URL}/compatibility/subjects/${schemaSubject}/versions/latest"

      (httpClient.post _).expects(uri, SchemaRegistryClient.contentType, bodyOfData).once()
      (httpClient.parseRequest _).expects(*,*).once()

      client.testCompatibility(subject = schemaSubject, data = bodyOfData)
    }

    it ("should build a valid GET request to check the top global compatibility") {

      val uri = s"${SchemaRegistryClient.DEFAULT_BASE_URL}/config"

      (httpClient.get _).expects(uri).once()
      (httpClient.parseRequest _).expects(*,*).once()

      client.getTopLevelCompatibility()
    }

    it ("should build a valid PUT request for setting top global compatibility") {

      val uri = s"${SchemaRegistryClient.DEFAULT_BASE_URL}/config"

      (httpClient.put _).expects(uri, SchemaRegistryClient.contentType, bodyOfData).once()
      (httpClient.parseRequest _).expects(*,*).once()

      client.setTopLevelCompatibility(bodyOfData)
    }

  }

}
