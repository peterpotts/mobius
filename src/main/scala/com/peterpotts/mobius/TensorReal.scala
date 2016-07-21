package com.peterpotts.mobius

class TensorReal(domain: Stream[Seq[Matrix]], tensor: Tensor, lazyLeft: => Real, lazyRight: => Real) extends Real {
  private lazy val left = lazyLeft
  private lazy val right = lazyRight

  lazy val (head, tail) = digitize

  private def digitize: (Matrix, Real) = domain match {
    case sign #:: digits => sign.foldLeft[Option[(Matrix, Tensor)]](None) {
      case (None, digit) =>
        val remainder = digit.inverse * tensor
        if (remainder.isPositive) Some(digit -> remainder.normalize) else None
      case (emit, _) => emit
    } map {
      case (digit, remainder) =>
        digit -> new TensorReal(digits, remainder, left, right)
    } getOrElse {
      val absorb = {
        //noinspection ScalaStyle
        if (tensor.magnitude < tensor.transpose.magnitude)
          new TensorReal(digits, tensor * left.head, left.tail, right)
        else
          new TensorReal(digits, tensor ** right.head, left, right.tail)
      }

      absorb.digitize
    }
  }
}

class UnsignedTensorReal(tensor: Tensor, lazyLeft: => Real, lazyRight: => Real)
  extends TensorReal(Digit.unsigned, tensor, lazyLeft, lazyRight)

class SignedTensorReal(tensor: Tensor, lazyLeft: => Real, lazyRight: => Real)
  extends TensorReal(Digit.signed, tensor, lazyLeft, lazyRight)

