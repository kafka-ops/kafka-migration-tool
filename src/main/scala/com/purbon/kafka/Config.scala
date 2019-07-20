package com.purbon.kafka

case class Config(
                   migrationsURI: String = "migrations",
                   schemaRegistryUrl: String = "http://localhost:8081",
                   action: Option[String] = None
                 )
