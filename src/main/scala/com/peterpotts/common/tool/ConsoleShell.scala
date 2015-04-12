package com.peterpotts.common.tool

import scala.io.StdIn

/**
 * A partial implementation of shell that is tied directly to standard input and standard output.
 */
trait ConsoleShell extends Shell {
  val colors: Boolean
  val promptColor = if (colors) Console.GREEN else ""
  val inputColor = if (colors) Console.RED else ""
  val outputColor = if (colors) Console.BLUE else ""
  val resetColor = if (colors) Console.RESET else ""

  def inputReadLine(): String = StdIn.readLine()

  def outputPrint(text: String): Unit = print(text)

  def outputPrintln(text: String): Unit = println(text)

  def outputFlush(): Unit = Console.flush()
}
