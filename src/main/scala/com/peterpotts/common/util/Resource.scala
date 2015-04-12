package com.peterpotts.common.util

/**
 * Automatic resource management.
 */
object Resource {
  def using[R, T](resource: R)(release: R => Unit)(use: R => T): T = try use(resource) finally release(resource)
}
