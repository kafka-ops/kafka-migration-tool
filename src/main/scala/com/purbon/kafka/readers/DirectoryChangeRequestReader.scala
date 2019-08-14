package com.purbon.kafka.readers

import java.io.File


object DirectoryChangeRequestReader {

  def apply(directory:String): DirectoryChangeRequestReader = {
    val dir = new File(directory)
    val iterator = new FSMigrationReaderIterator(dir.listFiles()
                                                    .sortBy(_.getAbsolutePath)
                                                    .iterator)
    new DirectoryChangeRequestReader(iterator)
  }
}

class DirectoryChangeRequestReader(var fileSystemMigrationIterator: FSMigrationReaderIterator) extends ChangeRequestReader {

  def this(directory: String) {
    this(new FSMigrationReaderIterator(new File(directory).listFiles().sortBy(_.getAbsolutePath).iterator))
  }

  override def iterator: Iterator[ChangeRequest] = fileSystemMigrationIterator
}
