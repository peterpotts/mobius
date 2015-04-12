package com.peterpotts.common.sample

class SampleMap[A, B](iterable: Iterable[Sample[(A, B)]])
  extends MapSample[Iterable[(A, B)], Map[A, B]](SampleIterable(iterable), _.toMap)

object SampleMap {
  def apply[A, B](iterable: Iterable[Sample[(A, B)]]): Sample[Map[A, B]] =
    new SampleMap(iterable)

  def apply[A, B](sampleAB: Sample[(A, B)], size: Int): Sample[Map[A, B]] =
    apply(Iterable.fill(size)(sampleAB))

  def apply[A, B](sampleAB: Sample[(A, B)]): Sample[Map[A, B]] = apply(sampleAB, defaultSampleSize)

  def apply[A, B](sampleAB: Sample[(A, B)], sizes: IndexedSeq[Int]): Sample[Map[A, B]] =
    SamplePick(sizes).flatMap(size => apply(sampleAB, size))

  def apply[A, B](sampleA: Sample[A], sampleB: Sample[B], size: Int): Sample[Map[A, B]] =
    apply(SamplePair(sampleA, sampleB), size)

  def apply[A, B](sampleA: Sample[A], sampleB: Sample[B]): Sample[Map[A, B]] =
    apply(SamplePair(sampleA, sampleB), defaultSampleSize)

  def apply[A, B](sampleA: Sample[A], sampleB: Sample[B], sizes: IndexedSeq[Int]): Sample[Map[A, B]] =
    apply(SamplePair(sampleA, sampleB), sizes)
}
