package com.peterpotts.common.sample

import org.mockito.Mockito.when
import org.scalatest.mock.MockitoSugar
import org.scalatest.{Matchers, WordSpec}

class SampleTest extends WordSpec with Matchers with MockitoSugar {
  "A sample instance" should {
    "generate mapped values" in {
      val sample = mock[Sample[Int]]
      when(sample.next()) thenReturn 12
      new MapSample(sample, (_: Int).toString).next() shouldEqual "12"
    }

    "generate filtered values" in {
      val sample = mock[Sample[Boolean]]
      when(sample.next()) thenReturn(false, false, true)
      new FilterSample(sample, identity[Boolean]).next() shouldEqual true
    }

    "generate distinct values" in {
      val sample = mock[Sample[Int]]
      when(sample.next()) thenReturn(1, 3, 4)
      val isEven: Int => Boolean = _ % 2 == 0
      val distinct = new DistinctBySample[Int, Boolean](sample, isEven)
      distinct.next() shouldEqual 1
      distinct.next() shouldEqual 4
    }
  }
}
