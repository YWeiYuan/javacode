/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.distribute.concurrency.multthread;

/**
 * volatile 可见性调研
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/11 8:36 下午
 */
public class ShareData {
//    volatile int number = 0;
    // 子线程如果修改，主线程是没有办法感知到的。
    int number = 0;

    public void setNumber100() {
        this.number = 100;
    }

    public static void main(String[] args) {
        String v1 = "";
        boolean equals = v1.equals("22");

    }
}
