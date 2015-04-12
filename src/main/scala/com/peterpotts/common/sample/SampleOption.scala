package com.peterpotts.common.sample

class SampleOption[A](sampleA: Sample[A]) extends FlatMapSample[Boolean, Option[A]](SampleBoolean, { boolean =>
  for (a <- sampleA) yield if (boolean) None else Some(a)
})

object SampleOption {
  def apply[A](sampleA: Sample[A]) = new SampleOption(sampleA)
}
