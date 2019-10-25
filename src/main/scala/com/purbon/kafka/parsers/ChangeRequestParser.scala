package com.purbon.kafka.parsers

import com.purbon.kafka.SchemaRegistryClient
import com.purbon.kafka.clients.MigrationAdminClient

import scala.reflect.runtime.universe
import scala.tools.reflect.ToolBox

trait ChangeRequestParser {

  def parse(data: String): ChangeRequest
}


class ScalaChangeRequestParser(client: SchemaRegistryClient,
                               adminClient: MigrationAdminClient) extends ChangeRequestParser {

  val tb = universe.runtimeMirror(getClass.getClassLoader).mkToolBox()

  override def parse(data: String): ChangeRequest = {

    val parsed: tb.u.Tree = tb.parse(data)
    tb.compile(parsed).apply()
      .asInstanceOf[Class[Migration]]
      .getConstructor(classOf[MigrationClients])
      .newInstance(new MigrationClients(client, adminClient))


  }
}

