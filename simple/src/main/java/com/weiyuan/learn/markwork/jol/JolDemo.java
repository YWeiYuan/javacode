/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.markwork.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 定位：分析对象在JVM的大小和分布
 * https://blog.csdn.net/shihlei/article/details/84914901
 * @author longquan.huang
 * @version 1.0
 * @date 2021/8/11 10:29 上午
 */
public class JolDemo {
    public static Object generate() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", new Integer(1));
        map.put("b", "b");
        map.put("c", new Date());
        for (int i = 0; i < 10; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }
        return map;
    }

    public static void print(String message) {
        System.out.println(message);
        System.out.println("-------");
    }

    public static void main(String[] args) {
        Object obj = generate();
        //查看对象内部信息
        print(ClassLayout.parseInstance(obj).toPrintable());
        //查看对象外部信息
        print(GraphLayout.parseInstance(obj).toPrintable());
        //获取对象总大小
        print("size : " + GraphLayout.parseInstance(obj).totalSize());
    }
}
