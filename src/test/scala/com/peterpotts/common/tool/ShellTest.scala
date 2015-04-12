package com.peterpotts.common.tool

import org.scalatest.{Matchers, WordSpec}

import scala.collection.mutable

class ShellTest extends WordSpec with Matchers {

  class TargetShell extends Shell {
    val inputs = mutable.Queue.empty[String]
    val outputs = mutable.Queue.empty[String]
    val promptColor = "p"
    val inputColor = "i"
    val outputColor = "o"
    val resetColor = "r"
    val name = "a"
    val help = List("b", "c")
    val buffer = mutable.Queue.empty[String]

    def inputReadLine(): String = inputs.dequeue()

    def outputPrint(text: String): Unit = buffer enqueue text

    def outputPrintln(text: String): Unit = {
      buffer enqueue (text + "\n")
      outputFlush()
    }

    def outputFlush(): Unit = outputs.enqueue(buffer.dequeueAll(_ => true): _*)

    def process(input: String): List[String] =
      if (input == "e")
        throw new RuntimeException("f")
      else
        List(input + input)
  }

  def withFixture(test: TargetShell => Any): Unit = test(new TargetShell)

  "A shell trait" should {
    "provide a controlled exit option" in withFixture {
      shell =>
        shell.inputs enqueue "exit"
        shell.run()
        shell.outputs shouldEqual mutable.Queue("pa> i", "r")
    }

    "provide a help option" in withFixture {
      shell =>
        shell.inputs enqueue "help"
        shell.inputs enqueue "exit"
        shell.run()
        shell.outputs shouldEqual mutable.Queue("pa> i", "o", "\n", "b\n", "c\n", "help\n", "exit\n", "pa> i", "r")
    }

    "provide an empty line option" in withFixture {
      shell =>
        shell.inputs enqueue ""
        shell.inputs enqueue "exit"
        shell.run()
        shell.outputs shouldEqual mutable.Queue("pa> i", "o", "pa> i", "r")
    }

    "provide a valid input processing option" in withFixture {
      shell =>
        shell.inputs enqueue "d"
        shell.inputs enqueue "exit"
        shell.run()
        shell.outputs shouldEqual mutable.Queue("pa> i", "o", "dd\n", "pa> i", "r")
    }

    "provide an invalid input processing option" in withFixture {
      shell =>
        shell.inputs enqueue "e"
        shell.inputs enqueue "exit"
        shell.run()
        shell.outputs shouldEqual mutable.Queue("pa> i", "o", "Error: f\n", "pa> i", "r")
    }
  }
}
