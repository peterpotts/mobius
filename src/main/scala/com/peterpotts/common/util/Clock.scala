package com.peterpotts.common.util

import org.joda.time.DateTime

trait Clock {
  def milliseconds: Long

  def seconds = milliseconds / 1000L

  def dateTime = new DateTime(milliseconds)
}
