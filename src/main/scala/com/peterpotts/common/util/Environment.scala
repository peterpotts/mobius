package com.peterpotts.common.util

object Environment {
  lazy val fixtures = sys.env.getOrElse("FIXTURES", "true") == "true"
  lazy val cassandraFixture = sys.env.getOrElse("CASSANDRA_FIXTURE", fixtures.toString) == "true"
}
