package com.peterpotts.common.sample

class SampleSeq[A](iterable: Iterable[Sample[A]])
  extends MapSample[Iterable[A], Seq[A]](SampleIterable(iterable), _.toSeq)

object SampleSeq {
  def apply[A](iterable: Iterable[Sample[A]]): Sample[Seq[A]] = new SampleSeq(iterable)

  def apply[A](sampleA: Sample[A], size: Int = defaultSampleSize): Sample[Seq[A]] =
    apply(Iterable.fill(size)(sampleA))

  def apply[A](sampleA: Sample[A], sizes: IndexedSeq[Int]): Sample[Seq[A]] =
    SamplePick(sizes).flatMap(size => apply(sampleA, size))
}
