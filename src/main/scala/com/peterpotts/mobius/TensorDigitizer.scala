package com.peterpotts.mobius

import scala.annotation.tailrec

class TensorDigitizer(tensor: Tensor, leftContinuation: => Digitizer, rightContinuation: => Digitizer) extends Digitizer {
  lazy private val leftDigitizer = leftContinuation
  lazy private val rightDigitizer = rightContinuation

  lazy val (head, tail) = pull

  @tailrec private def pull: (Matrix, Digitizer) =
    tensor.pull match {
      case Some((digit, remainder)) =>
        digit -> new TensorDigitizer(remainder, leftDigitizer, rightDigitizer)
      case None =>
        if (tensor.angle > tensor.transpose.angle)
          new TensorDigitizer(tensor * leftDigitizer.head, leftDigitizer.tail, rightDigitizer).pull
        else
          new TensorDigitizer(tensor ** rightDigitizer.head, leftDigitizer, rightDigitizer.tail).pull
    }
}
