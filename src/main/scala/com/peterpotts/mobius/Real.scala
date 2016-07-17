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
    val remainder = digit.inverse * head
    println("head = " + head)
    println("remainder = " + remainder)
    println("remainder.unsigned = " + remainder.unsigned)

    val merge = digit.inverse * Matrix(head.right, head.left)
    if (remainder.unsigned) x else if (merge.unsigned) y else tail.inside(remainder, x, y)
  }
}
