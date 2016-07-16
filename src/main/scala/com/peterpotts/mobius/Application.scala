package com.peterpotts.mobius

import com.peterpotts.common.tool.CommandLineArguments
import com.peterpotts.mobius.Math._
import com.typesafe.scalalogging.LazyLogging

//noinspection ScalaStyle
object Application extends LazyLogging {
  def main(args: Array[String]): Unit = {
    arithmetic()
    println("pi = " + pi.precision(100).decimal)
    println("e = " + e.precision(100).decimal)
    println("log(e) = " + log(e).precision(100).decimal)
    val one: Digitizer = 1
    println("exp(1 / 2) = " + exp(one / 2).precision(100).decimal)
  }

  def arithmetic(): Unit = {
    println("sqrt 4 + sqrt 2 = " + (sqrt(4) + sqrt(2)).precision(100).decimal)
    println("sqrt 4 - sqrt 2 = " + (sqrt(4) - sqrt(2)).precision(100).decimal)
    println("sqrt 4 * sqrt 2 = " + (sqrt(4) * sqrt(2)).precision(100).decimal)
    println("sqrt 4 / sqrt 2 = " + (sqrt(4) / sqrt(2)).precision(100).decimal)
    println("sqrt 1 = " + sqrt(1).precision(100).decimal)
  }

  def shell(args: Array[String]): Unit = {
    val (options, _) = CommandLineArguments(args)
    val get: String => Option[String] = key => options.get(key).orElse(sys.env.get(key))
    val colors = get("colors").getOrElse("true").toBoolean
    new ApplicationShell(colors).run()
  }
}
