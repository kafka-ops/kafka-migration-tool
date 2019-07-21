package com.purbon.kafka.clients

import java.io.IOException

import com.softwaremill.sttp._

class HttpClient {

  implicit val backend: SttpBackend[Id, Nothing] = HttpURLConnectionBackend()

  def post(uri: String, contentType: String, body: String): RequestT[Id, String, Nothing] = {
    sttp.post(uri = uri"${uri}")
      .contentType(contentType)
      .body(body)
  }

  def delete(uri: String): Request[String, Nothing] = {
    sttp.delete(uri = uri"${uri}")
  }

  def put(uri: String, contentType: String, body: String): RequestT[Id, String, Nothing] = {
    sttp.put(uri = uri"${uri}")
      .contentType(contentType)
      .body(body)
  }

  def get(uri: String): Request[String, Nothing] = {
    sttp.get(uri = uri"${uri}/config")
  }

  def parseRequest(request: RequestT[Id, String, Nothing], op: String): String = {
    val response = request.send()
    parseResponse(response, op)
  }

  def parseResponse(response: Id[Response[String]], op: String): String = {
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
