/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.juc.thread.stop;

/**
 * 如何安全中断线程
 * 使用内部标记变量
 * @author longquan.huang
 * @version 1.0
 * @date 2021/8/11 2:07 下午
 */
public class ThreadStopUseInterMark {
    private static class UseThread extends Thread {
        public UseThread(String name) {
            super(name);
        }
        /**
         * 标记变量，表示线程是否终止
         */
        private boolean live = true;

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            while (live) {
                System.out.println(name + " is running。。。。");
                System.out.println(name + " interrupted flag = " + interrupted());
            }
        }

        public void terminate() {
            live = false;
        }
    }

    public static void main(String[] args) {
        UseThread useThread = new UseThread("useThread");
        useThread.start();
        try {
            Thread.sleep(20);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        useThread.terminate();
    }
}
