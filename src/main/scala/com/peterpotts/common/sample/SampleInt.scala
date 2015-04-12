package com.peterpotts.common.sample

import scala.util.Random

class SampleInt(until: Int) extends SamplePick(0 until until)

object SampleInt extends Sample[Int] {
  def next(): Int = Random.nextInt()

  def apply(until: Int) = new SampleInt(until)
}
