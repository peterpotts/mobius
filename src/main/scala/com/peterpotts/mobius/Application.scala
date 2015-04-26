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
    //val result = new FixedTensorDigitizer(Operation.times, sqrt4, sqrt2)
    //println(result.digits(1000).min.rational)


    //println(new FixedMatrixDigitizer(Matrix.identity, sqrt2).digits(6).min.rational)

    //val result2 = new VariableTensorDigitizer(Operation.times, sqrt4, sqrt2)
    //println(new FixedMatrixDigitizer(Matrix.identity, result2).digits(400).min.rational)

    def em(n: Int) = Matrix(Vector(2 * n + 2, 2 * n + 1), Vector(2 * n + 1, 2 * n))
    def en(n: Int): Digitizer = new VariableMatrixDigitizer(em(n), en(n + 1))
    val e = new FixedMatrixDigitizer(em(0), en(1))
    println(e.digits(1000).min.rational)

    def expt(n: Int) = Tensor(
      Matrix(Vector(2 * n + 2, 2 * n + 1), Vector(2 * n + 1, 2 * n)),
      Matrix(Vector(2 * n, 2 * n + 1), Vector(2 * n + 1, 2 * n + 2)))

    def expn(n: Int, x: Digitizer): Digitizer = new FixedTensorDigitizer(expt(n), x, expn(n+1,x))

    def exp(x: Digitizer) = expn(0, new FixedMatrixDigitizer(Digit.s0.inverse, x))

    //println(exp(sqrt2).digits(400).min.rational)

    val result = new FixedTensorDigitizer(Operation.times, sqrt4, sqrt2)
    println(result.digits(100).min.rational)
  }

  def main2(args: Array[String]): Unit = {
    val (options, _) = CommandLineArguments(args)
    val get: String => Option[String] = key => options.get(key).orElse(sys.env.get(key))
    val colors = get("colors").getOrElse("true").toBoolean
    new ApplicationShell(colors).run()
  }
}
