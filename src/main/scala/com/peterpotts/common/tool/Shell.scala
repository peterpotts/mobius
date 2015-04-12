package com.peterpotts.common.tool

import java.io.IOException

import scala.util.Try

/**
 * A general command processor that drives the output from the input and provides infrastructure such as exit and help.
 */
trait Shell {
  val name: String
  val help: List[String]
  val promptColor: String
  val inputColor: String
  val outputColor: String
  val resetColor: String

  def inputReadLine(): String

  def outputPrint(text: String): Unit

  def outputPrintln(text: String): Unit

  def outputFlush(): Unit

  def process(input: String): List[String]

  def prompt(): Unit = {
    outputPrint(promptColor + name + "> " + inputColor)
    outputFlush()
  }

  def run(): Boolean = {
    case object ExitException extends Exception("Exit")

    def read(): String = Option(inputReadLine()).map(_.trim).getOrElse(throw new IOException("End of stream"))

    def parse(input: String): List[String] = input match {
      case "exit" => throw ExitException
      case "help" => "" :: help ++ List("help", "exit")
      case "" => Nil
      case _ => Try(process(input)).recover {
        case exception: Exception => List("Error: " + exception.getMessage)
      }.get
    }

    def write(output: List[String]): Unit = {
      outputPrint(outputColor)
      output foreach outputPrintln
      prompt()
    }

    try {
      prompt()
      Iterator.continually(read()).map(parse).foreach(write)
      throw ExitException
    } catch {
      case _: IOException =>
        outputPrintln("")
        outputPrint(resetColor)
        outputFlush()
        true
      case ExitException =>
        outputPrint(resetColor)
        outputFlush()
        false
    }
  }
}
