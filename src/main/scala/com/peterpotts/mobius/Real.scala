package com.peterpotts.mobius

trait Real {
  val head: Matrix

  val tail: Real

  lazy val stream: Stream[Matrix] = head #:: tail.stream

  private def fold(head: Matrix, tail: Stream[Matrix]): Stream[Matrix] = {
    val product = head * tail.head
    product #:: fold(product, tail.tail)
  }

  lazy val precision: Stream[Matrix] = head #:: fold(stream.head, stream.tail)

  def +(that: Real) = new UnsignedTensorReal(Tensor.plus, this, that)

  def -(that: Real) = new UnsignedTensorReal(Tensor.minus, this, that)

  def *(that: Real) = new UnsignedTensorReal(Tensor.times, this, that)

  def /(that: Real) = new UnsignedTensorReal(Tensor.divide, this, that)

  def inside(digit: Matrix, x: Real, y: Real): Real = {
    val inside = digit.inverse * head

    val outside = digit.turnover.inverse * head
    println("head = " + head)
    println("inside = " + inside)
    println("inside.unsigned = " + inside.unsigned)
    println("outside = " + outside)
    println("outside.unsigned = " + outside.unsigned)

    if (inside.unsigned) x else if (outside.unsigned) y else tail.inside(digit * head.inverse, x, y)
  }
}
