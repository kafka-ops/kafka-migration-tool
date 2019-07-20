import java.io.IOException

import scopt.OParser


case class Config( migrate: Boolean = false, clean : Boolean = false, action: Option[String] = None )

object KafkaMigrationToolCLI {


  def main(args: Array[String]): Unit = {

    OParser.parse(parser, args, Config()) match {
      case Some(config) => {
        config.action match {
          case Some(actionName: String) => ActionService(actionName).run
          case _ => throw new IOException("Wrong action requested")
        }
      }
      case _ => {
        //TODO fill if ever necessary
      }
    }
  }

  private def parser: OParser[Unit, Config] = {

    val builder = OParser.builder[Config]

    {

      import builder._

      OParser.sequence(
        programName("Kafka Migration Tool"),
        opt[Boolean]('m', "migrate")
          .action((x, c) => c.copy(migrate = true))
          .text("migrate is a boolean property"),
        opt[Boolean]('c', "clean")
          .action((x, c) => c.copy(clean = true))
          .text("clean is a boolean property"),
        cmd("migrate")
          .action( (_,c) => c.copy(action = Some("migrate") ) )
          .text("migrate command"),
        cmd("clean")
          .action( (_,c) => c.copy(action = Some("clean") ) )
          .text("clean command"),

      )
    }
  }
}
