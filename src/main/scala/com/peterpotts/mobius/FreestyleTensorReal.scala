package com.peterpotts.mobius

class FreestyleTensorReal(tensor: Tensor, lazyLeft: => Real, lazyRight: => Real) extends Real {
  lazy private val left = lazyLeft
  lazy private val right = lazyRight

  lazy val (head, tail) = digitize

  private lazy val digitize: (Matrix, Real) = {
    val digit = tensor.info

    //noinspection ScalaStyle
    if (digit.empty)
      None
    else {
      val remainder = digit.inverse * tensor
      Some(digit -> remainder.normalize)
    }
  } map {
    case (digit, remainder) =>
      digit -> new FreestyleTensorReal(remainder, left, right)
  } getOrElse {
    //noinspection ScalaStyle
    val absorb = if (tensor.range < tensor.transpose.range)
      new FreestyleTensorReal(tensor * left.head, left.tail, right)
    else
      new FreestyleTensorReal(tensor ** right.head, left, right.tail)

    absorb.digitize
  }
}
