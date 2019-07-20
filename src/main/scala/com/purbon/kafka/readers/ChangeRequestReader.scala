package com.purbon.kafka.readers

trait ChangeRequestReader {

  def load : Iterator[ChangeRequest]
}
