import com.purbon.kafka.parsers.{MigrationClients, RolesMigration}

class CreateRoleMigration(clients: MigrationClients) extends RolesMigration(clients) {

  def up(): Unit = {
    setAclsForConsumer("User:App1", "topicA")
  }

  def down(): Unit = {
    //Add method to delete the created acls
  }
}
