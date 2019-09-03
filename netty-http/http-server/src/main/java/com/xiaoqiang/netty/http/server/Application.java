package com.xiaoqiang.netty.http.server;

import com.xiaoqiang.netty.http.core.HttpRequestDecoder;
import com.xiaoqiang.netty.http.core.HttpResponseEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(eventLoopGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new HttpRequestDecoder());
                        ch.pipeline().addLast(new HttpServerHandler());
                        ch.pipeline().addLast(new HttpResponseEncoder());
                    }
                });
        ChannelFuture f = serverBootstrap.bind(80).sync();
        f.addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("server start...");
            } else {
                System.err.println("server start failed!");
            }
        });
    }
}
