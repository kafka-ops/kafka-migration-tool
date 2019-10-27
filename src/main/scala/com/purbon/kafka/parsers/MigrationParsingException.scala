package com.purbon.kafka.parsers


final case class MigrationParsingException(private val message: String = "")
  extends Exception(message)