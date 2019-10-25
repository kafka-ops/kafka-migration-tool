import com.purbon.kafka.SchemaRegistryClient
import com.purbon.kafka.parsers.SchemaMigration

class SchemaSetupMigration(client: SchemaRegistryClient) extends SchemaMigration(client) {

  def up(): Unit = {
    val schema = Map( "schema" -> Map( "type" -> "string ") )
    register("kafka-key2", schema)
  }

  def down(): Unit = {
    remove("kafka-key2", "latest")
  }
}
scala.reflect.classTag[SchemaSetupMigration].runtimeClass