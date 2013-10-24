import sbt._
import sbt.Keys._

object VainTestBuild extends Build {

  lazy val vaintest = Project(
    id = "vain-test",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "vain-test",
      organization := "me.gladwell",
      version := "1.0.0-SNAPSHOT",
      scalaVersion := "2.10.2",
      libraryDependencies ++= Seq(
        "me.gladwell" %% "vain" % "1.0.0-SNAPSHOT",
        "org.scalatest" %% "scalatest" % "2.0.RC2" % "test"
      )
    )
  )
}
