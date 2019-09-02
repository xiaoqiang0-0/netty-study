package com.xiaoqiang.ns.time.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerApp {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new TimeServerHandler());
        ChannelFuture f = bootstrap.bind(8848);
        f.sync();
        f.addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("server start ...");
            } else {
                System.err.println("server start error!");
            }
        });
    }
}
