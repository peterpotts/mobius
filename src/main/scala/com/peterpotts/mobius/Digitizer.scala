package com.peterpotts.mobius

trait Digitizer {
  val head: Matrix

  val tail: Digitizer

  lazy val toStream: Stream[Matrix] = head #:: tail.toStream

  def digits(n: Int) = toStream.take(n).reduce(_ * _)

  def debug[T](value: T): T = {
    //println(value)
    value
  }
}
