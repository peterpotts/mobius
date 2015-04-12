package com.peterpotts.common.util

object BooleanDecorator {

  implicit class DecoratedBoolean(boolean: Boolean) {
    def condOpt[T](value: => T): Option[T] = if (boolean) Some(value) else None
  }

}

