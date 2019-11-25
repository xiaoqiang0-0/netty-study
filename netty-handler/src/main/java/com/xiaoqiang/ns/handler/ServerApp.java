package com.xiaoqiang.ns.handler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class ServerApp {
    public static void main(String[] args) {
        ServerBootstrap server = new ServerBootstrap();
        server.group(new NioEventLoopGroup());
        server.channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new Handler1());
                        ch.pipeline().addLast(new Handler2());
                        ch.pipeline().addLast(new Handler3());
                    }
                });
        ChannelFuture future = server.bind(new InetSocketAddress(8080));
        future.syncUninterruptibly();
        future.channel().closeFuture().syncUninterruptibly();
    }
}
