package com.purbon.kafka.readers


import java.util
import scala.language.dynamics
import scala.beans.BeanProperty

class ChangeRequest {
  @BeanProperty var name: String = ""
  @BeanProperty var `type`: String = ""
  @BeanProperty var action: String = ""
  @BeanProperty var down: String = ""
}

class SchemaRegistrySingleChangeRequest extends ChangeRequest {
  @BeanProperty var subject: String = ""
  @BeanProperty var data: String = ""
  @BeanProperty var id: Any = ""

}

class BrokerChangeRequest extends ChangeRequest with Dynamic {
  @BeanProperty var topic: String = ""

  var config: util.LinkedHashMap[String, Any] = new util.LinkedHashMap[String, Any]

  def numPartitions: Int = config.get("numPartitions").asInstanceOf[Int]

  def replicationFactor: Int = config.get("replicationFactor").asInstanceOf[Int]

  def applyDynamic(name: String)(args: Any*): String = {
    config.get(name).asInstanceOf[String]
  }
}

object BrokerChangeRequest {

  def apply(extractedData: util.LinkedHashMap[String, Any]): BrokerChangeRequest = {
    val changeRequest = new BrokerChangeRequest
    changeRequest.setTopic(extractedData.get("topic").asInstanceOf[String])
    changeRequest.`type` = extractedData.get("type").asInstanceOf[String]
    changeRequest.setAction(extractedData.get("action").asInstanceOf[String])
    changeRequest.config = extractedData.get("config").asInstanceOf[util.LinkedHashMap[String, Any]]
    changeRequest
  }
}
