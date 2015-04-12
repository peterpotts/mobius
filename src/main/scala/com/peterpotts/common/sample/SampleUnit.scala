package com.peterpotts.common.sample

case object SampleUnit extends Sample[Unit] {
  def next(): Unit = ()
}
