package com.purbon.kafka

import java.io._
import java.time.Instant

case class ApplicationState(lastMigration: String = "", executionTime: Instant=Instant.now())

class StateManager(handler: StateHandler) {

  def print: Unit = println(state)

  private var state: ApplicationState = ApplicationState()

  def updateLastMigration(newLastMigrationApplied: String): Unit = {
    state = state.copy(lastMigration = newLastMigrationApplied)
  }

  def updateExecutionTime(time: Instant): Unit = {
    state = state.copy(executionTime = time)
  }


  def load : ApplicationState = {
    state = handler.load
    state
  }

  def save : Unit = {
    handler.save(state)
  }

}

trait StateHandler {

  def load: ApplicationState
  def save(state: ApplicationState): Unit

}

class LocalStateHandler(file: String = ".kafka-migration-tool.status") extends StateHandler {

  def load: ApplicationState = {
    createIfDoesNotExist(file)
    val ois = new ObjectInputStream(new FileInputStream(file))
    val storedCache = ois.readObject.asInstanceOf[ApplicationState]
    ois.close
    storedCache
  }

  def save(state: ApplicationState) : Unit = {
    val oos = new ObjectOutputStream(new FileOutputStream(file))
    oos.writeObject(state)
    oos.close
  }

  private def createIfDoesNotExist(filename: String): Unit = {
    val file = new File(filename)
    if (!file.exists()) {
      file.createNewFile()
      save(ApplicationState())
    }
  }

}