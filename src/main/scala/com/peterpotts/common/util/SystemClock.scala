package com.peterpotts.common.util

object SystemClock extends Clock {
  def milliseconds = System.currentTimeMillis
}
