package com.peterpotts.mobius

import com.peterpotts.common.tool.ConsoleShell

class ApplicationShell(val colors: Boolean) extends ConsoleShell {
  val name = "Mobius"

  val help =
    "java -Dlog.path=<logs> <class>" ::
      "" ::
      "Usage:" ::
      Nil

  override def process(input: String): List[String] = Nil
}
