package simulations.regret


import io.gatling.core.Predef.{atOnceUsers, findCheckBuilder2ValidatorCheckBuilder, pause, scenario}
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class CreateNewUserSimulation extends Simulation {

  private val httpProtocol = http
    .baseUrl("https://reqres.in")

  val createNewUser = scenario("Create NEW user")
    .exec(
      http("Create NEW user")
        .post("/api/users")
        .header("content-type", "application/json")
        .body(StringBody(
          """
            |{
            |    "name": "morpheus",
            |    "job": "leader"
            |}
            |""".stripMargin))
        .asJson
        .check(status.is(201))
        .check(jsonPath("$.name").is("morpheus"))
        .check(jsonPath("$.job").is("leader")))
    .pause(4)

  setUp(createNewUser.inject(rampUsers(10).during(10))).protocols(httpProtocol)

}
