package com.ic.netty;

import com.ic.netty.handler.NettyServerHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {

    @GetMapping("/get")
    public void test1() {
        System.out.println(NettyServerHandler.channelHandlerContext.channel().isActive());
        NettyServerHandler.channelHandlerContext.channel().writeAndFlush("123");
    }
}
