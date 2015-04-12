package com.peterpotts.common.util

import com.peterpotts.common.sample.SampleString
import org.scalatest.mock.MockitoSugar
import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class FlipperTest extends WordSpec with Matchers with MockitoSugar {
  "A flipper" should {
    "flip a future some" in {
      val value = SampleString.next()
      Await.result(Flipper.flip(Some(Future.successful(value))), 5.seconds) shouldEqual Some(value)
    }

    "flip a future none" in {
      Await.result(Flipper.flip(None), 5.seconds) shouldEqual None
    }
  }
}
