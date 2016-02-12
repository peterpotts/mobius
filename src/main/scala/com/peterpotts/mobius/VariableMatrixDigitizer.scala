package com.peterpotts.mobius

class VariableMatrixDigitizer(matrix: Matrix, _digitizer: => Digitizer) extends Digitizer {
  lazy private val digitizer = _digitizer

  lazy val (head, tail) = matrix -> digitizer
}
