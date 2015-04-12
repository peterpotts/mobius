package com.peterpotts.common.sample

/**
 * Sample data generator trait that incorporates for-comprehension.
 */
trait Sample[+A] {
  def next(): A

  def flatMap[B](function: A => Sample[B]): Sample[B] = new FlatMapSample[A, B](this, function)

  def map[B](function: A => B): Sample[B] = new MapSample[A, B](this, function)

  def filter(condition: A => Boolean): Sample[A] = new FilterSample[A](this, condition)

  def withFilter(condition: A => Boolean) = filter(condition)

  def filterNot(condition: A => Boolean): Sample[A] = filter(!condition(_))

  def distinct(): Sample[A] = new DistinctBySample[A, A](this, identity)

  def distinctBy[B](function: A => B): Sample[A] = new DistinctBySample[A, B](this, function)
}

class FlatMapSample[+A, B](sample: Sample[A], function: A => Sample[B]) extends Sample[B] {
  def next(): B = function(sample.next()).next()
}

class MapSample[+A, B](sample: Sample[A], function: A => B) extends Sample[B] {
  def next(): B = function(sample.next())
}

class FilterSample[+A](sample: Sample[A], condition: A => Boolean) extends Sample[A] {
  def next(): A = {
    val a = sample.next()
    if (condition(a)) a else next()
  }
}

class DistinctBySample[+A, B](sample: Sample[A], function: A => B) extends Sample[A] {
  private var set = Set.empty[B]

  def next(): A = {
    val a = sample.next()
    val b = function(a)

    if (set.contains(b)) {
      next()
    } else {
      set = set + b
      a
    }
  }
}
