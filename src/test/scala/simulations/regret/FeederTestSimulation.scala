package simulations.regret

import io.gatling.core.Predef.{atOnceUsers, findCheckBuilder2ValidatorCheckBuilder, pause, scenario}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class FeederTestSimulation extends Simulation {

  private val httpProtocol = http
    .baseUrl("http://computer-database.gatling.io")

  val feeder = csv("testData/user2.csv").random

  val scn = scenario("Feeder demo")
    .repeat(1) {
      feed(feeder)
        .exec { session =>
          println("Name = " + session("name").as[String])
          println("Job = " + session("job").as[String])
          session
        }
    }.pause(1)
    .exec(http("Go to ${page}")
      .get("/#{page}"))


  setUp(scn.inject(atOnceUsers(3))).protocols(httpProtocol)
    .assertions(global.failedRequests.count.is(0))

}
