package com.purbon.kafka

case class Config(
                   migrationsURI: String = "migrations",
                   brokersUrl: String = "localhost:9092",
                   schemaRegistryUrl: String = "http://localhost:8081",
                   action: Option[String] = None,
                   migrationType: Option[String] = None
                 )
