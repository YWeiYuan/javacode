/*
 * Copyright 2012-2022
 */
package com.xl.goahead.orderid;


import org.apache.commons.lang.StringUtils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 生成不重复订单id
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/7/8 5:15 下午
 */
public class GenerateOrderId2 {

    /** 订单号生成(NEW) **/
    private static final AtomicInteger SEQ = new AtomicInteger(1000);
    private static final DateTimeFormatter DF_FMT_PREFIX = DateTimeFormatter.ofPattern("yyMMddHHmmssSS");
    private static final ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");

    public static String generateOrderNo() {
        LocalDateTime now = LocalDateTime.now(ZONE_ID);
        if (SEQ.intValue() > 9999) {
            SEQ.getAndSet(1000);
        }
        return now.format(DF_FMT_PREFIX) + SEQ.getAndIncrement();
    }

    public static void main(String[] args) {
        List<String> orderNos = Collections.synchronizedList(new ArrayList<>());
        IntStream.range(0,9999).parallel().forEach( i -> orderNos.add(generateOrderNo()));
        List<String> collect = orderNos.stream().distinct().collect(Collectors.toList());
        System.out.println("生成订单数：" + orderNos.size());
        System.out.println("过滤后的订单数：" + collect.size());
        System.out.println("重复订单数：" + (orderNos.size() - collect.size()));
    }

}
