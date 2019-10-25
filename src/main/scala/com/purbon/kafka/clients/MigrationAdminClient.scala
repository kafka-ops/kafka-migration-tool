package com.purbon.kafka.clients

import org.apache.kafka.clients.admin.{AdminClient, NewTopic}
import scala.jdk.CollectionConverters._

class MigrationAdminClient(adminClient: AdminClient) {

  def createTopic(topicName: String, numPartitions: Int, replicationFactor: Short): Unit = {

    val newTopic = new NewTopic(topicName, numPartitions, replicationFactor)
    adminClient
      .createTopics(List(newTopic).asJava)
      .all
      .get
  }

  def deleteTopic(topicName: String): Unit = {
    adminClient
      .deleteTopics(List(topicName).asJava)
      .all
      .get
  }

}
