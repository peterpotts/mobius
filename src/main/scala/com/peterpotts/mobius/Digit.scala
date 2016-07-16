package com.peterpotts.mobius

import scala.collection.immutable.Seq

object Digit {
  val dMinus = Matrix(Vector(1, 1), Vector(0, 2))
  val dPlus = Matrix(Vector(2, 0), Vector(1, 1))
  val dZero = Matrix(Vector(3, 1), Vector(1, 3))

  val sPlus = Matrix(Vector(1, 0), Vector(0, 1))
  val sMinus = Matrix(Vector(0, -1), Vector(1, 0))
  val sZero = Matrix(Vector(1, 1), Vector(-1, 1))
  val sInfinity = Matrix(Vector(1, -1), Vector(1, 1))

  private val ds = Seq(dMinus, dPlus, dZero)
  private val ss = Seq(sPlus, sMinus, sZero, sInfinity)

  val unsigned = Stream.continually(ds)
  val signed = ss #:: unsigned
}
