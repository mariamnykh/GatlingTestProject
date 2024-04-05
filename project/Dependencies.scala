import sbt._

object Dependencies {
  lazy val gatling: Seq[ModuleID] = Seq(
    "io.gatling.highcharts" % "gatling-charts-highcharts",
    "io.gatling" % "gatling-test-framework",
    "io.gatling" % "gatling-app",
    "io.gatling" % "gatling-recorder"
  ).map(_ % "3.3.1" % Test)

//  lazy val circe: Seq[ModuleID] = Seq(
//    "io.circe" %% "circe-core",
//    "io.circe" %% "circe-generic",
//    "io.circe" %% "circe-parser"
//  ).map(_ % "0.12.3")

//  lazy val pureconfig = "com.github.pureconfig" %% "pureconfig" % "0.12.3"
//  lazy val enumeratum = "com.beachape" %% "enumeratum" % "1.6.1"
//  lazy val pureconfigEnumeratum = "com.github.pureconfig" %% "pureconfig-enumeratum" % "0.12.3"
//  lazy val sttp = "com.softwaremill.sttp.client" %% "core" % "2.1.5"

}
