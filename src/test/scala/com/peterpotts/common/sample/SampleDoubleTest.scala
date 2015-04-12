package com.peterpotts.common.sample

import org.scalatest.{Matchers, WordSpec}

class SampleDoubleTest extends WordSpec with Matchers {
  "A sample double instance" should {
    "generate double values" in {
      SampleDouble.next() should (be >= Double.MinValue and be <= Double.MaxValue)
    }
  }
}
