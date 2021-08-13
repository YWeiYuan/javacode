/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.juc.thread.stop;

/**
 * 如何优雅停止线程，使用interrupt
 * interrupt 打扰、阻断的意思
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/8/12 5:17 下午
 */
public class ThreadStopUseInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread("thread-1") {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println(name + " running begin...flag: " + isInterrupted());
                while (!isInterrupted()) {
                    System.out.println(name + " inner running ....flag: " +  isInterrupted());
//                    try {
//                        Thread.sleep(3);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                        // 如果没有这句会造成死循环，interrupt是终止了sleep，真实业务场景中记得需要添加break.
//                        break;
//                    }
                }
                System.out.println(name + " running begin...end: " + isInterrupted());
            }
        };
        thread.start();
        Thread.sleep(10);
        // 调用interrupt方法后，标志位变成true，这样就可以跳出while循环
        thread.interrupt();
        System.out.println(" end ....");
    }
}
