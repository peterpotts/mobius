package com.peterpotts.mobius

class VectorReal(domain: Stream[Seq[Matrix]], vector: Vector) extends Real {
  lazy val (head, tail) = digitize

  private lazy val digitize: (Matrix, Real) = domain match {
    case sign #:: digits => sign.foldLeft[Option[(Matrix, Vector)]](None) {
      case (None, digit) =>
        val remainder = digit.inverse * vector
        if (remainder.isPositive) Some(digit -> remainder.normalize) else None
      case (emit, _) => emit
    } map {
      case (digit, remainder) => digit -> new VectorReal(digits, remainder)
    } get
  }
}

class UnsignedVectorReal(vector: Vector) extends VectorReal(Digit.unsigned, vector)

class SignedVectorReal(vector: Vector) extends VectorReal(Digit.signed, vector)
