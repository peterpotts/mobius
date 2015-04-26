package com.peterpotts.mobius

case class Matrix(left: Vector, right: Vector) {
  lazy val transpose = Matrix(Vector(left.top, right.top), Vector(left.bottom, right.bottom))
  lazy val determinant = left.top * right.bottom - left.bottom * right.top
  lazy val spin = determinant.signum
  lazy val inverse = Matrix(Vector(right.bottom, -left.bottom), Vector(-right.top, left.top))
  lazy val gcd = left.gcd gcd right.gcd
  lazy val normal = gcd == BigInt(1)
  lazy val normalize = if (normal) this else this / gcd
  lazy val range = Math.abs(left.range - right.range)
  lazy val (min, max) = if (spin < 0) (left, right) else (right, left)
  lazy val valid = min.top.signum >= 0 && max.bottom.signum >= 0

  def *(that: BigInt): Matrix = Matrix(left * that, right * that)

  def /(that: BigInt): Matrix = Matrix(left / that, right / that)

  def *(that: Matrix): Matrix = Matrix(
    left * that.left.top + right * that.left.bottom,
    left * that.right.top + right * that.right.bottom)

  def *(that: Tensor): Tensor = Tensor(this * that.left, this * that.right)

  def <*>(that: Tensor): Tensor = (this * that).normalize
}

object Matrix {
  val identity = Matrix(Vector(1, 0), Vector(0, 1))
}
