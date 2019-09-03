package com.xiaoqiang.netty.http.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class HttpRequestEncoder extends MessageToByteEncoder<HttpRequest> {
    @Override
    protected void encode(ChannelHandlerContext ctx, HttpRequest msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.getMethod().name().getBytes());
        out.writeByte(' ');
        out.writeBytes(msg.getUri().getBytes());
        out.writeByte(' ');
        out.writeBytes(msg.getProtocolVersion().text().getBytes());
        out.writeByte('\r');
        out.writeByte('\n');
        msg.getHeaders().forEach(header -> {
            out.writeBytes(header.getKey().getBytes());
            out.writeByte(':');
            out.writeByte(' ');
            out.writeBytes(header.getValue().getBytes());
            out.writeByte('\r');
            out.writeByte('\n');
        });
        out.writeByte('\r');
        out.writeByte('\n');
        out.writeBytes(msg.getContent());
        out.writeByte('\r');
        out.writeByte('\n');
    }
}
