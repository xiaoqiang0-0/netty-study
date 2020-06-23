package com.xiaoqiang.hss;

import com.xiaoqiang.netty.http.core.HttpRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class StaticResourceDecoder extends MessageToMessageDecoder<HttpRequest> {


    @Override
    protected void decode(ChannelHandlerContext ctx, HttpRequest msg, List<Object> out) throws Exception {
        StaticResource resource = new StaticResource(msg.getUri());
        resource.setRequest(msg);
        ctx.writeAndFlush(resource);
        ctx.close();
    }
}
