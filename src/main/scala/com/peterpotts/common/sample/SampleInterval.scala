package com.peterpotts.common.sample

import org.joda.time.Interval

object SampleInterval extends SampleIdentity(
  for {
    start <- SampleDateTime
    end <- SampleDateTime
  } yield {
    if (start.isAfter(end)) new Interval(end, start) else new Interval(start, end)
  })
