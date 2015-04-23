package com.peterpotts.mobius

import scala.annotation.tailrec

class FixedMatrixDigitizer(matrix: Matrix, continuation: => Digitizer) extends Digitizer {
  private lazy val digitizer = continuation

  lazy val (head, tail) = digitize

  @tailrec private def digitize: (Matrix, Digitizer) =
    split match {
      case Some((digit, remainder)) =>
        digit -> new FixedMatrixDigitizer(remainder, digitizer)
      case None =>
        new FixedMatrixDigitizer(matrix * digitizer.head, digitizer.tail).digitize
    }

  private lazy val alpha = Digit.alpha.inverse * matrix
  private lazy val beta = Digit.beta.inverse * matrix
  private lazy val gamma = Digit.gamma.inverse * matrix

  private lazy val split =
    if (alpha.valid)
      Some(Digit.alpha -> alpha)
    else if (gamma.valid)
      Some(Digit.gamma -> gamma)
    else if (beta.valid)
      Some(Digit.beta -> beta)
    else
      None
}
