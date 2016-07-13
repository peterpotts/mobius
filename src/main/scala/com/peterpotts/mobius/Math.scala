package com.peterpotts.mobius

object Math {
  def sqrt(n: BigInt): Digitizer = {
    def oneTo(n: BigInt): Matrix = {
      val guess = BigInt(0).setBit(n.bitLength / 2)
      Matrix(Vector(guess, 1), Vector(n, guess))
    }

    new VariableMatrixDigitizer(oneTo(n), sqrt(n))
  }

  val pi: Digitizer = {
    val q0 = Matrix(Vector(6795705, 213440), Vector(6795704, 213440))

    def q(n: BigInt) = {
      val cn = (2 * n - 1) * (6 * n - 5) * (6 * n - 1) * (545140134 * n + 13591409)
      val dn = (2 * n - 1) * (6 * n - 5) * (6 * n - 1) * (n + 1)
      val en = BigInt("10939058860032000") * n * n * n * n
      Matrix(Vector(en - dn - cn, en + dn + cn), Vector(en + dn - cn, en - dn + cn))
    }

    def qs(n: BigInt): Digitizer = new FixedMatrixDigitizer(q(n), qs(n + 1))

    val phi = new FixedMatrixDigitizer(q0, qs(1))
    sqrt(10005) / phi
  }
}
