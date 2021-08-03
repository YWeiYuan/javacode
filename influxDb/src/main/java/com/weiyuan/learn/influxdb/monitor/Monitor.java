/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.influxdb.monitor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 创建定时任务，模拟上报数据，并写入InfluxDB
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/8/3 2:10 下午
 */
@Service
@Slf4j
@AllArgsConstructor
public class Monitor {
    private InfluxDB influxDB;

    @Scheduled(fixedRate = 5000)
    public void writeQPS() {
       int count =  (int) (Math.random() * 100);
        Point point = Point.measurement("ApiQPS")
                .tag("url", "hello")
                .addField("count", count)
                .addField("x", "local")
                .time(System.nanoTime(), TimeUnit.MICROSECONDS)
                .build();
        influxDB.write("monitor", "autogen", point);
        log.info("上报统计数据：{}", count);
    }
}

