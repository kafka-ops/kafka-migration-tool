package com.purbon.kafka.readers

import org.scalatest.{FunSpec, Matchers}
import org.scalatestplus.mockito.MockitoSugar


class DirectoryChangeRequestReaderTest  extends FunSpec
  with Matchers
  with MockitoSugar {

  describe("A change request reader") {

    val reader = new DirectoryChangeRequestReader("foo")

    it("should be able to extract request types from migration description files") {

      var changeRequestContentForBroker = "type: broker\naction: create-topic\ntopic: foo"
      reader.parseYml(changeRequestContentForBroker) shouldBe a [BrokerChangeRequest]

      var changeRequestContentForSR = "type: schema-registry\naction: register"
      reader.parseYml(changeRequestContentForSR) shouldBe a [SchemaRegistrySingleChangeRequest]

    }

    describe("A broker change request reader") {

      it ("shuold parse configuration parameters") {

        val changeRequest = "type: broker\naction: create-topic\ntopic: foo\nconfig:\n  numPartitions: 1\n  replicationFactor: 1"

        reader.parseYml(changeRequest) match {
          case brokerRequest: BrokerChangeRequest => {
            brokerRequest.config.numPartitions shouldBe 1
            brokerRequest.config.replicationFactor shouldBe 1
          }
          case _ => {
            fail("Config could not be parsed")
          }
        }

      }

    }
  }

}