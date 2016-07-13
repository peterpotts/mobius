package com.peterpotts.mobius

import scala.annotation.tailrec

class FixedTensorDigitizer(tensor: Tensor, _left: => Digitizer, _right: => Digitizer) extends Digitizer {
  private lazy val left = _left
  private lazy val right = _right

  lazy val (head, tail) = digitize

  @tailrec private def digitize: (Matrix, Digitizer) = {
    val emit = {
      lazy val alpha = Digit.dMinus.inverse * tensor
      lazy val beta = Digit.dZero.inverse * tensor
      lazy val gamma = Digit.dPlus.inverse * tensor

      //noinspection ScalaStyle
      if (alpha.unsigned)
        Some(Digit.dMinus -> alpha)
      else if (gamma.unsigned)
        Some(Digit.dPlus -> gamma)
      else if (beta.unsigned)
        Some(Digit.dZero -> beta)
      else
        None
    }

    emit match {
      case Some((digit, remainder)) =>
        digit -> new FixedTensorDigitizer(remainder, left, right)
      case None =>
        val absorb =
          if (tensor.range < tensor.transpose.range) {
            new FixedTensorDigitizer(tensor * left.head, left.tail, right)
          } else {
            new FixedTensorDigitizer(tensor ** right.head, left, right.tail)
          }

        absorb.digitize
    }
  }
}
