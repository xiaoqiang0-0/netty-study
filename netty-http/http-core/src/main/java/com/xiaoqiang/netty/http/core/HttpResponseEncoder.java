package com.xiaoqiang.netty.http.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


public class HttpResponseEncoder extends MessageToByteEncoder<HttpResponse> {
    @Override
    protected void encode(ChannelHandlerContext ctx, HttpResponse msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.getVersion().text().getBytes());
        out.writeByte(' ');
        out.writeByte(msg.getStatus().code());
        out.writeByte(' ');
        out.writeBytes(msg.getStatus().reasonPhrase().getBytes());
        out.writeByte('\r');
        out.writeByte('\n');
        msg.getHeaders().forEach((header)->{
            out.writeBytes(header.getKey().getBytes());
            out.writeByte(':');
            out.writeByte(' ');
            out.writeBytes(header.getValue().getBytes());
            out.writeByte('\r');
            out.writeByte('\n');
        });
        out.writeByte('\r');
        out.writeByte('\n');
        out.writeBytes(msg.getBody().getContent());
    }
}
