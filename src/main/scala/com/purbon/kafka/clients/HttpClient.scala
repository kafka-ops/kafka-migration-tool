package com.purbon.kafka.clients

import java.io.IOException

import com.softwaremill.sttp._

class HttpClient {

  implicit val backend: SttpBackend[Id, Nothing] = HttpURLConnectionBackend()

  // post(uri: Uri, ...) perhaps?
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

  // I think it should be the only public method here. Otherwise, I'm not getting why you need
  // get / put / delete / post methods. You kind of do not want to expose http library outside of this class
  // but RequestT and Id leak anyway.
  //
  // def parseRequest(request: RequestT[Id, String, Nothing], op: String): Try[String] = {
  def parseRequest(request: RequestT[Id, String, Nothing], op: String): String = {
    val response = request.send()
    parseResponse(response, op)
  }

  // private def parseResponse(response: Id[Response[String]], op: String): Try[String] = {
  // The parser should not handle exceptions here imo; it's not his job.
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
