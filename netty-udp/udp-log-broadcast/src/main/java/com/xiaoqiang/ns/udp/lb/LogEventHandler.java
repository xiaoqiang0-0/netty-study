package com.xiaoqiang.ns.udp.lb;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogEventHandler extends SimpleChannelInboundHandler<LogEvent> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogEvent msg) throws Exception {
        String s = msg.getReceived() +
                " [" +
                msg.getSource().toString() +
                "] [" +
                msg.getLogFile() +
                "] : " +
                msg.getMsg();
        System.out.println(s);
    }
}
