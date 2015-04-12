package com.peterpotts.mobius

case class Interval(min: BigDecimal, max: BigDecimal) {
  override def toString = s"[$min,\n $max]"
}