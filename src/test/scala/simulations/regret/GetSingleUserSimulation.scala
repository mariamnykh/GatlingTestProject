package simulations.regret

import io.gatling.core.Predef.Simulation

import io.gatling.core.Predef.{atOnceUsers, findCheckBuilder2ValidatorCheckBuilder, pause, scenario}
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class GetSingleUserSimulation extends Simulation{

  private val httpProtocol = http
    .baseUrl("https://reqres.in")

  val getUserById = scenario("Get user by id")
    .exec(
      http("Get user by id")
        .get("/api/users/2")
    .check(status.is(200))
    .check(jsonPath("$.data.id").is("2")))
    .pause(1)

  setUp(getUserById.inject(rampUsers(10).during(10))).protocols(httpProtocol)

}
