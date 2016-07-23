package com.peterpotts.mobius

import com.peterpotts.common.tool.CommandLineArguments
import com.peterpotts.mobius.Mobius._
import com.typesafe.scalalogging.LazyLogging

//noinspection ScalaStyle
object Application extends LazyLogging {
  def main(args: Array[String]): Unit = {
    arithmetic()
    println("pi = " + pi.decimal(100))
    println("e = " + e.decimal(100))
    println("log(e) = " + dZeroLog(new UnsignedVectorReal(Vector(99,100))).decimal(100))
//    println("log(e) = " + log(e).decimal(100))
    println("exp(1 / 2) = " + exp(Vector(1, 2)).decimal(100))

    val in = Matrix(Vector(2719, 1000), Vector(2718, 1000))
    println(in.decimal)
    val x = e isMemberOf in
    println("x = " + x)
    val out = Matrix(Vector(2718, 1000), Vector(2717, 1000))
    println(out.decimal)
    val y = e isMemberOf out
    println("y = " + y)
  }

  def arithmetic(): Unit = {
    println("sqrt 4 + sqrt 2 = " + (sqrt(4) + sqrt(2)).decimal(100))
    println("sqrt 4 - sqrt 2 = " + (sqrt(4) - sqrt(2)).decimal(100))
    println("sqrt 4 * sqrt 2 = " + (sqrt(4) * sqrt(2)).decimal(100))
    println("sqrt 4 / sqrt 2 = " + (sqrt(4) / sqrt(2)).decimal(100))
    println("sqrt 1 = " + sqrt(1).decimal(100))
  }

  def shell(args: Array[String]): Unit = {
    val (options, _) = CommandLineArguments(args)
    val get: String => Option[String] = key => options.get(key).orElse(sys.env.get(key))
    val colors = get("colors").getOrElse("true").toBoolean
    new ApplicationShell(colors).run()
  }
}
