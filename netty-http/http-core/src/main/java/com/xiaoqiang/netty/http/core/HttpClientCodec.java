package com.xiaoqiang.netty.http.core;

import io.netty.channel.CombinedChannelDuplexHandler;

public class HttpClientCodec extends CombinedChannelDuplexHandler<HttpResponseDecoder, HttpRequestEncoder> {
    public HttpClientCodec() {
        init(new HttpResponseDecoder(), new HttpRequestEncoder());
    }

    public HttpClientCodec(HttpResponseDecoder inboundHandler, HttpRequestEncoder outboundHandler) {
        super(inboundHandler, outboundHandler);
    }
}
