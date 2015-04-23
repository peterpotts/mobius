package com.peterpotts.mobius

case class Vector(top: BigInt, bottom: BigInt) {
  lazy val rational = BigDecimal(normalize.top) / BigDecimal(normalize.bottom)
  lazy val gcd = top gcd bottom
  lazy val normal = gcd == BigInt(1)
  lazy val normalize = if (normal) this else this / gcd
  lazy val range = top.bitLength - bottom.bitLength

  def *(that: BigInt): Vector = Vector(top * that, bottom * that)

  def /(that: BigInt): Vector = Vector(top / that, bottom / that)

  def +(that: Vector): Vector = Vector(top + that.top, bottom + that.bottom)

  def -(that: Vector): Vector = Vector(top - that.top, bottom - that.bottom)
}
