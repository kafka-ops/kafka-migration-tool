package com.purbon.kafka.clients
import java.util

import org.apache.kafka.clients.admin.AlterConfigOp.OpType
import org.apache.kafka.clients.admin._
import org.apache.kafka.common.acl.{AccessControlEntry, AclBinding, AclOperation, AclPermissionType}
import org.apache.kafka.common.config.ConfigResource
import org.apache.kafka.common.config.ConfigResource.Type
import org.apache.kafka.common.resource.{PatternType, ResourcePattern, ResourceType}
import org.apache.logging.log4j.LogManager

import scala.jdk.CollectionConverters._

class MigrationAdminClient(adminClient: AdminClient) {

  private val LOGGER = LogManager.getLogger(classOf[MigrationAdminClient])

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

  def setAclsForProducer(principal: String, topic: String): Unit = {
    val resourcePattern = new ResourcePattern(ResourceType.TOPIC, topic, PatternType.LITERAL)
    val entry = new AccessControlEntry(principal, "*", AclOperation.WRITE, AclPermissionType.ALLOW)
    createAcls(List(new AclBinding(resourcePattern, entry)))
  }

  def setAclsForConsumer(principal: String, topic: String): Unit = {
    val resourcePatternTopic = new ResourcePattern(ResourceType.TOPIC, topic, PatternType.LITERAL)
    val entryTopic = new AccessControlEntry(principal, "*", AclOperation.READ, AclPermissionType.ALLOW)
    val resourcePatternGroup = new ResourcePattern(ResourceType.GROUP, "*", PatternType.LITERAL)
    val entryGroup = new AccessControlEntry(principal, "*", AclOperation.READ, AclPermissionType.ALLOW)
    createAcls(List(
      new AclBinding(resourcePatternTopic, entryTopic),
      new AclBinding(resourcePatternGroup, entryGroup))
    )
  }

  def dump: Unit = {
    val topics: util.Set[String] = adminClient.listTopics()
      .names()
      .get

    val topicDetails: util.Map[String, TopicDescription] = adminClient.describeTopics(topics)
      .all()
      .get()

    val topicConfigs: util.Map[ConfigResource, Config] = adminClient
        .describeConfigs(topics.asScala.map( topic => new ConfigResource(ConfigResource.Type.TOPIC, topic)).asJavaCollection)
        .all()
        .get

    topics
      .asScala
      .foreach { topic =>
        val config = topicConfigs.get(new ConfigResource(ConfigResource.Type.TOPIC, topic))
        val details = topicDetails.get(topic)
        printTopic(topic, details, config)
      }

  }

  private def printTopic(topic: String, description: TopicDescription, topicConfig: Config): Unit = {
    println(topic);
    println(s"isInternal: ${description.isInternal}")
    description.partitions
      .asScala
      .foreach{ info =>
        print(s"PartitionID: ${info.partition()}")
        print(" ")
        print(s"Leader: ${info.leader().id()}")
        print(" ")
        print(s"Isr: ${info.isr.asScala.map( r => s"${r.id()}").mkString(",")}")
        print(" ")
        print(s"Replicas: ${info.replicas().asScala.map( r => s"${r.id()}").mkString(",")}")
        println()
      }
    printTopicConfig(topicConfig)
  }

  private def printTopicConfig(topicConfig: Config): Unit = {
    println("Config: ")
    topicConfig
      .entries()
      .asScala
      .foreach(println)
  }


  private def createAcls(acls: List[AclBinding]): Unit = {
    try
      adminClient
        .createAcls(acls.asJava)
        .all
        .get
    catch {
      case e: Exception =>
        LOGGER.error(e)
    }
  }
}
