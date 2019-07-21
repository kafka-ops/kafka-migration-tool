name := "kafka-migration-tool"

version := "0.0.1"

scalaVersion := "2.13.0"

libraryDependencies += "com.github.scopt" %% "scopt" % "4.0.0-RC2"
libraryDependencies += "com.softwaremill.sttp" %% "core" % "1.6.3"
libraryDependencies += "org.yaml" % "snakeyaml" % "1.24"

libraryDependencies += "org.json4s" %% "json4s-native" % "3.6.7"
libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.6.7"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test
libraryDependencies += "org.scalamock" %% "scalamock" % "4.3.0" % Test
libraryDependencies += "org.mockito" %% "mockito-scala-scalatest" % "1.5.12" % Test


mainClass in assembly := Some("com.purbon.kafka.KafkaMigrationToolCLI")
