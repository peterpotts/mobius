package com.peterpotts.mobius

class FixedVectorDigitizer(vector: Vector) extends Digitizer {
  lazy val (head, tail) = digitize

  private lazy val digitize: (Matrix, Digitizer) = {
    val emit = {
      lazy val alpha = Digit.dMinus.inverse * vector
      lazy val beta = Digit.dZero.inverse * vector
      lazy val gamma = Digit.dPlus.inverse * vector

      //noinspection ScalaStyle
      if (alpha.unsigned)
        Digit.dMinus -> alpha.normalize
      else if (gamma.unsigned)
        Digit.dPlus -> gamma.normalize
      else if (beta.unsigned)
        Digit.dZero -> beta.normalize
      else
        throw new IllegalArgumentException
    }

    val (digit, remainder) = emit
    digit -> new FixedVectorDigitizer(remainder)
  }
}
