package com.xiaoqiang.netty.http.core;

import io.netty.channel.CombinedChannelDuplexHandler;

public class HttpClientCodec extends CombinedChannelDuplexHandler<HttpResponseDecoder, HttpRequestEncoder> {
}
