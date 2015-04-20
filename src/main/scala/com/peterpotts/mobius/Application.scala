package com.peterpotts.mobius

import com.peterpotts.common.tool.CommandLineArguments
import com.typesafe.scalalogging.slf4j.LazyLogging

object Application extends LazyLogging {
  def main(args: Array[String]): Unit = {
    def oneTo(n: BigInt) = Matrix(Vector(1, 1), Vector(n, 1))
    val oneTo2 = oneTo(2)
    val oneTo4 = oneTo(4)
    def sqrt2: Chain = new Chain(oneTo2, sqrt2)
    def sqrt4: Chain = new Chain(oneTo4, sqrt4)

    val result = new Tee(Operation.times, sqrt4, sqrt2)
    println(Tee.interval(result, 1000))
  }

  def main2(args: Array[String]): Unit = {
    val (options, _) = CommandLineArguments(args)
    val get: String => Option[String] = key => options.get(key).orElse(sys.env.get(key))
    val colors = get("colors").getOrElse("true").toBoolean
    new ApplicationShell(colors).run()
  }
}
