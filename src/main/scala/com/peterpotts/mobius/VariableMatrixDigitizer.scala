package com.peterpotts.mobius

class VariableMatrixDigitizer(matrix: Matrix, continuation: => Digitizer) extends Digitizer {
  lazy private val digitizer = continuation

  lazy val (head, tail) = matrix -> digitizer
}
