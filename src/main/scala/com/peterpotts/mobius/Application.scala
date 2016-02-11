package com.peterpotts.mobius

import com.peterpotts.common.tool.CommandLineArguments
import com.typesafe.scalalogging.LazyLogging

//noinspection ScalaStyle
object Application extends LazyLogging {
  def main(args: Array[String]): Unit = {
    val oneTo: BigInt => Matrix = n => Matrix(Vector(1, 1), Vector(n, 1))

    val oneTo2 = oneTo(2)

    val oneTo4 = oneTo(4)

    def sqrt2: Digitizer = new VariableMatrixDigitizer(oneTo2, sqrt2)
    def sqrt4: Digitizer = new VariableMatrixDigitizer(oneTo4, sqrt4)

    val result: Digitizer = new FixedTensorDigitizer(Operation.minus, sqrt4, sqrt2)

    println("Sqrt 2 = " + sqrt2.precision(100).min.rational)
    println("Sqrt 4 = " + sqrt4.precision(100).min.rational)

    println("Sqrt 4 - Sqrt 2 = " + result.precision(100).min.rational)

    println(Operation.minus * Matrix(Vector(2, 1), Vector(2, 1)) ** Matrix(Vector(1, 2), Vector(1, 2)))
    //
    //
    //    val x = Operation.divide ** sqrt4.precision(100) * sqrt2.precision(100)
    //
    //    val y = new FixedMatrixDigitizer(Matrix.identity, x)
    //    println(x.min.rational)
  }

  def main2(args: Array[String]): Unit = {

    //println(new FixedMatrixDigitizer(Matrix.identity, sqrt2).digits(6).min.rational)

    //val result2 = new VariableTensorDigitizer(Operation.times, sqrt4, sqrt2)
    //println(new FixedMatrixDigitizer(Matrix.identity, result2).digits(400).min.rational)

    def em(n: Int) = Matrix(Vector(2 * n + 2, 2 * n + 1), Vector(2 * n + 1, 2 * n))
    def en(n: Int): Digitizer = new VariableMatrixDigitizer(em(n), en(n + 1))
    val e = new FixedMatrixDigitizer(em(0), en(1))
    //println(e.precision(1000).min.rational)

    def expt(n: Int) = Tensor(
      Matrix(Vector(2 * n + 2, 2 * n + 1), Vector(2 * n + 1, 2 * n)),
      Matrix(Vector(2 * n, 2 * n + 1), Vector(2 * n + 1, 2 * n + 2)))

    def expn(n: Int, x: Digitizer): Digitizer = new FixedTensorDigitizer(expt(n), x, expn(n + 1, x))

    def exp(x: Digitizer) = expn(0, x)

    //println(exp(sqrt4).precision(400).min.rational)

    //val result = new FixedTensorDigitizer(Operation.times, sqrt4, sqrt2)
    //println(result.precision(100).min.rational)
  }

  def main3(args: Array[String]): Unit = {
    val (options, _) = CommandLineArguments(args)
    val get: String => Option[String] = key => options.get(key).orElse(sys.env.get(key))
    val colors = get("colors").getOrElse("true").toBoolean
    new ApplicationShell(colors).run()
  }
}
