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
  //lazy val valid = min.top.signum >= 0 && max.bottom.signum >= 0
  lazy val valid = min.signum >= 0 && max.signum >= 0
  lazy val unsigned = left.signum == right.signum && right.signum != 0

  def *(that: BigInt): Matrix = Matrix(left * that, right * that)

  def /(that: BigInt): Matrix = Matrix(left / that, right / that)

  def *(that: Vector): Vector = Vector(
    left.top * that.top + right.top * that.bottom,
    left.bottom * that.top + right.bottom * that.bottom)

  def *(that: Matrix): Matrix = Matrix(this * that.left, this * that.right)

  def *(that: Tensor): Tensor = Tensor(this * that.left, this * that.right)

  def <*>(that: Vector): Vector = (this * that).normalize

  def <*>(that: Matrix): Matrix = (this * that).normalize

  def <*>(that: Tensor): Tensor = (this * that).normalize
}

object Matrix {
  val identity = Matrix(Vector(1, 0), Vector(0, 1))
  val negation = Matrix(Vector(-1, 0), Vector(0, 1))
  val reciprocal = Matrix(Vector(0, 1), Vector(1, 0))
}
