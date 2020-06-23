package com.xiaoqiang.hss;

import com.xiaoqiang.netty.http.core.HttpRequest;
import com.xiaoqiang.netty.http.core.HttpResponse;
import com.xiaoqiang.netty.http.core.HttpResponseBody;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.List;

public class StaticResourceEncoder extends MessageToMessageEncoder<StaticResource> {
    @Override
    protected void encode(ChannelHandlerContext ctx, StaticResource msg, List<Object> out) throws Exception {
        HttpResponse response = new HttpResponse();
        HttpRequest request = msg.getRequest();
        response.setHeaders(request.getHeaders());
        response.setStatus(HttpResponseStatus.OK);
        response.setVersion(request.getProtocolVersion());
        response.setBody(new HttpResponseBody(msg.getData()));
        ctx.writeAndFlush(response);
        ctx.close();
    }
}
