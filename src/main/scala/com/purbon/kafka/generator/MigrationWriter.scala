package com.purbon.kafka.generator

import java.io.{File, PrintWriter}
import java.nio.file.Paths

trait MigrationWriter {

  def flush(filename: String, content: String): String
}

class MigrationFileWriter(path: String) extends MigrationWriter {

  override def flush(filename: String, content: String): String = {
    val fullPath = Paths.get(path, filename)
    val pw = new PrintWriter(new File(fullPath.toString))
    pw.print(content)
    pw.close()
    filename
  }
}
