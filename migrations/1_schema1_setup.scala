class SchemaSetupMigration extends SchemaMigration {

  def up(): Unit = {
    val schema = Map( "schema" -> Map( "type" -> "string ") )
    register("kafka-key2", schema)
  }

  def down(): Unit = {
    System.out.println("down");
    remove("kafka-key2")
  }
}