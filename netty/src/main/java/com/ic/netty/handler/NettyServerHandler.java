package com.ic.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    // channelId <--> ctx
    private static Map<String, ChannelHandlerContext> deviceOnlineMap = new ConcurrentHashMap<>();

    // deviceId <--> channelId
    private static Map<String, String> deviceControlMap = new ConcurrentHashMap<>();

    public static ChannelHandlerContext channelHandlerContext;

    /**
     * 客户端连接会触发
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        String channelId = ctx.channel().id().toString();
//        map.put(channelId, ctx);
//        System.out.println(map.size() + " | " + map);
//        log.info(channelId+ "Channel active......");
//        int port = ((InetSocketAddress) ctx.channel().localAddress()).getPort();
//        System.out.println(port);
//        System.out.println("连接：" + Thread.currentThread().getName());

        if (channelHandlerContext == null) {
            channelHandlerContext = ctx;
            System.out.println("注册");
        }
    }

    /**
     * 客户端发消息会触发
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接收消息：" + Thread.currentThread().getName());
//        if (true) {
//            throw new RuntimeException("");
//        }
//        String str = null;
//        str.toString();
        log.info("服务器收到消息: {}", msg.toString());
//        ctx.write("你也好哦");
//        ctx.flush();
    }

    /**
     * 发生异常触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
        log.info("exceptionCaught");
    }

    /**
     * 客户端断开会触发
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        String channelId = ctx.channel().id().toString();
//        map.remove(channelId);
//        System.out.println(map.size() + " | " + map);
//        log.info(channelId + "Channel inactive......");
        log.info("Channel inactive......");
    }
}

//map<channelId, ctx>
//
//map<deviceId, channelId>



