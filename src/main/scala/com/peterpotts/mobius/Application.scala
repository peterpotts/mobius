package com.peterpotts.mobius

import com.peterpotts.common.tool.CommandLineArguments
import com.typesafe.scalalogging.slf4j.LazyLogging

object Application extends LazyLogging {
  def main(args: Array[String]): Unit = {
    val x = Matrix(Vector(1, 1), Vector(2, 1))
    def sqrt2: Chain = new Chain(x, sqrt2)

    println(Chain.interval(sqrt2, 500))

    val result = new Tee(Operation.times, sqrt2, sqrt2)
    println(Tee.interval(result, 500))
  }

  def main2(args: Array[String]): Unit = {
    val (options, _) = CommandLineArguments(args)
    val get: String => Option[String] = key => options.get(key).orElse(sys.env.get(key))
    val colors = get("colors").getOrElse("true").toBoolean
    new ApplicationShell(colors).run()
  }
}
