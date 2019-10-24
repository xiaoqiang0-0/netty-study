package com.xiaoqiang.netty.http.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class HttpResponseDecoder extends ByteToMessageDecoder implements HttpResponseParser {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        HttpResponse response = new HttpResponse();
        response.setVersion(parseVersion(in));
        response.setStatus(parseStatus(in));
        response.setHeaders(parseHeaders(in));
        response.setBody(new HttpResponseBody(parseContent(in)));
        out.add(response);
    }
}
