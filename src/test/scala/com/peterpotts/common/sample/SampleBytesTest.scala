package com.peterpotts.common.sample

import org.scalatest.{Matchers, WordSpec}

class SampleBytesTest extends WordSpec with Matchers {
  "A sample bytes instance" should {
    "generate default byte arrays" in {
      SampleBytes.next().length == defaultSampleSize
    }

    "generate limited byte arrays" in {
      SampleBytes(42).next().length == 42
    }
  }

}
