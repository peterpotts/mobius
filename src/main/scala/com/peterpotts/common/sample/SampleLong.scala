package com.peterpotts.common.sample

import scala.util.Random

case object SampleLong extends Sample[Long] {
  def next(): Long = Random.nextLong()

  def apply(until: Long) = SamplePick(0L until until)
}
