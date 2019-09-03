package com.xiaoqiang.netty.http.core;

import io.netty.channel.CombinedChannelDuplexHandler;

public class HttpServerCodec extends CombinedChannelDuplexHandler<HttpRequestDecoder, HttpResponseEncoder> {
}
