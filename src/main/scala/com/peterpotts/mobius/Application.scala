package com.peterpotts.mobius

import com.peterpotts.common.tool.CommandLineArguments
import com.typesafe.scalalogging.LazyLogging

//noinspection ScalaStyle
object Application extends LazyLogging {
  def main(args: Array[String]): Unit = {
    fixedArithmetic()
    variableArithmetic()
    pi()
  }

  def fixedArithmetic(): Unit = {
    val oneTo: BigInt => Matrix = n => Matrix(Vector(1, 1), Vector(n, 1))
    val oneTo2 = oneTo(2)
    val oneTo4 = oneTo(4)

    def sqrt2: Digitizer = new FixedMatrixDigitizer(oneTo2, sqrt2)
    def sqrt4: Digitizer = new FixedMatrixDigitizer(oneTo4, sqrt4)

    println("Sqrt 4 + Sqrt 2 = " + new FixedTensorDigitizer(Tensor.plus, sqrt4, sqrt2).precision(100).min.rational)
    println("Sqrt 4 - Sqrt 2 = " + new FixedTensorDigitizer(Tensor.minus, sqrt4, sqrt2).precision(100).min.rational)
    println("Sqrt 4 * Sqrt 2 = " + new FixedTensorDigitizer(Tensor.times, sqrt4, sqrt2).precision(100).min.rational)
    println("Sqrt 4 / Sqrt 2 = " + new FixedTensorDigitizer(Tensor.divide, sqrt4, sqrt2).precision(100).min.rational)
  }

  def variableArithmetic(): Unit = {
    val oneTo: BigInt => Matrix = n => Matrix(Vector(1, 1), Vector(n, 1))
    val oneTo2 = oneTo(2)
    val oneTo4 = oneTo(4)

    def sqrt2: Digitizer = new VariableMatrixDigitizer(oneTo2, sqrt2)
    def sqrt4: Digitizer = new VariableMatrixDigitizer(oneTo4, sqrt4)

    println("Sqrt 4 + Sqrt 2 = " + new VariableTensorDigitizer(Tensor.plus, sqrt4, sqrt2).precision(100).min.rational)
    println("Sqrt 4 - Sqrt 2 = " + new VariableTensorDigitizer(Tensor.minus, sqrt4, sqrt2).precision(100).min.rational)
    println("Sqrt 4 * Sqrt 2 = " + new VariableTensorDigitizer(Tensor.times, sqrt4, sqrt2).precision(100).min.rational)
    println("Sqrt 4 / Sqrt 2 = " + new VariableTensorDigitizer(Tensor.divide, sqrt4, sqrt2).precision(100).min.rational)
  }

  def pi(): Unit = {
    def oneTo(n: BigInt): Matrix = {
      val guess = BigInt(0).setBit(n.bitLength / 2)
      Matrix(Vector(guess, 1), Vector(n, guess))
    }

    def sqrt(n: BigInt): Digitizer = new VariableMatrixDigitizer(oneTo(n), sqrt(n))

    def divide(x: Digitizer, y: Digitizer) = new VariableTensorDigitizer(Tensor.divide, x, y)

    val q0 = Matrix(Vector(6795705, 213440), Vector(6795704, 213440))

    def q(n: BigInt) = {
      val cn = (2 * n - 1) * (6 * n - 5) * (6 * n - 1) * (545140134 * n + 13591409)
      val dn = (2 * n - 1) * (6 * n - 5) * (6 * n - 1) * (n + 1)
      val en = BigInt("10939058860032000") * n * n * n * n
      Matrix(Vector(en - dn - cn, en + dn + cn), Vector(en + dn - cn, en - dn + cn))
    }

    def sq(n: BigInt): Digitizer = new FixedMatrixDigitizer(q(n), sq(n + 1))

    val phi = new FixedMatrixDigitizer(q0, sq(1))
    val pi = divide(sqrt(10005), phi)

    println("Sqrt 10005 = " + sqrt(10005).precision(100).min.rational)
    println("Pi = " + pi.precision(100).min.rational)
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
