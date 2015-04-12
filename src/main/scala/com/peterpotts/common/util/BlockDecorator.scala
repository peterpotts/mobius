package com.peterpotts.common.util

import scala.reflect.ClassTag
import scala.util.control.NonFatal

object BlockDecorator {

  implicit class DecoratedBlock[T](block: => T) {
    def rethrow(exception: => Exception): T = try block catch {
      case NonFatal(_) => throw exception
    }

    def rethrow[E <: Exception : ClassTag](constructor: E => Exception): T = try block catch {
      case cause: E => throw constructor(cause)
    }
  }

}
