package com.peterpotts.common.sample

import org.scalatest.{Matchers, WordSpec}

class SampleLiftTest extends WordSpec with Matchers {
  "A sample lift instance" should {
    "generate lifted values" in {
      SampleLift("stone").next() shouldEqual "stone"
    }
  }
}
