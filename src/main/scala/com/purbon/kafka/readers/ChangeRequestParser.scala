package com.purbon.kafka.readers

import java.io.IOException
import java.util

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor


trait ChangeRequestParser {

  def parse(data: String): ChangeRequest
}


class ScalaChangeRequestParser extends ChangeRequestParser {
  override def parse(data: String): ChangeRequest = ???
}

class YAMLChangeRequestParser extends ChangeRequestParser {

  val singleYamlParser = new Yaml(new Constructor(classOf[SchemaRegistrySingleChangeRequest]))
  val rawYamlParser = new Yaml()

  override def parse(data: String): ChangeRequest = {
    parseYml(data);
  }

  private def parseYml(data: String): ChangeRequest = {

    extractChangeRequestType(data) match {
      case "schema-registry" => {
        parseSingleYaml(data)
      }
      case "broker" => {
        parseBrokerYaml(data)
      }
    }
  }

  private def extractChangeRequestType(data: String): String = {
    val pattern = "type:\\s+([\\w|-]+)".r
    data.split("\n")(0) match {
      case pattern(requestType) => requestType
      case _ => {
        throw new IOException("The requested change is does not contains the required type attribute")
      }
    }
  }

  private def parseBrokerYaml(data: String): BrokerChangeRequest = {
    val extractedData: util.LinkedHashMap[String, Any] = rawYamlParser.load[util.LinkedHashMap[String, Any]](data)
    BrokerChangeRequest(extractedData)
  }

  private def parseSingleYaml(data: String): SchemaRegistrySingleChangeRequest = {
    singleYamlParser.load(data).asInstanceOf[SchemaRegistrySingleChangeRequest]
  }
}
