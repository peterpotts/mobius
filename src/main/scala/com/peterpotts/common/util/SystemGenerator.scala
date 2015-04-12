package com.peterpotts.common.util

import java.util.UUID

object SystemGenerator extends Generator {
  def uuid() = UUID.randomUUID()
}
