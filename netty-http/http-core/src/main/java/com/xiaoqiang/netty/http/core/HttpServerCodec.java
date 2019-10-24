package com.xiaoqiang.netty.http.core;

import io.netty.channel.CombinedChannelDuplexHandler;

public class HttpServerCodec extends CombinedChannelDuplexHandler<HttpRequestDecoder, HttpResponseEncoder> {
    public HttpServerCodec() {
        init(new HttpRequestDecoder(), new HttpResponseEncoder());
    }

    public HttpServerCodec(HttpRequestDecoder inboundHandler, HttpResponseEncoder outboundHandler) {
        super(inboundHandler, outboundHandler);
    }
}
