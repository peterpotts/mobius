package com.peterpotts.common.sample

class SampleIterable[A](iterable: Iterable[Sample[A]]) extends Sample[Iterable[A]] {
  def next(): Iterable[A] = iterable.map(_.next())
}

object SampleIterable {
  def apply[A](iterable: Iterable[Sample[A]]) = new SampleIterable(iterable)

  def apply[A](sampleA: Sample[A], size: Int = defaultSampleSize): Sample[Iterable[A]] =
    apply(Iterable.fill(size)(sampleA))

  def apply[A](sampleA: Sample[A], sizes: IndexedSeq[Int]): Sample[Iterable[A]] =
    SamplePick(sizes).flatMap(size => apply(sampleA, size))
}
