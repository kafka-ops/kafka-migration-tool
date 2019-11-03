package com.purbon.kafka.generator

import java.io.IOException

import org.mockito.{ArgumentMatchersSugar, MockitoSugar}
import org.scalatest.{FunSpec, Matchers}

class MigrationGeneratorTest  extends FunSpec
  with MockitoSugar
  with Matchers
  with ArgumentMatchersSugar {

  describe ("An Migration generator") {

    val migrationFileWriter = new MigrationFileWriter(path = System.getProperty("java.io.tmpdir"))

    it("should flush a valid schema migration if given the selected type") {
      val filename = MigrationGenerator.generate(MigrationGenerator.schemaMigration, migrationFileWriter)
      filename should endWith("_SchemaMigration.scala")
    }

    it("should flush a valid topic migration if given the selected type") {
      val filename = MigrationGenerator.generate(MigrationGenerator.topicMigration, migrationFileWriter)
      filename should endWith("_TopicMigration.scala")
    }

    it("should flush a valid access migration if given the selected type") {
      val filename = MigrationGenerator.generate(MigrationGenerator.accessMigration, migrationFileWriter)
      filename should endWith("_AccessMigration.scala")
    }

    it("should raise an exception if an invalid type is requested") {
      an [IOException] should be thrownBy MigrationGenerator.generate("invalidType", migrationFileWriter)
    }

  }

}
