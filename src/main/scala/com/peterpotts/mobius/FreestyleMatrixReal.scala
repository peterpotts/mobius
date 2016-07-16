package com.peterpotts.mobius

class FreestyleMatrixReal(matrix: Matrix, lazyReal: => Real) extends Real {
  lazy private val real = lazyReal

  lazy val (head, tail) = matrix -> real
}
