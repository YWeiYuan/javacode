package com.weiyuan.learn

import com.weiyuan.learn.simulations.AddPauseTime
import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder


/**
 * 加特林执行入口
 *
 * @author longquan.huang
 * @date 2021/8/13 5:19 下午
 * @version 1.0
 */
object GatlingRunner {
  def main(args: Array[String]): Unit = {
    val simClass = classOf[AddPauseTime].getName
    val props = new GatlingPropertiesBuilder
    props.simulationClass(simClass)
    Gatling.fromMap(props.build)
  }
}
