package com.peterpotts.mobius

import scala.annotation.tailrec

class Chain(val matrix: Matrix, continuation: => Chain) {
  lazy val chain = continuation
}

object Chain {
  @tailrec def head(chain: Chain): (Matrix, Chain) =
    chain.matrix.pull match {
      case Some((digit, matrix)) =>
        digit -> new Chain(matrix, chain.chain)
      case None =>
        head(new Chain(chain.matrix * chain.chain.matrix, chain.chain.chain))
    }

  def stream(chain: Chain): Stream[Matrix] =
    head(chain) match {
      case (digit, tail) => digit #:: stream(tail)
    }

  def interval(chain: Chain, n: Int) = stream(chain).take(n).reduce(_ * _).interval
}
