package com.peterpotts.common.util

object JavaNull {

  implicit class DecorateWithNotNull[T](value: T) {
    def notJavaNull: T = {
      require(value != null, "Must be not null")
      value
    }

    def isJavaNull: Boolean = value == null

    def isNotJavaNull: Boolean = value != null
  }

}
