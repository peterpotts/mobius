package com.peterpotts.common.sample

import org.scalatest.{Matchers, WordSpec}

class SampleBooleanTest extends WordSpec with Matchers {
  "A sample boolean instance" should {
    "generate boolean values" in {
      SampleBoolean.next() should (equal(true) or equal(false))
    }
  }
}
