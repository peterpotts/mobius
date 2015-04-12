package com.peterpotts.common.sample

import org.scalatest.{Matchers, WordSpec}

class SamplePickTest extends WordSpec with Matchers {
  "A sample pick instance" should {
    "pick values" in {
      val animals = IndexedSeq("cat", "dog")
      val animal = SamplePick(animals).next()
      animals.contains(animal) shouldEqual true
    }
  }
}
