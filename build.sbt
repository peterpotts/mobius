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
    val spray = "1.3.3"
    val jodaMoney = "0.10.0"
    val eddsa = "0.0.1-SNAPSHOT"
    val phantom = "1.8.12"
    val cassandra = "2.1.5"
    val lz4 = "1.2.0"
    val codahale = "3.0.2"
    val aws = "1.9.40"
    val rhino = "1.7R2"
    val spraySwagger = "0.5.1"
    val typesafeConfig = "1.2.1"
    val camel = "2.15.2"
    val zip4j = "1.3.2"
    val openCsv = "2.3"
    val mockFtpServer = "2.6"
    val slf4j = "1.7.12"
    val logback = "1.1.3"
    val hazelcast = "3.6-EA2"
  }

  object Dependencies {
    val scalatest = "org.scalatest" %% "scalatest" % Versions.scalatest

    val mockitoCore = "org.mockito" % "mockito-core" % Versions.mockito

    val akkaActor = "com.typesafe.akka" %% "akka-actor" % Versions.akka
    val akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j" % Versions.akka
    val akkaAgent = "com.typesafe.akka" %% "akka-agent" % Versions.akka
    val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % Versions.akka

    val typesafeConfig = "com.typesafe" % "config" % Versions.typesafeConfig

    val slf4jApi = "org.slf4j" % "slf4j-api" % Versions.slf4j
    // log4jOverSlf4j: Associated with exclude("log4j", "log4j")
    val log4jOverSlf4j = "org.slf4j" % "log4j-over-slf4j" % Versions.slf4j
    // jclOverSlf4j: Associated with exclude("commons-logging", "commons-logging")
    val jclOverSlf4j = "org.slf4j" % "jcl-over-slf4j" % Versions.slf4j
    val logbackClassic = "ch.qos.logback" % "logback-classic" % Versions.logback
  }

  import Dependencies._
  Seq(scalatest % "test",
    mockitoCore % "test",
    akkaActor,
    akkaSlf4j,
    akkaAgent,
    akkaTestkit % "test",
    typesafeConfig,
    slf4jApi,
    log4jOverSlf4j,
    jclOverSlf4j,
    logbackClassic)
}

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("releases"),
  Resolver.typesafeRepo("releases"),
  "Spray Repository" at "http://repo.spray.io")

enablePlugins(com.typesafe.sbt.packager.archetypes.JavaServerAppPackaging)

mainClass in assembly := Some("com.peterpotts.mobius.Application")

assemblyMergeStrategy in assembly := {
  case "reference.conf" => MergeStrategy.first
  case pathList => (assemblyMergeStrategy in assembly).value(pathList)
}

maintainer in Linux := "Peter Potts <peter.potts@cointrust.com>"

packageSummary in Linux := "Mobius"

packageDescription := "Exact real arithmetic using Mobius transformations."

daemonUser in Linux := normalizedName.value

daemonGroup in Linux := (daemonUser in Linux).value
