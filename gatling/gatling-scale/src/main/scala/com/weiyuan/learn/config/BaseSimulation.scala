package com.weiyuan.learn.config

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
 * 基础配置
 *
 * @author longquan.huang
 * @Date 2021/8/13 6:01 下午
 * @version 1.0
 */
class BaseSimulation extends Simulation {
  val userCount: Int = getProperty("USERS", "5").toInt
  val rampDuration: Int = getProperty("RAMP_DURATION", "10").toInt
  val testDuration: Int = getProperty("DURATION", "60").toInt

  // 1 Common HTTP Configuration
  val httpConf = http
    .baseUrl("http://localhost:8080/app/")
    .header("Accept", "application/json")
  //    .proxy(Proxy("localhost", 8888).httpsPort(8888))

  after {
    println("Stress test completed.")
  }

  private def getProperty(propertyName: String, defaultValue: String) = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }

}
