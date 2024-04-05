package simulations.regret

import io.gatling.core.Predef.{atOnceUsers, findCheckBuilder2ValidatorCheckBuilder, pause, scenario}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class DeleteUserSimulation extends Simulation {

  private val httpProtocol = http
    .baseUrl("https://reqres.in")

  val deleteUser = scenario("Delete user")
    .exec(
      http("Delete user")
        .delete("/api/users/2")
        .check(status.is(204)))
    .pause(4)

  setUp(deleteUser.inject(rampUsers(10).during(10))).protocols(httpProtocol)

}
