package com.weiyuan.learn.simulations

import com.weiyuan.learn.config.BaseSimulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt

/**
 * 一句话说明描述功能
 *
 * @author longquan.huang
 * @date 2021/8/13 5:59 下午
 * @version 1.0
 */
class AddPauseTime extends BaseSimulation{

  val scn = scenario("Video Game DB")
    .exec(http("Get All Video Games - 1st call")
      .get("videogames"))
    .pause(5)

    .exec(http("Get specific game")
      .get("videogames/1"))
    .pause(1, 20)

    .exec(http("Get All Video Games - 2nd call")
      .get("videogames"))
    .pause(3000.milliseconds)

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)

}
