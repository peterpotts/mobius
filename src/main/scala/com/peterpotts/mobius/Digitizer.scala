package com.peterpotts.mobius

trait Digitizer {
  def head: Matrix

  def tail: Digitizer

  def toStream: Stream[Matrix] = head #:: tail.toStream

  def digits(n: Int) = toStream.take(n).reduce(_ * _)
}
