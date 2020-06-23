package com.xiaoqiang.hss;

import com.xiaoqiang.netty.http.core.HttpRequest;
import com.xiaoqiang.netty.http.core.HttpResponse;
import com.xiaoqiang.netty.http.core.HttpResponseBody;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpResponseStatus;

public class StaticResourceHandler extends SimpleChannelInboundHandler<HttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
        StaticResource resource = new StaticResource(msg.getUri());
        resource.setRequest(msg);
        HttpResponse response = new HttpResponse();
        response.setHeaders(msg.getHeaders());
        response.setStatus(HttpResponseStatus.OK);
        response.setVersion(msg.getProtocolVersion());
        response.setBody(new HttpResponseBody(resource.getData()));
        ctx.writeAndFlush(response);
        ctx.close();
    }
}
