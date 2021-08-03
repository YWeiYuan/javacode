package com.weiyuan.learn.influxdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class InfluxDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfluxDbApplication.class, args);
    }

}
