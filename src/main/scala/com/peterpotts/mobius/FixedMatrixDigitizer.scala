package com.peterpotts.mobius

class FixedMatrixDigitizer(matrix: Matrix, _digitizer: => Digitizer) extends Digitizer {
  private lazy val digitizer = _digitizer

  lazy val (head, tail) = digitize

  private lazy val digitize: (Matrix, Digitizer) = {
    val emit = {
      lazy val alpha = Digit.dMinus.inverse * matrix
      lazy val beta = Digit.dZero.inverse * matrix
      lazy val gamma = Digit.dPlus.inverse * matrix

      //noinspection ScalaStyle
      if (alpha.unsigned)
        Some(Digit.dMinus -> alpha.normalize)
      else if (gamma.unsigned)
        Some(Digit.dPlus -> gamma.normalize)
      else if (beta.unsigned)
        Some(Digit.dZero -> beta.normalize)
      else
        None
    }

    emit map {
      case (digit, remainder) =>
        digit -> new FixedMatrixDigitizer(remainder, digitizer)
    } getOrElse {
      new FixedMatrixDigitizer(matrix * digitizer.head, digitizer.tail).digitize
    }
  }
}
