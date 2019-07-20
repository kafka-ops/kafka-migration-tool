package com.purbon.kafka.services

import com.purbon.kafka.SchemaRegistryClient

class CleanService(schemaRegistryClient: SchemaRegistryClient) extends Service {
  override def run: Unit = {
    println("Running the Clean service")
  }
}
