/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.log;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * test log4j2的使用
 * https://blog.csdn.net/vbirdbest/article/details/71751835
 * @author longquan.huang
 * @version 1.0
 * @date 2021/5/12 1:41 下午
 */
public class Log4j2Demo {
    final private static Logger log = LogManager.getLogger("test");
    public static void main(String[] args) {
        log.error("Logging in user {} with birthday {}", "name", new Date());
        log.error("xxx: {}", () -> "1111");
        final String text = "debug...format";
        log.debug("debug: {}", ()-> text);
    }
}
