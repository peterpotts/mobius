package com.peterpotts.common.sample

class SampleLift[A](a: A) extends MapSample[Unit, A](SampleUnit, _ => a)

object SampleLift {
  def apply[A](a: A): Sample[A] = new SampleLift(a)
}
