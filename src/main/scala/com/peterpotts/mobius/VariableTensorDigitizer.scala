package com.peterpotts.mobius

import scala.annotation.tailrec

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
        digit -> new VariableTensorDigitizer(remainder, leftDigitizer, rightDigitizer).absorb
      case None =>
        absorb.digitize
    }

  private def absorb =
    if (tensor.range < tensor.transpose.range)
      new VariableTensorDigitizer(tensor * leftDigitizer.head, leftDigitizer.tail, rightDigitizer)
    else
      new VariableTensorDigitizer(tensor ** rightDigitizer.head, leftDigitizer, rightDigitizer.tail)

  private lazy val matrix = Matrix(tensor.max, tensor.min)
  private lazy val remnants = debug(matrix.inverse <*> tensor)

  private lazy val split:Option[(Matrix,Tensor)] ={
    if (remnants.unsigned){
      //println(s"Emit $matrix")
      //println(s"Remnants $remnants")
      //println(s"remnants.unsigned = ${remnants.unsigned}")
      //println(s"Emit $matrix")
      Some(matrix -> remnants)
    }else {
      None
    }

  }
}
