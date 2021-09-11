/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.io.bio;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO实现
 * 阻塞IO模型，一个连接对应一个线程进行接管，有1000条，就需要1000个线程。效率很低。占用大量内存 + cpu 调度时间。
 *
 * nc -v localhost 8888
 * @author longquan.huang
 * @version 1.0
 * @date 2021/8/30 4:27 下午
 */
public class BIO {
    private static boolean stop = false;
    public static void main(String[] args) throws Exception {
        int connectionNum = 0;
        int port = 8888;
        ExecutorService threadPool = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(port);

        while (!stop) {
            if (10 == connectionNum) {
                stop = true;
            }
            Socket accept = serverSocket.accept();
            threadPool.execute(()->{
                try {
                    Scanner scanner = new Scanner(accept.getInputStream());
                    PrintStream printStream = new PrintStream(accept.getOutputStream());
                    while (!stop) {
                        String trim = scanner.next().trim();
                        System.out.println("scanner... " + trim);
                        printStream.println("PONG:" + trim);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            connectionNum++;
        }
        threadPool.shutdown();
        serverSocket.close();
    }
}
