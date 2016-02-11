name := "mobius"

organization := "com.peterpotts"

version := "1.0.0-SNAPSHOT"

homepage := Some(url("https://github.com/peterpotts/mobius"))

startYear := Some(2015)

scmInfo := Some(
  ScmInfo(
    url("https://github.com/peterpotts/mobius"),
    "scm:git:https://github.com/peterpotts/mobius.git",
    Some("scm:git:git@github.com:peterpotts/mobius.git")))

scalaVersion := "2.11.7"

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-feature",
  "-language:postfixOps",
  "-Xlint:_",
  "-Xverify",
  "-Yclosure-elim")

javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")

javaOptions ++= Seq("-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005")

libraryDependencies ++= {
  object Versions {
    val scalatest = "2.2.4"
    val mockito = "1.10.19"
    val akka = "2.3.11"
    val jodaConvert = "1.8"
    val jodaTime = "2.9.2"
    val eddsa = "0.0.1-SNAPSHOT"
    val typesafeConfig = "1.2.1"
    val scalaLogging = "3.1.0"
    val slf4j = "1.7.12"
    val logback = "1.1.3"
  }

  object Dependencies {
    val scalatest = "org.scalatest" %% "scalatest" % Versions.scalatest

    val mockitoCore = "org.mockito" % "mockito-core" % Versions.mockito

    val akkaActor = "com.typesafe.akka" %% "akka-actor" % Versions.akka
    val akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j" % Versions.akka
    val akkaAgent = "com.typesafe.akka" %% "akka-agent" % Versions.akka
    val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % Versions.akka

    val jodaConvert = "org.joda" % "joda-convert" % Versions.jodaConvert
    val jodaTime = "joda-time" % "joda-time" % Versions.jodaTime

    val typesafeConfig = "com.typesafe" % "config" % Versions.typesafeConfig
    val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % Versions.scalaLogging

    val slf4jApi = "org.slf4j" % "slf4j-api" % Versions.slf4j
    // log4jOverSlf4j: Associated with exclude("log4j", "log4j")
    val log4jOverSlf4j = "org.slf4j" % "log4j-over-slf4j" % Versions.slf4j
    // jclOverSlf4j: Associated with exclude("commons-logging", "commons-logging")
    val jclOverSlf4j = "org.slf4j" % "jcl-over-slf4j" % Versions.slf4j
    val logbackClassic = "ch.qos.logback" % "logback-classic" % Versions.logback
  }

  import Dependencies._

  Seq(
    scalatest % "test",
    mockitoCore % "test",
    akkaActor,
    akkaSlf4j,
    akkaAgent,
    akkaTestkit % "test",
    jodaConvert,
    jodaTime,
    typesafeConfig,
    scalaLogging,
    slf4jApi,
    log4jOverSlf4j,
    jclOverSlf4j,
    logbackClassic)
}

enablePlugins(com.typesafe.sbt.packager.archetypes.JavaServerAppPackaging)

/*-----------------*/
/* Assembly plugin */
/*-----------------*/

mainClass in assembly := Some("com.peterpotts.mobius.Application")

assemblyMergeStrategy in assembly := {
  case "reference.conf" => MergeStrategy.first
  case pathList => (assemblyMergeStrategy in assembly).value(pathList)
}

/*-----------------*/
/* Packager plugin */
/*-----------------*/

maintainer in Linux := "Peter Potts <peter.potts@cointrust.com>"

packageSummary in Linux := "Mobius"

packageDescription := "Exact real arithmetic using Mobius transformations."

daemonUser in Linux := normalizedName.value

daemonGroup in Linux := (daemonUser in Linux).value
