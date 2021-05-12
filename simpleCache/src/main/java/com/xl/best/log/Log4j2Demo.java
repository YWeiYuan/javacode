/*
 * Copyright 2012-2022
 */
package com.xl.best.log;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Supplier;

import java.util.Date;

/**
 * 一句话说明描述功能
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/5/12 1:41 下午
 */
public class Log4j2Demo {
    final private static Logger log = LogManager.getLogger("test");
    public static void main(String[] args) {
        log.error("Logging in user {} with birthday {}", "name", new Date());
        log.error("xxx: {}", new Supplier<Object>() {
            @Override
            public Object get() {
                return "1111";
            }
        });
    }
}
