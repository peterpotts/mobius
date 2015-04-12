package com.peterpotts.common.sample

import scala.util.Random

class SampleShuffle[T](samples: Sample[T]*) extends Sample[T] {
  def next(): T = samples(Random.nextInt(samples.size)).next()
}

object SampleShuffle {
  def apply[T](samples: Sample[T]*) = new SampleShuffle(samples: _*)
}
