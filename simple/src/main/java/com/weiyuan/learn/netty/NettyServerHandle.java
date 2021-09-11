/*
 * Copyright 2012-2022
 */
package com.weiyuan.learn.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;


/**
 * 处理与客户端连接的数据
 *
 * @author longquan.huang
 * @version 1.0
 * @date 2021/9/6 12:19 上午
 */
public class NettyServerHandle extends ChannelInboundHandlerAdapter {
    /**
     * 当客户端连接服务器完成会触发该方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("客户端连接通道建立完成...");
    }

    /**
     * 读取客户端发送的数据
     * @param ctx 上下文对象，含有通道channel， 管道pipeline
     * @param msg 就是客户端发送的数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Channel channel = ctx.channel();
        // 本质是一个双向连接，出站入站
        ChannelPipeline pipeline = ctx.pipeline();
        // 将msg转成一个ByteBuf,类似Nio的ByteBuffer
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("收到客户端的消息：" + buf.toString(CharsetUtil.UTF_8));
    }







}
