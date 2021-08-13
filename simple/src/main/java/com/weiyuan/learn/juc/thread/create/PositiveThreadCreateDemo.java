/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.juc.thread.create;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * 有建设性创建线程例子
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/8/13 10:10 上午
 */
public class PositiveThreadCreateDemo {
    public static void main(String[] args) {
        // positive example 1 使用注入been的方式
        /**
         <bean id="userThreadPool"
         class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
         <property name="corePoolSize" value="10" />
         <property name="maxPoolSize" value="100" />
         <property name="queueCapacity" value="2000" />
         <property name="threadFactory" value= threadFactory />
         <property name="rejectedExecutionHandler">
         <ref local="rejectedExecutionHandler" />
         </property>
         </bean>

         // in code
         userThreadPool.execute(thread);
         */

        // positive example 2：
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

        // positive example 3
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("common-pool-%d").build();
        // common Thread pool
        ThreadPoolExecutor commonPool = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        commonPool.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        commonPool.shutdown();

    }

}
