package com.peterpotts.common.tool

import org.scalatest.{Matchers, WordSpec}

class CommandLineArgumentsTest extends WordSpec with Matchers {
  "A command line arguments parser" should {
    "extract options and arguments" in {
      val options = Map("a" -> "b", "c" -> "true")
      val arguments = List("d", "e")
      CommandLineArguments(Array("--a=b", "--c", "d", "e")) shouldEqual options -> arguments
    }
  }
}
