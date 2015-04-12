package com.peterpotts.mobius

object Operation {
  val plus = Tensor(Matrix(Vector(0, 0), Vector(1, 0)), Matrix(Vector(1, 0), Vector(0, 1)))
  val minus = Tensor(Matrix(Vector(0, 0), Vector(1, 0)), Matrix(Vector(-1, 0), Vector(0, 1)))
  val times = Tensor(Matrix(Vector(1, 0), Vector(0, 0)), Matrix(Vector(0, 0), Vector(0, 1)))
  val divide = Tensor(Matrix(Vector(0, 0), Vector(1, 0)), Matrix(Vector(0, 1), Vector(0, 0)))

}
