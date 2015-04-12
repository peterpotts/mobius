package com.peterpotts.common.sample

class SampleString(iterable: Iterable[Sample[Char]])
  extends MapSample[Iterable[Char], String](SampleIterable(iterable), _.mkString)

object SampleString extends SampleString(Iterable.fill(defaultSampleSize)(SampleAlphanumeric)) {
  def apply(iterable: Iterable[Sample[Char]]): SampleString = new SampleString(iterable)

  def apply(sampleChar: Sample[Char], size: Int = defaultSampleSize): Sample[String] =
    apply(Iterable.fill(size)(sampleChar))

  def apply(sampleChar: Sample[Char], sizes: IndexedSeq[Int]): Sample[String] =
    SamplePick(sizes).flatMap(size => apply(sampleChar, size))

  def apply(size: Int): Sample[String] = apply(SampleAlphanumeric, size)

  def apply(sizes: IndexedSeq[Int]): Sample[String] = apply(SampleAlphanumeric, sizes)
}
