package com.purbon.kafka

import java.io.File
import java.nio.channels.{FileChannel, FileLock}
import java.nio.file.{Paths, StandardOpenOption}

case class CLIFileLock(fileChannel: FileChannel, fileLock: FileLock)
object ExecutionLock {

  val defaultFileLockPath = Paths.get(".lock")

  def acquireLock: CLIFileLock = {
    val fileLockChannel = FileChannel.open(defaultFileLockPath, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW)
    val fileLock = fileLockChannel.tryLock();
    CLIFileLock(fileLockChannel, fileLock)
  }

  def releaseLock(lock: CLIFileLock): Unit = {
    lock.fileLock.release();
    lock.fileChannel.close()
    new File(defaultFileLockPath.getFileName.toString).delete()
  }
}
