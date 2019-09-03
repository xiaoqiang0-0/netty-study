package com.xiaoqiang.netty.http.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;



/**
 * http request decoder
 */
public class HttpRequestDecoder extends ByteToMessageDecoder implements HttpRequestParser {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        HttpRequest request = new HttpRequest();
        request.setMethod(parseMethod(in));
        request.setUri(parseUri(in));
        request.setProtocolVersion(parseVersion(in));
        request.setHeaders(parseHeaders(in));
        request.setContent(parseContent(in));
        out.add(request);
    }

}
