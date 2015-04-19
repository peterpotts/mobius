package com.peterpotts.mobius

import scala.annotation.tailrec

class Tee(val tensor: Tensor, leftContinuation: => Chain, rightContinuation: => Chain) {
  lazy val leftChain = leftContinuation
  lazy val rightChain = rightContinuation
}

object Tee {
  @tailrec def head(tee: Tee): (Matrix, Tee) =
    tee.tensor.pull match {
      case Some((digit, tensor)) =>
        digit -> new Tee(tensor, tee.leftChain, tee.rightChain)
      case None =>
        if (tee.tensor.angle > tee.tensor.transpose.angle)
          head(new Tee(
            tee.tensor * tee.leftChain.matrix,
            tee.leftChain.chain,
            tee.rightChain))
        else
          head(new Tee(
            tee.tensor ** tee.rightChain.matrix,
            tee.leftChain,
            tee.rightChain.chain))
    }

  def stream(tee: Tee): Stream[Matrix] =
    head(tee) match {
      case (digit, tail) => digit #:: stream(tail)
    }

  def interval(tee: Tee, n: Int) = stream(tee).take(n).reduce(_ * _).interval
}
