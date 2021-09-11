/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 一句话说明描述功能
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/5 11:19 上午
 */
public class NioSelectorServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9000));
        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);
        Selector sel = Selector.open();
        serverSocketChannel.register(sel, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功....");
        while (true) {
            // 阻塞等待需要处理的事件发生...
            int select = sel.select();
            Set<SelectionKey> selectionKeys = sel.selectedKeys();
            Iterator<SelectionKey> it = selectionKeys.iterator();
            while (it.hasNext()) {
                SelectionKey eventKey = it.next();
                // 有连接事件发生
                if (eventKey.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel)eventKey.channel();
                    // 服务端同意接收客户端连接..
                    channel.accept();
                    channel.configureBlocking(false);
                    channel.register(sel, SelectionKey.OP_READ);
                    System.out.println("客户端连接成功...");
                } else if (eventKey.isReadable()) {
                    // 客户端发送消息过来... 是已经准备好了...
                    SocketChannel channel = (SocketChannel)eventKey.channel();
                    ByteBuffer buf = ByteBuffer.allocate(128);
                    int len = channel.read(buf);
                    if (len > 0) {
                        System.out.println("从客户端发送过来的消息：" + new String(buf.array()));
                    } else if (len == -1) {
                        System.out.println("客户端端口连接...");
                        channel.close();
                    }
                }
                // 从事件集合中移除本次已经处理的事件，防止重复处理事件.
                it.remove();
            }
        }
    }
}
