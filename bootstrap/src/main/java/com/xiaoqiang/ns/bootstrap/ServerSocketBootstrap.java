package com.xiaoqiang.ns.bootstrap;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ServerSocketBootstrap {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new SimpleChannelInboundHandler<ByteBuffer>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuffer msg) throws Exception {
                        System.out.println("received data:");
                        System.out.println(new String(msg.array(), StandardCharsets.UTF_8));
                        msg.put("Success!".getBytes());
                    }
                });
        ChannelFuture future = serverBootstrap.bind(80);
        future.addListener(channelFuture -> {
            if (channelFuture.isSuccess()) {
                System.out.println("server bound");
            } else {
                System.out.println("bound attempt failed");
                channelFuture.cause().printStackTrace();
            }
        });

    }
}
