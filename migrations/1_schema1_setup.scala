import com.purbon.kafka.readers._

class SchemaSetupMigration extends SchemaMigration {

  def up(): Unit = {
    val schema = Map( "schema" -> Map( "type" -> "string ") )
    register("kafka-key2", schema)
  }

  def down(): Unit = {
    println("down");
    remove("kafka-key2")
  }
}
scala.reflect.classTag[SchemaSetupMigration].runtimeClass