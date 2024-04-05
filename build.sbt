import Dependencies._

enablePlugins(GatlingPlugin)

lazy val root = (project in file("."))
  .settings(
    inThisBuild(
      List(
        organization := "com.maria.test",
        scalaVersion := "2.12.10",
        //scalaVersion := "2.11.12", not competible with gatling 3
        version := "0.1.0-SNAPSHOT"
      )),
    name := "GatlingTestProject",
    libraryDependencies ++= gatling
  )
