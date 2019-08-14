package com.purbon.kafka.readers

import org.scalatest.{FunSpec, Matchers}
import org.scalatestplus.mockito.MockitoSugar


class ChangeRequestParserTest  extends FunSpec
  with Matchers
  with MockitoSugar {

  class DummyChangeRequestParser extends ChangeRequestParser {

  }
  describe("A change request parser") {

    val reader = new DummyChangeRequestParser

    it("should be able to extract request types from migration description files") {

      var changeRequestContentForBroker = "type: broker\naction: create-topic\ntopic: foo"
      reader.parseYml(changeRequestContentForBroker) shouldBe a [BrokerChangeRequest]

      var changeRequestContentForSR = "type: schema-registry\naction: register"
      reader.parseYml(changeRequestContentForSR) shouldBe a [SchemaRegistrySingleChangeRequest]

    }

    describe("A broker change request reader") {

      it ("should parse configuration parameters") {

        val changeRequest = "type: broker\naction: create-topic\ntopic: foo\nconfig:\n  numPartitions: 1\n  replicationFactor: 1"

        reader.parseYml(changeRequest) match {
          case brokerRequest: BrokerChangeRequest => {
            brokerRequest.numPartitions shouldBe 1
            brokerRequest.replicationFactor shouldBe 1
          }
          case _ => {
            fail("Config could not be parsed")
          }
        }
      }

      it ("should parse open config parameters") {

        val changeRequest = "type: broker\naction: create-topic\ntopic: foo\nconfig:\n  foo: bar"

        reader.parseYml(changeRequest) match {
          case brokerRequest: BrokerChangeRequest => {
            brokerRequest.foo() shouldBe "bar"
          }
          case _ => {
            fail("Config could not be parsed")
          }
        }

      }

    }
  }

}