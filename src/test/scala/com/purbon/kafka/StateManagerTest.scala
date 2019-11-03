package com.purbon.kafka

import java.io.File
import java.time.Instant

import org.mockito.{ArgumentMatchersSugar, MockitoSugar}
import org.scalatest.{FunSpec, Matchers}

class StateManagerTest  extends FunSpec
  with Matchers
  with MockitoSugar
  with ArgumentMatchersSugar {

  describe("An state manager") {

    val mockHandler = mock[LocalStateHandler]
    val stateManager = new StateManager(handler = mockHandler)

    it ("should load status from the state handler") {
      stateManager.load
      verify(mockHandler, times(1)).load
    }

    it ("should save status with the state handler") {
      stateManager.save
      verify(mockHandler, times(1)).save(any[ApplicationState])
    }

  }

  describe("A local state manager") {

    it ("should retrieve the last updated state and time") {

      val tmpStateFile = File.createTempFile("app", ".state")

      val localStateHandler = new LocalStateHandler(tmpStateFile.getAbsolutePath)
      val stateManager = new StateManager(handler = localStateHandler)

      val lastMigrationName = "foo.scala"
      val timestamp = Instant.now

      stateManager.updateLastMigration(newLastMigrationApplied =  lastMigrationName)
      stateManager.updateExecutionTime(timestamp)
      stateManager.save

      val actualState = stateManager.load
      actualState shouldBe ApplicationState(lastMigration = lastMigrationName, executionTime = timestamp)

    }

  }
}