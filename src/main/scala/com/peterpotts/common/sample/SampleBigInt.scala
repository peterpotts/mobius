package com.peterpotts.common.sample

import scala.util.Random

class SampleBigInt(numberOfBits: Int) extends Sample[BigInt] {
  def next(): BigInt = BigInt(numberOfBits, Random)
}

object SampleBigInt extends SampleBigInt(256) {
  def apply(numberOfBits: Int) = new SampleBigInt(numberOfBits)
}
