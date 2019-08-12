package com.purbon.kafka.readers


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

class BrokerTopicConfig {
  @BeanProperty var numPartitions: Int = 1
  @BeanProperty var replicationFactor: Short = 1

}

class BrokerChangeRequest extends ChangeRequest {
  @BeanProperty var topic: String = ""
  @BeanProperty var config: BrokerTopicConfig = new BrokerTopicConfig
}
