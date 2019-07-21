package com.purbon.kafka

import java.io._

import org.json4s.JValue

import scala.collection.immutable.HashMap

case class StatusUpdate(data: String, responseJSON: JValue)

class FileStatusKeeper {
  def print: Unit = println(cache)

  val file = ".kafka-migration-tool.status"

  private var cache: Map[String, List[StatusUpdate]] = HashMap.empty[String, List[StatusUpdate]]

  def update(subject: String, action: String, data: String, responseJSON: JValue): Unit = {
    val changeLogList: List[StatusUpdate] = (cache get subject) getOrElse(List.empty[StatusUpdate])
    action match {
      case "register" => {
        val updatedChangeLogList = changeLogList .:: (StatusUpdate(data, responseJSON))
        cache += (subject -> updatedChangeLogList)
      }
      case "delete" => {
        val updatedChangeLogList = changeLogList.filter { statusUpdate =>
            statusUpdate.responseJSON.equals(responseJSON)
        }
        cache += (subject -> updatedChangeLogList)
      }
      case _ => {
        // IGNORED
      }
    }
  }


  def load : Unit = {
    createIfDoesNotExist(file)
    val ois = new ObjectInputStream(new FileInputStream(file))
    val storedCache = ois.readObject.asInstanceOf[Map[String, List[StatusUpdate]]]
    ois.close
    cache = storedCache
  }

  def save : Unit = {
    val oos = new ObjectOutputStream(new FileOutputStream(file))
    oos.writeObject(cache)
    oos.close
  }

  private def createIfDoesNotExist(filePath: String): Unit = {
    val file = new File(filePath)
    if (!file.exists()) {
      file.createNewFile()
      save
    }
  }
}
