package com.peterpotts.common.sample

import scala.util.{Failure, Success, Try}

class SampleTry[A](sampleA: Sample[A], sampleB: Sample[String])
  extends FlatMapSample[Boolean, Try[A]](SampleBoolean, { boolean =>
    for {
      a <- sampleA
      b <- sampleB
    } yield if (boolean) Success(a) else Failure(new RuntimeException(b))
  })

object SampleTry {
  def apply[A](sampleA: Sample[A], sampleB: Sample[String]) = new SampleTry(sampleA, sampleB)
}
