/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.markwork;

import org.openjdk.jol.info.ClassLayout;

/**
 * 一句话说明描述功能
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/8/11 10:25 上午
 */
public class MarkWork {
    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(new OneClass()).toPrintable());
    }
}
