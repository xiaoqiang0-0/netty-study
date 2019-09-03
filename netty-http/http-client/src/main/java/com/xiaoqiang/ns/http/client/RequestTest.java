package com.xiaoqiang.ns.http.client;

import com.xiaoqiang.netty.http.core.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;

import java.net.InetSocketAddress;

public class RequestTest {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventExecutors);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.remoteAddress(new InetSocketAddress("localhost", 8080));
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new HttpRequestEncoder());
                ch.pipeline().addLast(new SimpleChannelInboundHandler<HttpResponse>() {
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        HttpRequest request = new HttpRequest();
                        request.setMethod(HttpMethod.POST);
                        HttpHeaders headers = new DefaultHttpHeaders();
                        headers.add("ContentType", "text/html");
                        request.setHeaders(headers);
                        request.setProtocolVersion(HttpVersion.HTTP_1_1);
                        request.setUri("/hello");
                        request.setContent(Unpooled.wrappedBuffer("Hello?".getBytes()));
                        ctx.writeAndFlush(request);
                    }

                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, HttpResponse msg) throws Exception {
                        System.out.println("收到服务器响应:");
                        System.out.println(msg);
                    }
                });
                ch.pipeline().addLast(new HttpResponseDecoder());
            }
        });
        try {
            bootstrap.connect().sync().channel().closeFuture().sync();

        } finally {
            eventExecutors.shutdownGracefully().sync();
        }
    }
}
