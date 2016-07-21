package com.peterpotts.mobius

import scala.math.BigDecimal.RoundingMode

case class Matrix(left: Vector, right: Vector) {
  lazy val transpose = Matrix(Vector(left.top, right.top), Vector(left.bottom, right.bottom))
  lazy val determinant = left.top * right.bottom - left.bottom * right.top
  lazy val spin = determinant.signum
  lazy val inverse = Matrix(Vector(right.bottom, -left.bottom), Vector(-right.top, left.top))
  lazy val complement = Matrix(Vector(right.top, right.bottom), Vector(-left.top, -left.bottom))
  lazy val gcd = left.gcd gcd right.gcd
  lazy val normal = gcd == BigInt(1)
  lazy val normalize = if (normal) this else this / gcd
  lazy val magnitude = math.abs(left.magnitude - right.magnitude)
  lazy val (min, max) = if (spin < 0) (left, right) else (right, left)

  lazy val valid = min.signum >= 0 && max.signum >= 0
  lazy val isPositive = left.signum == right.signum && right.signum != 0
  lazy val info = Matrix(max, min)
  lazy val empty = info.isPositive == info.inverse.isPositive

  lazy val decimal = {
    val lower = min.decimal
    val upper = max.decimal
    val diff = upper - lower
    lower.setScale(math.min(lower.precision, upper.precision) - diff.precision, RoundingMode.HALF_UP)
  }

  def isSubsetOf(that: Matrix): Boolean = (that.inverse * this).isPositive

  def *(that: BigInt): Matrix = Matrix(left * that, right * that)

  def /(that: BigInt): Matrix = Matrix(left / that, right / that)

  def *(that: Vector): Vector = Vector(
    left.top * that.top + right.top * that.bottom,
    left.bottom * that.top + right.bottom * that.bottom)

  def *(that: Matrix): Matrix = Matrix(this * that.left, this * that.right)

  def *(that: Tensor): Tensor = Tensor(this * that.left, this * that.right)
}

object Matrix {
  val identity = Matrix(Vector(1, 0), Vector(0, 1))
  val negation = Matrix(Vector(-1, 0), Vector(0, 1))
  val reciprocal = Matrix(Vector(0, 1), Vector(1, 0))
}
