package com.xiaoqiang.netty.http.server;

import com.xiaoqiang.netty.http.core.HttpRequest;
import com.xiaoqiang.netty.http.core.HttpResponse;
import com.xiaoqiang.netty.http.core.HttpResponseBody;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
        log.info("Now is readable!");

        HttpResponse response = new HttpResponse();
        response.setHeaders(msg.getHeaders());
        response.setStatus(HttpResponseStatus.OK);
        response.setVersion(msg.getProtocolVersion());
        response.setBody(new HttpResponseBody("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Hello Netty!</h1>\n" +
                "<p>This message from http server.Power by netty!</p>\n" +
                "</body>\n" +
                "</html>"));
        ctx.writeAndFlush(response);
        ctx.close();
    }
}
