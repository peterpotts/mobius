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

scalaVersion := "2.11.4"

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

libraryDependencies ++= {
  val akkaVersion = "2.3.8"
  val sprayVersion = "1.3.2"
  val phantomVersion = "1.5.0"
  val slf4jVersion = "1.7.10"

  Seq(
    "rhino" % "js" % "1.7R2" % "test",
    "org.scalatest" %% "scalatest" % "2.2.3" % "test",
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "io.spray" %% "spray-routing" % sprayVersion,
    "io.spray" %% "spray-caching" % sprayVersion,
    "io.spray" %% "spray-client" % sprayVersion,
    "io.spray" %% "spray-testkit" % sprayVersion % "test",
    "com.gettyimages" %% "spray-swagger" % "0.5.0",
    "com.typesafe" % "config" % "1.2.1",
    "com.websudos" %% "phantom-dsl" % phantomVersion,
    "com.websudos" %% "phantom-zookeeper" % phantomVersion exclude("log4j", "log4j"),
    "net.jpountz.lz4" % "lz4" % "1.3.0",
    "io.dropwizard.metrics" % "metrics-core" % "3.1.0",
    "org.slf4j" % "slf4j-api" % slf4jVersion,
    "org.slf4j" % "log4j-over-slf4j" % slf4jVersion, // See exclude("log4j", "log4j")
    "org.slf4j" % "jcl-over-slf4j" % slf4jVersion, // See exclude("commons-logging", "commons-logging")
    "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
    "ch.qos.logback" % "logback-classic" % "1.1.2")
}

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("releases"),
  Resolver.typesafeRepo("releases"),
  "Spray Repository" at "http://repo.spray.io",
  "Twitter Repository" at "http://maven.twttr.com",
  "Websudos Repository" at "http://maven.websudos.co.uk/ext-release-local")

enablePlugins(com.typesafe.sbt.packager.archetypes.JavaServerAppPackaging)

net.virtualvoid.sbt.graph.Plugin.graphSettings

mainClass in assembly := Some("com.peterpotts.mobius.Application")

assemblyMergeStrategy in assembly := {
  case "com/twitter/common/args/apt/cmdline.arg.info.txt.1" => MergeStrategy.discard
  case "com/websudos/phantom/Manager$.class" => MergeStrategy.first
  case "com/websudos/phantom/Manager.class" => MergeStrategy.first
  case "logback.xml" => MergeStrategy.first
  case pathList => (assemblyMergeStrategy in assembly).value(pathList)
}

maintainer in Linux := "Peter Potts <peter.potts@cointrust.com>"

packageSummary in Linux := "Mobius"

packageDescription := "Exact real arithmetic using Mobius transformations."

daemonUser in Linux := normalizedName.value

daemonGroup in Linux := (daemonUser in Linux).value

