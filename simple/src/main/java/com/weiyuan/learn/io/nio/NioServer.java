/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

/**
 * 一句话说明描述功能
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/5 9:47 上午
 */
public class NioServer {
    private static final List<SocketChannel> socketChannelList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9000));
        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);
        System.out.println("服务器启动成功...");
        while (true) {
            // 接收连接 上面设置了非阻塞，这个地方就不会阻塞
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                // 设置read等操作非阻塞
                socketChannel.configureBlocking(false);
                socketChannelList.add(socketChannel);
            }
            Iterator<SocketChannel> iterator = socketChannelList.iterator();
            while (iterator.hasNext()) {
                SocketChannel channel = iterator.next();
                ByteBuffer buf = ByteBuffer.allocate(1024);
                // 上面设置非阻塞
                int len = channel.read(buf);
                if (len > 0) {
                    System.out.println("接收到的数据：" + new String(buf.array()));
                } else if (len == -1) {
                    iterator.remove();
                    System.out.println("客户端关闭了...");
                }
            }

        }




    }
}
