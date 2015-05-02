package com.peterpotts.mobius

object Digit {
  val dMinus = Matrix(Vector(1, 1), Vector(0, 2))
  val dZero = Matrix(Vector(3, 1), Vector(1, 3))
  val dPlus = Matrix(Vector(2, 0), Vector(1, 1))
  
  val sInfinity = Matrix(Vector(1, -1), Vector(1, 1))
  val sMinus = Matrix(Vector(0, -1), Vector(1, 0))
  val sZero = Matrix(Vector(1, 1), Vector(-1, 1))
  val sPlus = Matrix(Vector(1, 0), Vector(0, 1))
}
