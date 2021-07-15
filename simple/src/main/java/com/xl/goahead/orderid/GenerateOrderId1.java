/*
 * Copyright 2012-2022
 */
package com.xl.goahead.orderid;


import org.apache.commons.lang.StringUtils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 生成不重复订单id
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/7/8 5:15 下午
 */
public class GenerateOrderId1 {

    /**
     * 订单号生成规则：OD + yyMMddHHmmssSSS + 5位数(商户ID3位+随机数2位) 22位
     * @param merchId 商户id
     * @return 订单号
     */
    public static String getNumber(String merchId) {
        StringBuilder orderNo = new StringBuilder(new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()));
        if (StringUtils.isNotEmpty(merchId)) {
            if (merchId.length() > 3) {
                orderNo.append(merchId, 0, 3);
            } else {
                orderNo.append(merchId);
            }
        }
        int length = orderNo.toString().length();
        orderNo.append(getRandomByLen(20 - length));
        return orderNo.toString();
    }

    /**
     * 生产指定位数的随机数
     *
     * @param size 生成随机数个数
     * @return 随机数
     */
    private static String getRandomByLen(int size) {
        SecureRandom r = new SecureRandom();
        StringBuilder begin = new StringBuilder("1");
        StringBuilder end = new StringBuilder("9");
        for (int i = 0; i < size; i++) {
            end.append("0");
            begin.append("0");
        }
        int randomNum = r.nextInt(Integer.parseInt(begin.toString()) + Integer.parseInt(end.toString()));
        return String.valueOf(randomNum);
    }

    public static void main(String[] args) {
        String merchId = "12334";
        List<String> orderNos = Collections.synchronizedList(new ArrayList<>());
        IntStream.range(0,5000).parallel().forEach( i ->
            orderNos.add(getNumber(merchId))
        );
        List<String> collect = orderNos.stream().distinct().collect(Collectors.toList());
        System.out.println("生成订单数：" + orderNos.size());
        System.out.println("过滤后的订单数：" + collect.size());
        System.out.println("重复订单数：" + (orderNos.size() - collect.size()));
    }

}
