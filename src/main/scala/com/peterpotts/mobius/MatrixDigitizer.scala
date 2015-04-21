package com.peterpotts.mobius

import scala.annotation.tailrec

class MatrixDigitizer(matrix: Matrix, continuation: => Digitizer) extends Digitizer {
  lazy private val digitizer = continuation

  lazy val (head, tail) = pull

  @tailrec private def pull: (Matrix, Digitizer) =
    matrix.pull match {
      case Some((digit, remainder)) =>
        digit -> new MatrixDigitizer(remainder, digitizer)
      case None =>
        new MatrixDigitizer(matrix * digitizer.head, digitizer.tail).pull
    }
}
