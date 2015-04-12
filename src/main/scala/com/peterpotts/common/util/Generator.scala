package com.peterpotts.common.util

import java.util.UUID

trait Generator {
  def uuid(): UUID
}
