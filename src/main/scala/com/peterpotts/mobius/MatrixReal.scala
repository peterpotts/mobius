package com.peterpotts.mobius

class MatrixReal(domain: Stream[Seq[Matrix]], matrix: Matrix, lazyReal: => Real) extends Real {
  private lazy val real = lazyReal

  lazy val (head, tail) = digitize

  private lazy val digitize: (Matrix, Real) = domain match {
    case sign #:: digits => sign.foldLeft[Option[(Matrix, Matrix)]](None) {
      case (None, digit) =>
        val remainder = digit.inverse * matrix
        if (remainder.unsigned) Some(digit -> remainder.normalize) else None
      case (emit, _) => emit
    } map {
      case (digit, remainder) =>
        digit -> new MatrixReal(digits, remainder, real)
    } getOrElse {
      val absorb = new MatrixReal(digits, matrix * real.head, real.tail)
      absorb.digitize
    }
  }
}

class UnsignedMatrixReal(matrix: Matrix, lazyReal: => Real) extends MatrixReal(Digit.unsigned, matrix, lazyReal)

class SignedMatrixReal(matrix: Matrix, lazyReal: => Real) extends MatrixReal(Digit.signed, matrix, lazyReal)
