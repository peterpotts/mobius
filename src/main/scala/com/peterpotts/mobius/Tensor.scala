package com.peterpotts.mobius

case class Tensor(left: Matrix, right: Matrix) {
  lazy val transpose = Tensor(Matrix(left.left, right.left), Matrix(left.right, right.right))
  lazy val gcd = left.gcd gcd right.gcd
  lazy val normal = gcd == BigInt(0) || gcd == BigInt(1)
  lazy val normalize = if (normal) this else this / gcd
  lazy val range = left.range max right.range
  lazy val valid = left.valid && right.valid
  lazy val min = Matrix(left.min, right.min).min
  lazy val max = Matrix(left.max, right.max).max

  lazy val unsigned =
    left.left.signum == left.right.signum &&
      left.right.signum == right.left.signum &&
      right.left.signum == right.right.signum &&
      right.right.signum != 0

  def *(that: BigInt): Tensor = Tensor(left * that, right * that)

  def /(that: BigInt): Tensor = Tensor(left / that, right / that)

  def *(that: Vector): Matrix = transpose ** that

  def **(that: Vector): Matrix = Matrix(left * that, right * that)

  def *(that: Matrix): Tensor = (transpose ** that).transpose

  def **(that: Matrix): Tensor = Tensor(left * that, right * that)
}

object Tensor {
  val plus = Tensor(Matrix(Vector(0, 0), Vector(1, 0)), Matrix(Vector(1, 0), Vector(0, 1)))
  val minus = Tensor(Matrix(Vector(0, 0), Vector(1, 0)), Matrix(Vector(-1, 0), Vector(0, 1)))
  val times = Tensor(Matrix(Vector(1, 0), Vector(0, 0)), Matrix(Vector(0, 0), Vector(0, 1)))
  val divide = Tensor(Matrix(Vector(0, 0), Vector(1, 0)), Matrix(Vector(0, 1), Vector(0, 0)))
}
