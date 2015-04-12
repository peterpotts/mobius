package com.peterpotts.common.sample

import scala.concurrent.duration.Duration

object SampleDuration extends SampleIdentity(
  for {
    length <- SamplePick(1 to 9)
    unit <- SampleTimeUnit
  } yield Duration(length, unit))
