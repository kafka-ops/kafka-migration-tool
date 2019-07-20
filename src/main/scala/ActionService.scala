import services.{MigrationService, Service}

object ActionService {

  def apply(action: String): Service = {

    action match {
      case "migrate" => new MigrationService()
    }
  }
}
