package com.purbon.kafka.parsers


final case class MigrationParsingException(ex: Exception) extends Exception(ex) {

  def this(message: String) {
    this(new Exception(message))
  }
}