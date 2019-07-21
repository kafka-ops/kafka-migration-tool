package com.purbon.kafka.readers

import java.io.File

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import org.yaml.snakeyaml.error.YAMLException

import scala.io.Source

class DirectoryChangeRequestReader(directory: String) extends ChangeRequestReader {

  val singleYamlParser = new Yaml(new Constructor(classOf[SingleChangeRequest]))
  val groupYamlParser = new Yaml(new Constructor(classOf[GroupChangeRequest]))

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

  private def parseYml(data: String): ChangeRequest = {
    try {
      parseSingleYaml(data)
    } catch {
      case e: YAMLException => parseGroupYaml(data)
    }
  }

  private def parseSingleYaml(data: String): SingleChangeRequest = {
    singleYamlParser.load(data).asInstanceOf[SingleChangeRequest]
  }

  private def parseGroupYaml(data: String): GroupChangeRequest = {
     groupYamlParser.load(data).asInstanceOf[GroupChangeRequest]
  }
}
