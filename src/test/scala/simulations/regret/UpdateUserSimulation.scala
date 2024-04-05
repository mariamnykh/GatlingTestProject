package simulations.regret

import io.gatling.core.Predef.{atOnceUsers, findCheckBuilder2ValidatorCheckBuilder, pause, scenario}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class UpdateUserSimulation extends Simulation {

  private val httpProtocol = http
    .baseUrl("https://reqres.in")

  val updateNewUser = scenario("Update NEW user")
    .exec(
      http("Update NEW user")
        .put("/api/users/2")
        .header("content-type", "application/json")
        .body(RawFileBody("testData/user.json"))
        .asJson
        .check(status.is(200))
        .check(jsonPath("$.name").is("john"))
        .check(jsonPath("$.job").is("zion resident")))
    .pause(4)

  setUp(updateNewUser.inject(rampUsers(10).during(10))).protocols(httpProtocol)

}
