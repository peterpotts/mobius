package com.peterpotts.mobius

trait Digitizer {
  val head: Matrix

  val tail: Digitizer

  lazy val stream: Stream[Matrix] = head #:: tail.stream

  private def fold(head: Matrix, tail: Stream[Matrix]): Stream[Matrix] = {
    val product = head * tail.head
    product #:: fold(product, tail.tail)
  }

  lazy val precision: Stream[Matrix] = head #:: fold(stream.head, stream.tail)

  def +(that: Digitizer) = new FixedTensorDigitizer(Tensor.plus, this, that)

  def -(that: Digitizer) = new FixedTensorDigitizer(Tensor.minus, this, that)

  def *(that: Digitizer) = new FixedTensorDigitizer(Tensor.times, this, that)

  def /(that: Digitizer) = new FixedTensorDigitizer(Tensor.divide, this, that)
}
