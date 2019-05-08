package com.xiaoqiang.ns.el;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class SocketClientBootstrap {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new SimpleChannelInboundHandler<ByteBuffer>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuffer msg) throws Exception {
                        System.out.println("Received data:");
                        System.out.println(new String(msg.array(), StandardCharsets.UTF_8));
                    }
                });
        ChannelFuture future = bootstrap.connect(
                new InetSocketAddress("github.com", 80)
        );
        future.addListener(channelFuture -> {
            if (future.isSuccess()) {
                System.out.println("connect established");
            } else {
                System.err.println("connect attempt failed");
                channelFuture.cause().printStackTrace();
            }
        });
    }
}
