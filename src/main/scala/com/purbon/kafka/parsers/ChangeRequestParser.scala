package com.purbon.kafka.parsers

import com.purbon.kafka.SchemaRegistryClient

import scala.reflect.runtime.universe
import scala.tools.reflect.ToolBox

trait ChangeRequestParser {

  def parse(data: String): ChangeRequest
}


class ScalaChangeRequestParser(client: SchemaRegistryClient) extends ChangeRequestParser {

  val tb = universe.runtimeMirror(getClass.getClassLoader).mkToolBox()

  override def parse(data: String): ChangeRequest = {

    val parsed: tb.u.Tree = tb.parse(data)
    val clazz = tb.compile(parsed).apply().asInstanceOf[Class[Migration]]
    clazz.getConstructor(classOf[SchemaRegistryClient]).newInstance(client)

  }
}

