package com.peterpotts.common.sample

import java.util.UUID

object SampleUUID extends Sample[UUID] {
  def next(): UUID = UUID.randomUUID
}
