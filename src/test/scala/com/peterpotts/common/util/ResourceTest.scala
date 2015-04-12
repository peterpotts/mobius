package com.peterpotts.common.util

import org.mockito.Mockito.verify
import org.scalatest.mock.MockitoSugar
import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

class ResourceTest extends WordSpec with Matchers with MockitoSugar {
  "A resource" should {
    "be released" in {
      val target = mock[Resource]
      val value = Random.nextInt()
      Resource.using(target)(_.release())(resource => value) shouldEqual value
      verify(target).release()
    }
  }

  trait Resource {
    def release()
  }

}
