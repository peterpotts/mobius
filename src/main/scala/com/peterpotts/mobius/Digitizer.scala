package com.peterpotts.mobius

trait Digitizer {
  def head: Matrix

  def tail: Digitizer

  def toStream: Stream[Matrix] = head #:: tail.toStream
}
