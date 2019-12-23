name := "kafka-migration-tool"

version := "0.0.1"
scalaVersion := "2.13.0"

enablePlugins(JavaAppPackaging)
enablePlugins(UniversalPlugin)

val appMainClass = "com.purbon.kafka.CliTool"

libraryDependencies += "org.scala-lang" % "scala-compiler" % "2.13.0"
libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.13.0"

libraryDependencies += "com.github.scopt" %% "scopt" % "4.0.0-RC2"
libraryDependencies += "com.softwaremill.sttp" %% "core" % "1.6.3"
libraryDependencies += "org.yaml" % "snakeyaml" % "1.24"

libraryDependencies += "org.json4s" %% "json4s-native" % "3.6.7"
libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.6.7"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test
libraryDependencies += "org.scalamock" %% "scalamock" % "4.3.0" % Test
libraryDependencies += "org.mockito" %% "mockito-scala-scalatest" % "1.5.12" % Test

libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.12.1"
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.12.1"
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.3.0"

libraryDependencies += "org.antlr" % "antlr4-runtime" % "4.7.2"



mainClass in assembly := Some(appMainClass)
mainClass in Compile := Some(appMainClass)

discoveredMainClasses in Compile := Seq()

unmanagedSourceDirectories in Compile += baseDirectory.value / "src" / "main" / "antlr"

maintainer := "purbon@acm.org"

lazy val excludes = jacocoExcludes in Test  :=Seq(
  s"${appMainClass}*",
  "com.purbon.kafka.clients.*"
)

lazy val jacoco = jacocoReportSettings in test  :=JacocoReportSettings(
  "Jacoco Coverage Report",
  None,
  JacocoThresholds (branch = 100),
  Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.CSV),
  "utf-8")

val jacocoSettings = Seq(jacoco, excludes)
lazy val jse = (project in file (".")).settings(jacocoSettings: _*)