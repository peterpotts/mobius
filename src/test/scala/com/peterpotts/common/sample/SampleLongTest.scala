package com.peterpotts.common.sample

import org.scalatest.{Matchers, WordSpec}

class SampleLongTest extends WordSpec with Matchers {
  "A sample long instance" should {
    "generate long values" in {
      SampleLong.next() should (be >= Long.MinValue and be <= Long.MaxValue)
    }

    "generate limited long values" in {
      SampleLong(5L).next() should (be >= 0L and be < 5L)
    }
  }
}
