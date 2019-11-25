package com.xiaoqiang.ns.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

public class Handler1 extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        if (msg.getByte(0)!='1') {
            ctx.fireChannelRead(msg.retain());
        }
        ByteBuf buf = Unpooled.buffer();
        buf.writeCharSequence("This message from Handler1", StandardCharsets.UTF_8);
        ctx.writeAndFlush(buf);
        ctx.close();
    }
}
