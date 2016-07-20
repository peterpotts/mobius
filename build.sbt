import Settings.Dependencies._
import sbt.Keys._

name := Settings.name
organization := Settings.organization
version := Settings.version
homepage := Some(url(s"https://github.com/peterpotts/${Settings.name}"))
//noinspection ScalaStyle
startYear := Some(2016)
scalaVersion := Settings.scalaVersion

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

scmInfo := Some(
  ScmInfo(
    url(s"https://github.com/peterpotts/${Settings.name}"),
    s"scm:git:https://github.com/peterpotts/${Settings.name}.git",
    Some(s"scm:git:git@github.com:peterpotts/${Settings.name}.git")))

libraryDependencies ++= Seq(scalaCompiler,
  scalaReflect,
  scalaParserCombinators,
  scalaXml,
  scalaTest % "test",
  mockitoCore % "test",
  jodaConvert,
  jodaTime,
  typesafeConfig,
  scalaLogging,
  slf4jApi,
  logbackClassic)
