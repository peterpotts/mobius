package com.peterpotts.mobius

import scala.annotation.tailrec

class FixedTensorDigitizer(tensor: Tensor, _left: => Digitizer, _right: => Digitizer) extends Digitizer {
  private lazy val left = _left
  private lazy val right = _right
  lazy val (head, tail) = digitize

  @tailrec private def digitize: (Matrix, Digitizer) =
    split match {
      case Some((digit, remainder)) =>
        digit -> new FixedTensorDigitizer(remainder, left, right)
      case None =>
        absorb.digitize
    }

  private lazy val absorb =
    if (tensor.range < tensor.transpose.range) {
      new FixedTensorDigitizer(tensor * left.head, left.tail, right)
    } else {
      new FixedTensorDigitizer(tensor ** right.head, left, right.tail)
    }

  private lazy val alpha = Digit.dMinus.inverse * tensor
  private lazy val beta = Digit.dZero.inverse * tensor
  private lazy val gamma = Digit.dPlus.inverse * tensor

  private lazy val split =
    if (alpha.unsigned)
      Some(Digit.dMinus -> debug(alpha))
    else if (gamma.unsigned)
      Some(Digit.dPlus -> debug(gamma))
    else if (beta.unsigned)
      Some(Digit.dZero -> debug(beta))
    else
      None
}
