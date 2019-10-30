import com.purbon.kafka.parsers.{MigrationClients, RoleMigration}

class CreateRoleMigration(clients: MigrationClients) extends RoleMigration(clients) {

  def up(): Unit = {
    setAclsForConsumer("User:App1", "topicA")
  }

  def down(): Unit = {
    //Add method to delete the created acls
  }
}
