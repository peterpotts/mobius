package com.peterpotts.common.sample

import scala.util.Random

object SampleDouble extends Sample[Double] {
  def next(): Double = Random.nextDouble()
}
