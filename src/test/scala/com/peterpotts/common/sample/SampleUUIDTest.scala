package com.peterpotts.common.sample

import org.scalatest.{Matchers, WordSpec}

class SampleUUIDTest extends WordSpec with Matchers {
  "A sample UUID instance" should {
    "generate UUID values" in {
      SampleUUID.next() == SampleUUID.next() shouldEqual false
    }
  }
}
