package com.peterpotts.mobius

case class Vector(top: BigInt, bottom: BigInt) {
  lazy val rational = BigDecimal(normalize.top) / BigDecimal(normalize.bottom)
  lazy val gcd = top gcd bottom
  lazy val normal = gcd == BigInt(1)
  lazy val normalize = if (normal) this else this / gcd
  lazy val range = top.bitLength - bottom.bitLength

  lazy val signum =
    top.signum match {
      case -1 =>
        bottom.signum match {
          case -1 => -1
          case 0 => -1
          case 1 => 0
        }
      case 0 =>
        bottom.signum match {
          case -1 => -1
          case 0 => 0
          case 1 => 1
        }
      case 1 =>
        bottom.signum match {
          case -1 => 0
          case 0 => 1
          case 1 => 1
        }
    }

  lazy val unsigned = signum != 0

  def *(that: BigInt): Vector = Vector(top * that, bottom * that)

  def /(that: BigInt): Vector = Vector(top / that, bottom / that)

  def +(that: Vector): Vector = Vector(top + that.top, bottom + that.bottom)

  def -(that: Vector): Vector = Vector(top - that.top, bottom - that.bottom)
}
