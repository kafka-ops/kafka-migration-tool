package com.purbon.kafka.readers

import java.io.{File, IOException}

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

import scala.io.Source

class DirectoryChangeRequestReader(directory: String) extends ChangeRequestReader {

  val singleYamlParser = new Yaml(new Constructor(classOf[SchemaRegistrySingleChangeRequest]))
  val brokerYamlParser = new Yaml(new Constructor(classOf[BrokerChangeRequest]))


  class MigrationDirectoryReaderIterator(fileIterator: Iterator[File]) extends Iterator[ChangeRequest] {
    override def hasNext: Boolean = fileIterator.hasNext

    override def next(): ChangeRequest = {
      val nextFile = fileIterator.next()
      val changeRequest = parseYml(Source.fromFile(nextFile).getLines.mkString("\n"))
      changeRequest.name = nextFile.getName
      changeRequest
    }
  }

  def load: Iterator[ChangeRequest] = {
    val dir = new File(directory)
    new MigrationDirectoryReaderIterator(dir
      .listFiles()
      .sortBy(_.getAbsolutePath)
      .iterator)
  }

  def parseYml(data: String): ChangeRequest = {

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
    brokerYamlParser.load(data).asInstanceOf[BrokerChangeRequest]
  }

  private def parseSingleYaml(data: String): SchemaRegistrySingleChangeRequest = {
    singleYamlParser.load(data).asInstanceOf[SchemaRegistrySingleChangeRequest]
  }
}
