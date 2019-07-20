package com.purbon.kafka.readers

import scala.beans.BeanProperty

trait ChangeRequest {
  var name: String = ""
}

class SingleChangeRequest extends ChangeRequest {


  @BeanProperty var action: String = ""
  @BeanProperty var subject: String = ""
  @BeanProperty var data: String = ""
  @BeanProperty var down: String = ""
  @BeanProperty var id: Int = -1

}


class GroupChangeRequest extends ChangeRequest {

  @BeanProperty var actions: Array[SingleChangeRequest] = Array[SingleChangeRequest]()

}