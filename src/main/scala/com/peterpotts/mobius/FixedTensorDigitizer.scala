package com.peterpotts.mobius

import scala.annotation.tailrec

class FixedTensorDigitizer(
  tensor: Tensor,
  leftContinuation: => Digitizer,
  rightContinuation: => Digitizer) extends Digitizer {
  lazy private val leftDigitizer = leftContinuation
  lazy private val rightDigitizer = rightContinuation

  lazy val (head, tail) = digitize

  @tailrec private def digitize: (Matrix, Digitizer) =
    split match {
      case Some((digit, remainder)) =>
        digit -> new FixedTensorDigitizer(remainder, leftDigitizer, rightDigitizer)
      case None =>
        if (tensor.range < tensor.transpose.range) {
          //println("Left absorption")
          new FixedTensorDigitizer(tensor * leftDigitizer.head, leftDigitizer.tail, rightDigitizer).digitize
        }else {
          //println("Right absorption")
          new FixedTensorDigitizer(tensor ** rightDigitizer.head, leftDigitizer, rightDigitizer.tail).digitize
        }
    }

  private lazy val alpha = Digit.dMinus.inverse * tensor
  private lazy val beta = Digit.dZero.inverse * tensor
  private lazy val gamma = Digit.dPlus.inverse * tensor

  private lazy val split =
    if (alpha.valid) {
      //println(s"Emit - from $tensor leaving $alpha")
      Some(Digit.dMinus -> debug(alpha))
    } else if (gamma.valid) {
      //println(s"Emit + from $tensor leaving $gamma")
      Some(Digit.dPlus -> debug(gamma))
    } else if (beta.valid) {
      //println(s"Emit 0 from $tensor leaving $beta")
      Some(Digit.dZero -> debug(beta))
    } else {
      //println("No emission")
      None
    }
}
