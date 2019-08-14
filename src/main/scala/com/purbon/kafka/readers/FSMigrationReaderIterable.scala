package com.purbon.kafka.readers

import java.io.File

import scala.io.Source

class FSMigrationReaderIterator(fileIterator: Iterator[File]) extends Iterator[ChangeRequest]
                                                              with ChangeRequestParser {
  override def hasNext: Boolean = fileIterator.hasNext

  override def next(): ChangeRequest = {
    val nextFile = fileIterator.next()
    val changeRequest : ChangeRequest = parseYml(Source.fromFile(nextFile).getLines.mkString("\n"))
    changeRequest.name = nextFile.getName
    changeRequest
  }

}

class MigrationReaderIterable(migrationReaderIterator: Iterator[ChangeRequest]) extends Iterable[ChangeRequest] {
  override def iterator: Iterator[ChangeRequest] = migrationReaderIterator
}
