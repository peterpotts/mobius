package com.peterpotts.common.sample

import scala.util.Random

class SampleBytes(size: Int) extends Sample[Array[Byte]] {
  def next(): Array[Byte] = {
    val bytes = new Array[Byte](size)
    Random.nextBytes(bytes)
    bytes
  }
}

object SampleBytes extends SampleBytes(defaultSampleSize) {
  def apply(size: Int) = new SampleBytes(size)
}
