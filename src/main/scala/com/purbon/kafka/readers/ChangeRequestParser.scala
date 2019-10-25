package com.purbon.kafka.readers

import scala.reflect.runtime.universe
import scala.tools.reflect.ToolBox

trait ChangeRequestParser {

  def parse(data: String): ChangeRequest
}


class ScalaChangeRequestParser extends ChangeRequestParser {

  val tb = universe.runtimeMirror(getClass.getClassLoader).mkToolBox()

  override def parse(data: String): ChangeRequest = {

    val parsed: tb.u.Tree = tb.parse(data)
    val clazz = tb.compile(parsed).apply().asInstanceOf[Class[Migration]]
    clazz.getConstructor().newInstance()

  }
}

