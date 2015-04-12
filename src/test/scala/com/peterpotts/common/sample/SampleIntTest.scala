package com.peterpotts.common.sample

import org.scalatest.{Matchers, WordSpec}

class SampleIntTest extends WordSpec with Matchers {
  "A sample int instance" should {
    "generate int values" in {
      SampleInt.next() should (be >= Int.MinValue and be <= Int.MaxValue)
    }

    "generate limited int values" in {
      SampleInt(10).next() should (be >= 0 and be < 10)
    }
  }
}
