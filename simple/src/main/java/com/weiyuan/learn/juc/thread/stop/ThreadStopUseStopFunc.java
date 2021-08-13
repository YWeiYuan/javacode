/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.juc.thread.stop;


/**
 * 线程中断，使用thread的stop方法
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/8/11 2:23 下午
 */
public class ThreadStopUseStopFunc {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("run start ----");
            try {
                Thread.sleep(11);
                System.out.println("run end ----");
            } catch (InterruptedException ex) {
                System.out.println("InterruptedException ---");
                ex.printStackTrace();
            } finally {
                System.out.println("finally start...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("finally end....");
            }
        });

        thread.start();
        Thread.sleep(20);
        thread.stop();
        System.out.println("end ----");
    }
}
