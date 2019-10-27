import com.purbon.kafka.parsers.{MigrationClients, TopicMigration}

class CreateTopicMigration(clients: MigrationClients) extends TopicMigration(clients) {

  def up(): Unit = {
    createTopic("foo", 1, 1)
  }

  def down(): Unit = {
    deleteTopic("foo")
  }
}
