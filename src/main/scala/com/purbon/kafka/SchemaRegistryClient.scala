package com.purbon.kafka

import com.purbon.kafka.clients.HttpClient

object SchemaRegistryClient {
  val DEFAULT_BASE_URL = "http://localhost:8081"
  val contentType = "application/vnd.schemaregistry.v1+json"
}

class SchemaRegistryClient(val url:String = SchemaRegistryClient.DEFAULT_BASE_URL,
                           val httpClient:HttpClient = new HttpClient) {

  def addSchema(subject: String, data: String): String = {
    val request = httpClient.post(uri = s"${url}/subjects/${subject}/versions",
      contentType = SchemaRegistryClient.contentType,
      body = data)
    httpClient.parseRequest(request, "addSchema")
  }

  def deleteSchema(subject: String, version: Int): String = {
    val request = httpClient.delete(s"${url}/subjects/${subject}/versions/${version}")
    httpClient.parseRequest(request, "deleteSchema")
  }

  def testCompatibility(subject: String, data: String): String = {
    val request = httpClient.post(uri = s"${url}/compatibility/subjects/${subject}/versions/latest",
      contentType = SchemaRegistryClient.contentType,
      body = data)
    httpClient.parseRequest(request, "testCompatibility")
  }

  def getTopLevelCompatibility(): String = {
    val request = httpClient.get(s"${url}/config")
    httpClient.parseRequest(request, "getTopLevelCompatibility")

  }

  def setTopLevelCompatibility(data: String): String = {
    val request = httpClient.put(uri = s"${url}/config",
      contentType = SchemaRegistryClient.contentType,
      body = data
    )

    httpClient.parseRequest(request, "setTopLevelCompatibility")
  }
}
