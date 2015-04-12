package com.peterpotts.common.sample

import org.scalatest.{Matchers, WordSpec}

class SampleUnitTest extends WordSpec with Matchers {
  "A sample unit instance" should {
    "generate unit values" in {
      SampleUnit.next() shouldEqual ((): Unit)
    }
  }
}
