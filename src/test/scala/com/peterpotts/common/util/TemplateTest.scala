package com.peterpotts.common.util

import org.scalatest.{Matchers, WordSpec}

class TemplateTest extends WordSpec with Matchers {
  "A template" should {
    "parse" in {
      val expected = "A rose is red"
      val actual = Template("A <flower> is <color>") parse Map("flower" -> "rose", "color" -> "red")
      actual shouldEqual expected
    }
  }
}
