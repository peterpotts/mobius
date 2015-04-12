package com.peterpotts.common.util

import scala.concurrent.{ExecutionContext, Future}

object FutureDecorator {

  implicit class DecoratedFutureOption[T](futureOption: Future[Option[T]]) {
    def mapGetOrThrow(exception: => Exception)(implicit executionContext: ExecutionContext): Future[T] =
      futureOption.map(_.getOrElse(throw exception))
  }

}
