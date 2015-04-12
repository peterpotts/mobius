package com.peterpotts.common.sample

import scala.util.Random

object SampleBoolean extends Sample[Boolean] {
  def next(): Boolean = Random.nextBoolean()
}
