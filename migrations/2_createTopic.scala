import com.purbon.kafka.parsers.{MigrationClients, TopicMigration}

class CreateTopicMigration(clients: MigrationClients) extends TopicMigration(clients) {

  def up(): Unit = {
    createTopic("foo", 1, 1)
    updateConfig("foo", Map("retention.ms" -> "1000"))
  }

  def down(): Unit = {
    deleteTopic("foo")
  }
}
