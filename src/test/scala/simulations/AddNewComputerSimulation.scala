package simulations

import io.gatling.core.Predef.{atOnceUsers, findCheckBuilder2ValidatorCheckBuilder, pause, scenario}
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class AddNewComputerSimulation extends Simulation {

  private val httpProtocol = http
    .baseUrl("http://computer-database.gatling.io")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.9,uk-UA;q=0.8,uk;q=0.7,ru;q=0.6,de;q=0.5")
    .upgradeInsecureRequestsHeader("1")

  val getAllComputers = scenario("Get all computers")
    .exec(
      http("Get all computers")
        .get("/computers"))
    .pause(10)

  val openNewComputerPage = scenario("Open new computer page")
    .exec(
      http("Open new computer page")
        .get("/computers"))

  val createNewComputer = scenario("Create new computer")
    .exec(
      http("Create new computer")
        .post("/computers")
        .formParam("name", "Maria")
        .formParam("introduced", "2021-12-12")
        .formParam("discontinued", "2021-12-12")
        .formParam("company", "1")
        .check(status.is(200))
    )

  val searchComputer = scenario("filter computer by model")
    .exec(
      http("filter computer by model")
        .get("/computers?f=Apple")
    )

  val users = scenario("Users").exec(openNewComputerPage, searchComputer)

  val admin = scenario("Admins").exec(openNewComputerPage, createNewComputer, searchComputer)

  //  private val scn = scenario((getClass.getSimpleName))
  //    .exec(openNewComputerPage, createNewComputer)

  setUp(
    users.inject(rampUsers(10).during(10)),
    admin.inject(rampUsers(4).during(10)))
    .protocols(httpProtocol)
}
