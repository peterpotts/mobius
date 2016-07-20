import sbt._

object Settings {
  val name = "mobius"
  val organization = "com.peterpotts"
  val version = "1.0.0-SNAPSHOT"
  val scalaVersion = "2.11.8"
  val scalaMajorVersion = scalaVersion.split('.').take(2).mkString(".")

  object Versions {
    val scalaModules = "1.0.4"
    val scalaTest = "2.2.6"
    val mockito = "1.10.19"
    val jodaTime = "2.9.3"
    val jodaMoney = "0.10.0"
    val jodaConvert = "1.6"
    val typesafeConfig = "1.2.1"
    val slf4j = "1.7.21"
    val scalaLogging = "3.4.0"
    val logback = "1.1.7"
  }

  object Dependencies {
    val scalaCompiler = "org.scala-lang" % "scala-compiler" % scalaVersion
    val scalaReflect = "org.scala-lang" % "scala-reflect" % scalaVersion
    val scalaParserCombinators = "org.scala-lang.modules" %% "scala-parser-combinators" % Versions.scalaModules
    val scalaXml = "org.scala-lang.modules" %% "scala-xml" % Versions.scalaModules
    val scalaTest = "org.scalatest" %% "scalatest" % Versions.scalaTest

    val mockitoCore = "org.mockito" % "mockito-core" % Versions.mockito

    val jodaTime = "joda-time" % "joda-time" % Versions.jodaTime
    val jodaMoney = "org.joda" % "joda-money" % Versions.jodaMoney
    val jodaConvert = "org.joda" % "joda-convert" % Versions.jodaConvert

    val typesafeConfig = "com.typesafe" % "config" % Versions.typesafeConfig

    val slf4jApi = "org.slf4j" % "slf4j-api" % Versions.slf4j
    val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % Versions.scalaLogging
    val logbackClassic = "ch.qos.logback" % "logback-classic" % Versions.logback
  }

}
