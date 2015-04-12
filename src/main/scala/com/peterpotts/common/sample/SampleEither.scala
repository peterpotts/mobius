package com.peterpotts.common.sample

case class SampleEither[A, B](sampleA: Sample[A], sampleB: Sample[B])
  extends FlatMapSample[Boolean, Either[A, B]](SampleBoolean, { boolean =>
    for {
      a <- sampleA
      b <- sampleB
    } yield if (boolean) Left(a) else Right(b)
  })
