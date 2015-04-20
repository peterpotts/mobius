package com.peterpotts.mobius

case class Tensor(left: Matrix, right: Matrix) {
  lazy val alpha = Digit.alpha.inverse * this
  lazy val beta = Digit.beta.inverse * this
  lazy val gamma = Digit.gamma.inverse * this
  lazy val transpose = Tensor(Matrix(left.left, right.left), Matrix(left.right, right.right))
  lazy val gcd = left.gcd gcd right.gcd
  lazy val normal = gcd == BigInt(1)
  lazy val normalize = if (normal) this else this / gcd
  lazy val angle = Math.max(left.angle, right.angle)

  lazy val valid: Boolean = left.valid && right.valid

  lazy val pull: Option[(Matrix, Tensor)] = {
    if (beta.valid)
      Some(Digit.beta -> beta)
    else if (alpha.valid)
      Some(Digit.alpha -> alpha)
    else if (gamma.valid)
      Some(Digit.gamma -> gamma)
    else
      None
  }

  lazy val interval = Interval(left.interval.min min right.interval.min, left.interval.max max right.interval.max)

  def *(that: BigInt): Tensor = Tensor(left * that, right * that)

  def /(that: BigInt): Tensor = Tensor(left / that, right / that)

  def *(that: Matrix): Tensor = Tensor(left * that, right * that)

  def **(that: Matrix): Tensor = (transpose * that).transpose
}
