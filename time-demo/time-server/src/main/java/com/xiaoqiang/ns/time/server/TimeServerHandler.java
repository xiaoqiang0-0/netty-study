package com.xiaoqiang.ns.time.server;

import com.xiaoqiang.ns.time.core.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

@ChannelHandler.Sharable
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelFuture f = ctx.writeAndFlush(new UnixTime((int) (System.currentTimeMillis() / 1000 + 2208988800L)));
        f.addListener((ChannelFutureListener) future -> {
            assert f == future;
            ctx.close();
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
