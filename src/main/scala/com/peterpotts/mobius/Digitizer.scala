package com.peterpotts.mobius

trait Digitizer {
  val head: Matrix

  val tail: Digitizer

  lazy val stream: Stream[Matrix] = head #:: tail.stream

  private def fold(head: Matrix, tail: Stream[Matrix]): Stream[Matrix] = {
    val product = head * tail.head
    product #:: fold(product, tail.tail)
  }

  lazy val precision = head #:: fold(stream.head, stream.tail)

  def debug[T](value: T): T = {
    //println(value)
    value
  }
}
