package com.peterpotts.common.sample

import org.scalatest.{Matchers, WordSpec}

class SampleIterableTest extends WordSpec with Matchers {
  "A sample iterable instance" should {
    "generate iterable values" in {
      val input = Iterable("flower", "tree", "grass")
      val samples = input.map(SampleLift[String])
      val output = SampleIterable(samples).next()
      output shouldEqual input
    }
  }
}
