package com.peterpotts.common.sample

import org.joda.time.DateTime

import scala.util.Random

case object SampleDateTime extends Sample[DateTime] {
  def next() = {
    val seconds = Random.nextInt(Int.MaxValue).toLong
    val milliseconds = Random.nextInt(1000).toLong
    val instance = seconds * 1000L + milliseconds
    new DateTime(instance)
  }
}
