package com.purbon.kafka

import java.nio.file.{Files, Paths}
import java.time.Instant

import org.mockito.{ArgumentMatchersSugar, MockitoSugar}
import org.scalatest.{FunSpec, Matchers}

class LocalStateHandlerTest  extends FunSpec
  with Matchers
  with MockitoSugar
  with ArgumentMatchersSugar {

  describe("A local state handler") {

    val tmpDirPath = System.getProperty("java.io.tmpdir")

    it ("should load an application state from a local file") {
      val tmpStateFile = Paths.get(tmpDirPath, "my-file.state")
      val localStateHandler = new LocalStateHandler(file = tmpStateFile.toString)
      localStateHandler.load
      val timestamp = Instant.now
      localStateHandler.save(ApplicationState(lastMigration = "foo.scala", executionTime = timestamp))
      val actualState = localStateHandler.load
      actualState shouldBe ApplicationState(lastMigration = "foo.scala", executionTime = timestamp)
    }

    it ("should create an empty file if it does not exist") {
      val tmpStateFile = Paths.get(tmpDirPath, "not-existant-file.state")
      Files.deleteIfExists(tmpStateFile)
      val localStateHandler = new LocalStateHandler(file = tmpStateFile.toString)
      localStateHandler.load
      Files.exists(tmpStateFile) shouldBe true
    }


  }


}