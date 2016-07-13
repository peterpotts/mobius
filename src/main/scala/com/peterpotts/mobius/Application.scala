package com.peterpotts.mobius

import com.peterpotts.common.tool.CommandLineArguments
import com.typesafe.scalalogging.LazyLogging

//noinspection ScalaStyle
object Application extends LazyLogging {
  def main(args: Array[String]): Unit = {
    arithmetic()
    pi()
  }

  import Math.sqrt

  def arithmetic(): Unit = {
    println("Sqrt 4 + Sqrt 2 = " + (sqrt(4) + sqrt(2)).precision(100).decimal)
    println("Sqrt 4 - Sqrt 2 = " + (sqrt(4) - sqrt(2)).precision(100).decimal)
    println("Sqrt 4 * Sqrt 2 = " + (sqrt(4) * sqrt(2)).precision(100).decimal)
    println("Sqrt 4 / Sqrt 2 = " + (sqrt(4) / sqrt(2)).precision(100).decimal)
  }

  def pi(): Unit = println("Pi = " + Math.pi.precision(100).decimal)

  def shell(args: Array[String]): Unit = {
    val (options, _) = CommandLineArguments(args)
    val get: String => Option[String] = key => options.get(key).orElse(sys.env.get(key))
    val colors = get("colors").getOrElse("true").toBoolean
    new ApplicationShell(colors).run()
  }
}
