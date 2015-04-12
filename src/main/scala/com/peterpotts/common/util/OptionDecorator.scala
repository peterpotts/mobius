package com.peterpotts.common.util

object OptionDecorator {

  implicit class DecoratedOption[T](option: Option[T]) {
    def getOrThrow(exception: => Exception): T = option.getOrElse(throw exception)
  }

}
