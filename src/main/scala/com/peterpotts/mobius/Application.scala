package com.peterpotts.mobius

import com.peterpotts.common.tool.CommandLineArguments
import com.typesafe.scalalogging.slf4j.LazyLogging

object Application extends LazyLogging {
  def main(args: Array[String]): Unit = {
    def oneTo(n: BigInt) = Matrix(Vector(1, 1), Vector(n, 1))
    val oneTo2 = oneTo(2)
    val oneTo4 = oneTo(4)
    def sqrt2: Digitizer = new VariableMatrixDigitizer(oneTo2, sqrt2)
    def sqrt4: Digitizer = new VariableMatrixDigitizer(oneTo4, sqrt4)
    val result = new FixedTensorDigitizer(Operation.times, sqrt4, sqrt2)
    println(result.digits(1000).min.rational)


    println(new FixedMatrixDigitizer(Matrix.identity, sqrt2).digits(1000).min.rational)

    val result2 = new VariableTensorDigitizer(Operation.times, sqrt4, sqrt2)
    println(new FixedMatrixDigitizer(Matrix.identity, result2).digits(100).min.rational)

  }

  def main2(args: Array[String]): Unit = {
    val (options, _) = CommandLineArguments(args)
    val get: String => Option[String] = key => options.get(key).orElse(sys.env.get(key))
    val colors = get("colors").getOrElse("true").toBoolean
    new ApplicationShell(colors).run()
  }
}
