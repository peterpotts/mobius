package com.peterpotts.mobius

class VariableTensorDigitizer(tensor: Tensor, _left: => Digitizer, _right: => Digitizer) extends Digitizer {
  lazy private val left = _left
  lazy private val right = _right

  lazy val (head, tail) = digitize

  private lazy val digitize: (Matrix, Digitizer) = {
    val emit = {
      val matrix = Matrix(tensor.max, tensor.min)
      val remnants = matrix.inverse <*> tensor
      if (remnants.unsigned) Some(matrix -> remnants.normalize) else None
    }

    emit map {
      case (digit, remainder) =>
        digit -> new VariableTensorDigitizer(remainder, left, right).absorb // TODO lazy!!
    } getOrElse {
      absorb.digitize
    }
  }

  //noinspection ScalaStyle
  private lazy val absorb =
    if (tensor.range < tensor.transpose.range)
      new VariableTensorDigitizer(tensor * left.head, left.tail, right)
    else
      new VariableTensorDigitizer(tensor ** right.head, left, right.tail)
}
