package com.xiaoqiang.ns.ws;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private final ChannelGroup channels;

    public TextWebSocketFrameHandler(ChannelGroup channels) {
        this.channels = channels;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //TODO 替换WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE 为新的解决方案
        if (evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE) {
            ctx.pipeline().remove(HttpRequestHandler.class);
            channels.writeAndFlush(new TextWebSocketFrame("Client " + ctx.channel() + " joined."));
            channels.add(ctx.channel());
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        channels.writeAndFlush(msg.retain());
    }
}
