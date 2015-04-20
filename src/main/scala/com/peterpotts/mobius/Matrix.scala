package com.peterpotts.mobius

case class Matrix(left: Vector, right: Vector) {
  lazy val alpha = Digit.alpha.inverse * this
  lazy val beta = Digit.beta.inverse * this
  lazy val gamma = Digit.gamma.inverse * this
  lazy val transpose = Matrix(Vector(left.top, right.top), Vector(left.bottom, right.bottom))
  lazy val determinant = left.top * right.bottom - left.bottom * right.top
  lazy val spin = determinant.signum
  lazy val inverse = Matrix(Vector(right.bottom, -left.bottom), Vector(-right.top, left.top))
  lazy val gcd = left.gcd gcd right.gcd
  lazy val normal = gcd == BigInt(1)
  lazy val normalize = if (normal) this else this / gcd
  lazy val angle = Math.abs(left.angle - right.angle)

  lazy val valid: Boolean =
    if (spin < 0)
      left.top.signum >= 0 && right.bottom.signum >= 0
    else if (spin > 0)
      left.bottom.signum >= 0 && right.top.signum >= 0
    else
      false

  lazy val pull: Option[(Matrix, Matrix)] = {
    if (alpha.valid)
      Some(Digit.alpha -> alpha)
    else if (gamma.valid)
      Some(Digit.gamma -> gamma)
    else if (beta.valid)
      Some(Digit.beta -> beta)
    else
      None
  }

  lazy val interval =
    if (spin < 0)
      Interval(left.rational, right.rational)
    else if (spin > 0)
      Interval(right.rational, left.rational)
    else
      throw new ArithmeticException("No spin")

  def *(that: BigInt): Matrix = Matrix(left * that, right * that)

  def /(that: BigInt): Matrix = Matrix(left / that, right / that)

  def *(that: Matrix): Matrix = Matrix(
    left * that.left.top + right * that.left.bottom,
    left * that.right.top + right * that.right.bottom)

  def *(that: Tensor): Tensor = Tensor(this * that.left, this * that.right)
}

object Matrix {
  val identity = Matrix(Vector(1, 0), Vector(0, 1))
}
