package com.purbon.kafka.clients

import java.util

import org.apache.kafka.clients.admin.AlterConfigOp.OpType
import org.apache.kafka.clients.admin.{AdminClient, AlterConfigOp, ConfigEntry, NewTopic}
import org.apache.kafka.common.config.ConfigResource
import org.apache.kafka.common.config.ConfigResource.Type

import scala.jdk.CollectionConverters._

class MigrationAdminClient(adminClient: AdminClient) {

  def createTopic(topicName: String, numPartitions: Int, replicationFactor: Short, config: Map[String, String] = Map.empty[String, String]): Unit = {

    val newTopic = new NewTopic(topicName, numPartitions, replicationFactor)
    if (!config.isEmpty) {
      newTopic.configs(config.asJava)
    }

    adminClient
      .createTopics(List(newTopic).asJava)
      .all
      .get
  }

  def updateConfigs(topic: String, config: Map[String, String]): Unit = {

    val newConfig: Map[ConfigResource, util.Collection[AlterConfigOp]] = config map {
      case (key, value) => {
        val resource = new ConfigResource(Type.TOPIC, topic)
        val op = new AlterConfigOp(new ConfigEntry(key, value), OpType.SET)
        (resource, List(op).asJava)
      }
    }

    adminClient
      .incrementalAlterConfigs(newConfig.asJava)
      .all()
      .get()
  }


  def deleteTopic(topicName: String): Unit = {
    adminClient
      .deleteTopics(List(topicName).asJava)
      .all
      .get
  }

}
