package com.peterpotts.common.sample

import java.net.InetAddress

import scala.util.Random

object SampleInetAddress extends Sample[InetAddress] {
  def next() = InetAddress.getByName((1 to 4).map(_ => Random.nextInt(256)).mkString("."))
}
