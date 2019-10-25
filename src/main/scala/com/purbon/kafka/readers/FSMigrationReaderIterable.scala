package com.purbon.kafka.readers

import java.io.File

import scala.io.Source

class FSMigrationReaderIterator(fileIterator: Iterator[File], crp: ChangeRequestParser)
  extends Iterator[ChangeRequest] {

  override def hasNext: Boolean = fileIterator.hasNext

  override def next(): ChangeRequest = {
    val nextFile = fileIterator.next()
    val changeRequest : ChangeRequest = crp.parse(Source.fromFile(nextFile).getLines.mkString("\n"))
    changeRequest.name = nextFile.getName
    changeRequest
  }

}

class MigrationReaderIterable(migrationReaderIterator: Iterator[ChangeRequest]) extends Iterable[ChangeRequest] {
  override def iterator: Iterator[ChangeRequest] = migrationReaderIterator
}
