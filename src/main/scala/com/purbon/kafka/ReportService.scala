package com.purbon.kafka

import com.purbon.kafka.clients.MigrationAdminClient
import com.purbon.kafka.services.Service

object ReportService {

  def apply(schemaRegistryClient: SchemaRegistryClient, kafkaAdminClient: MigrationAdminClient): Service = {
    new ReportService(schemaRegistryClient = schemaRegistryClient, kafkaAdminClient = kafkaAdminClient)
  }
}

class ReportService(schemaRegistryClient: SchemaRegistryClient, kafkaAdminClient: MigrationAdminClient) extends Service {
  override def run: Unit = {
    println("Schema Registry dump: ")
    schemaRegistryClient.dump
    println("Kafka cluster dump: ")
    kafkaAdminClient.dump
  }
}
