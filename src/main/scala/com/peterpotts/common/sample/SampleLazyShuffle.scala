package com.peterpotts.common.sample

import scala.util.Random

class SampleLazyShuffle[T](sample1: => Sample[T], sample2: => Sample[T]) extends Sample[T] {
  def next(): T = if (Random.nextBoolean()) sample1.next() else sample2.next()
}

object SampleLazyShuffle {
  def apply[T](sample1: => Sample[T], sample2: => Sample[T]) = new SampleLazyShuffle(sample1, sample2)
}
