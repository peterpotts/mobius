package com.peterpotts.common.sample

import scala.concurrent.duration.{DAYS, HOURS, MINUTES}

object SampleTimeUnit extends SamplePick(IndexedSeq(DAYS, HOURS, MINUTES))
