package com.peterpotts.mobius

trait Real {
  val head: Matrix

  val tail: Real

  lazy val stream: Stream[Matrix] = head #:: tail.stream

  def decimal(n: Int): BigDecimal = stream.take(n).reduce(_ * _).decimal

  def +(that: Real): Real = new UnsignedTensorReal(Tensor.plus, this, that)

  def -(that: Real): Real = new UnsignedTensorReal(Tensor.minus, this, that)

  def *(that: Real): Real = new UnsignedTensorReal(Tensor.times, this, that)

  def /(that: Real): Real = new UnsignedTensorReal(Tensor.divide, this, that)

  def isMemberOf(matrix: Matrix): Boolean = {
    if (head isSubsetOf matrix)
      true
    else if (head isSubsetOf matrix.complement)
      false
    else
      tail isMemberOf (head.inverse * matrix)
  }
}
