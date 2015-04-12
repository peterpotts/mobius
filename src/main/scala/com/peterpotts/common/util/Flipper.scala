package com.peterpotts.common.util

import scala.concurrent.{ExecutionContext, Future}

object Flipper {
  def flip[T](value: Option[Future[T]])(implicit executionContext: ExecutionContext): Future[Option[T]] =
    value.fold(Future.successful[Option[T]](None))(_.map(Some(_)))
}
