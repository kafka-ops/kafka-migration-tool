package com.purbon.kafka.services

import java.io.IOException

import com.purbon.kafka.readers.ChangeRequestReader
import com.purbon.kafka.{ActionService, Config, FileStatusKeeper}
import org.mockito.{ArgumentMatchersSugar, MockitoSugar}
import org.scalatest.{FunSpec, MustMatchers}

class ActionServiceTest  extends FunSpec
  with MockitoSugar
  with MustMatchers
  with ArgumentMatchersSugar {

  describe ("An Action service manager") {

    val mockFileStatusManager = mock[FileStatusKeeper]
    val mockChangeRequestReader = mock[ChangeRequestReader]

    it("execute the migration task if the migrate action if received") {
      val config = Config(action = Some("migrate"))
      val task = ActionService(config, mockFileStatusManager, mockChangeRequestReader)
      task mustBe a[MigrationService]
    }

    it("execute the migration task if the migrate:up action if received") {
      val config = Config(action = Some("migrate:up"))
      val task = ActionService(config, mockFileStatusManager, mockChangeRequestReader)
      task mustBe a[MigrationService]
    }

    it("execute the clean up task if the migrate:down action if received") {
      val config = Config(action = Some("migrate:down"))
      val task = ActionService(config, mockFileStatusManager, mockChangeRequestReader)
      task mustBe a[MigrationCleanupService]
    }

    it("thrown an exception if an incorrect command is received") {
      val config = Config(action = Some("incorrectCommand"))
      an [IOException] should be thrownBy ActionService(config, mockFileStatusManager, mockChangeRequestReader)
    }

    it("thrown an exception if no command is received") {
      val config = Config()
      an [IOException] should be thrownBy ActionService(config, mockFileStatusManager, mockChangeRequestReader)
    }

  }

}
