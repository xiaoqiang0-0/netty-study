package com.xiaoqiang.ns.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

public class Handler2 extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        ctx.pipeline().remove(Handler1.class);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        if (msg.getByte(0)!='2') {
            ctx.fireChannelRead(msg.retain());
        }
        ByteBuf buf = Unpooled.buffer();
        buf.writeCharSequence("This message from Handler2", StandardCharsets.UTF_8);
        ctx.writeAndFlush(buf);
        ctx.close();
    }
}
