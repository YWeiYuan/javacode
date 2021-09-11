/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty应用入口
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/5 10:47 下午
 */
public class NettyServer {
    public static void main(String[] args) {
        // 创建两个线程组bossGroup 和 workGroup, 含有的子线程 NioEventLoop的个数默认为cpu核数的两倍。
        // bossGroup 只处理连接请求，真正的和客户端业务处理会交给workGroup完成。
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup(8);

        // 创建服务端的启动对象
        ServerBootstrap bootstrap = new ServerBootstrap();
        int port = 9000;
        try {
            bootstrap.group(bossGroup, workGroup)
                    // 使用NioServerSocketChannel 作为服务器的通道实现
                    .channel(NioServerSocketChannel.class)
                    // 初始化服务器连接队列大小，服务器处理客户端程序端连接请求是顺序处理的，所有同一时间只能处理一个客户端连接。
                    // 多个客户端同时连接过来的时候，服务器将不能处理的连接请求放在队列中，等待处理。
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            // 对workGroup的处理器
                            ch.pipeline().addLast(new NettyServerHandle());
                        }
                    });
            System.out.println("netty 服务启动....");
            // 绑定一个端口并且同步生成一个channelFuture 异步对象，通过isDone()方法，可以判断异步事件的执行情况。
            // bing方法是异步操作，使用sync是等待异步操作完成。
            ChannelFuture cf = bootstrap.bind(port).sync();

            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (cf.isSuccess()) {
                        System.out.println("监听端口" + port + "成功");
                    } else {
                        System.out.println("监听端口" + port + "失败");
                    }
                }
            });
        } catch (Exception ex) {

        }


    }
}
