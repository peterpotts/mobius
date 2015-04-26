package com.peterpotts.mobius

import scala.annotation.tailrec

/**
 * Not working!!!
 */
class VariableTensorDigitizer(
  tensor: Tensor,
  leftContinuation: => Digitizer,
  rightContinuation: => Digitizer) extends Digitizer {
  lazy private val leftDigitizer = leftContinuation
  lazy private val rightDigitizer = rightContinuation

  lazy val (head, tail) = digitize

  @tailrec private def digitize: (Matrix, Digitizer) =
    split match {
      case Some((digit, remainder)) =>
        digit -> new VariableTensorDigitizer(remainder, leftDigitizer, rightDigitizer)
      case None =>
        if (tensor.range > tensor.transpose.range)
          new VariableTensorDigitizer(tensor * leftDigitizer.head, leftDigitizer.tail, rightDigitizer).digitize
        else
          new VariableTensorDigitizer(tensor ** rightDigitizer.head, leftDigitizer, rightDigitizer.tail).digitize
    }

  private lazy val matrix = Matrix(tensor.max, tensor.min)
  private lazy val remnants = debug(matrix.inverse <*> tensor)

  private lazy val split =
    if (tensor.min.top.signum == 0 && tensor.max.bottom.signum == 0)
      None
    else
      Some(matrix -> remnants)
}
