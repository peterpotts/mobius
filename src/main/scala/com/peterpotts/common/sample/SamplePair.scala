package com.peterpotts.common.sample

object SamplePair {
  def apply[A, B](sampleA: Sample[A], sampleB: Sample[B]): Sample[(A, B)] = for {
    a <- sampleA
    b <- sampleB
  } yield (a, b)
}
