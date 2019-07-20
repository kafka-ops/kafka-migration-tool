package com.purbon.kafka

import java.io.IOException

import com.softwaremill.sttp._

class SchemaRegistryClient(val url: String="http://localhost:8081") {

  private val contentType = "application/vnd.schemaregistry.v1+json"
  implicit val backend: SttpBackend[Id, Nothing] = HttpURLConnectionBackend()

  def addSchema(subject: String, data: String): String = {

    val request: RequestT[Id, String, Nothing] = sttp.post(uri = uri"${url}/subjects/${subject}/versions")
      .contentType(contentType)
      .body(data)

    parseResponse(request.send(), "addSchema")
  }

  def deleteSchema(subject: String, version: Int): String = {
    val request: Request[String, Nothing] = sttp.delete(uri = uri"${url}/subjects/${subject}/versions/${version}")
    parseResponse(request.send(), "deleteSchema")
  }

  def testCompatibility(subject: String, data: String): String = {
    val request: RequestT[Id, String, Nothing] = sttp.post(uri = uri"${url}/compatibility/subjects/${subject}/versions/latest" )
      .contentType(contentType)
      .body(data)

    parseResponse(request.send(), "testCompatibility")
  }

  def getTopLevelCompatibility(): String = {
    val request: Request[String, Nothing] = sttp.get(uri = uri"${url}/config")
    parseResponse(request.send(), "getTopLevelCompatibility")
  }

  def setTopLevelCompatibility(data: String): String = {
    val request = sttp.put(uri = uri"${url}/config")
      .contentType(contentType)
      .body(data)
    parseResponse(request.send(), "setTopLevelCompatibility")
  }

  private def parseResponse(response: Id[Response[String]], op: String): String = {

    if (!response.isSuccess) {
      println(s"Error while processing schema op ${op} request. Error code: ${response.statusText}, ${response.code}")
    }

    response.body match {
      case Left(left) => {
        throw new IOException(s"Error while processing schema op ${op} request, with ${left}")
      }
      case Right(right) => right
    }
  }

}
