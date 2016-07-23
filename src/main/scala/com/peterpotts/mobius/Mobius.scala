package com.peterpotts.mobius

import scala.language.implicitConversions

object Mobius {
  implicit def intToReal(n: Int): Real = bigIntToReal(n)

  implicit def bigIntToReal(n: BigInt): Real = vectorToReal(Vector(n, 1))

  implicit def vectorToReal(vector: Vector): Real = new UnsignedVectorReal(vector)

  def sqrt(n: BigInt): Real = {
    def oneTo(n: BigInt): Matrix = {
      val guess = BigInt(0).setBit(n.bitLength / 2)
      Matrix(Vector(guess, 1), Vector(n, guess))
    }

    val otn = oneTo(n)
    lazy val sn: Real = new FreestyleMatrixReal(otn, sn)
    sn
  }

  val pi: Real = {
    val q0 = Matrix(Vector(6795705, 213440), Vector(6795704, 213440))

    def q(n: BigInt) = {
      val cn = (2 * n - 1) * (6 * n - 5) * (6 * n - 1) * (545140134 * n + 13591409)
      val dn = (2 * n - 1) * (6 * n - 5) * (6 * n - 1) * (n + 1)
      val en = BigInt("10939058860032000") * n * n * n * n
      Matrix(Vector(en - dn - cn, en + dn + cn), Vector(en + dn - cn, en - dn + cn))
    }

    def qs(n: BigInt): Real = new UnsignedMatrixReal(q(n), qs(n + 1))

    val phi = new UnsignedMatrixReal(q0, qs(1))
    sqrt(10005) / phi
  }

  val e: Real = {
    def m(n: BigInt) = Matrix(Vector(2 * n + 2, 2 * n + 1), Vector(2 * n + 1, 2 * n))
    def ms(n: BigInt): Real = new UnsignedMatrixReal(m(n), ms(n + 1))
    ms(0)
  }

  def log(x: Real): Real = {
    def t(n: BigInt) = {
      if (n == 0)
        Tensor(Matrix(Vector(1, 0), Vector(1, 1)), Matrix(Vector(-1, 1), Vector(-1, 0)))
      else
        Tensor(
          Matrix(Vector(n, 0), Vector(2 * n + 1, n + 1)),
          Matrix(Vector(n + 1, 2 * n + 1), Vector(0, n)))
    }

    def ts(n: BigInt, x: Real): Real = new UnsignedTensorReal(t(n), x, ts(n + 1, x))
    ts(0, x)
  }

  def exp(x: Real): Real = {
    def t(n: BigInt) =
      Tensor(
        Matrix(Vector(2 * n + 2, 2 * n + 1), Vector(2 * n + 1, 2 * n)),
        Matrix(Vector(2 * n, 2 * n + 1), Vector(2 * n + 1, 2 * n + 2)))

    def ts(n: BigInt, x: Real): Real = new UnsignedTensorReal(t(n), x, ts(n + 1, x))
    ts(0, new UnsignedMatrixReal(Digit.sZero.inverse, x))
  }
}
