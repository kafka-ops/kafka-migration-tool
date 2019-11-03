import com.purbon.kafka.parsers.{MigrationClients, SchemaMigration}

class SchemaSetupMigration(clients: MigrationClients) extends SchemaMigration(clients) {

  def up(): Unit = {
    val schema = """
        |{"schema": "{\"type\": \"string\"}"}
      """.stripMargin

    register("kafka-key2", schema)
  }

  def down(): Unit = {
    remove("kafka-key2", "latest")
  }
}
