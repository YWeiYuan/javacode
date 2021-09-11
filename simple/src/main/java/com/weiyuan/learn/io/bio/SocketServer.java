/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一句话说明描述功能
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/4 9:34 下午
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true) {
            System.out.println("等待连接....");
            // 这个是阻塞方法
            Socket socket = serverSocket.accept();
            System.out.println("有客户端连接了....");
            ExecutorService service = Executors.newCachedThreadPool();
            service.execute(
                    () -> {
                        // ... do something inside runnable task
                        try {
                            handle(socket);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            service.shutdown();

        }
    }

    /**
     * 处理线程..
     *
     * @param socket
     */
    public static void handle(Socket socket) throws IOException {
        String threadName = Thread.currentThread().getName();
        byte[] bytes = new byte[1024];
        System.out.println(threadName + " 准备read..");
        InputStream inputStream = socket.getInputStream();
        int read = 0;
        System.out.println(threadName + " read 完毕");
        StringBuilder buf = new StringBuilder();
        while ((read = inputStream.read(bytes)) != -1) {
            buf.append(new String(bytes, 0, read));
        }
        System.out.println(threadName + " 接收数据内容：" + buf);
        socket.close();
    }
}
