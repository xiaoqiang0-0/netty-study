package com.xiaoqiang.netty.http.core;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class HttpResponseBody {

    protected ByteBuf content;

    public HttpResponseBody(String content) {
        this.content = Unpooled.wrappedBuffer(content.getBytes());
    }

    public HttpResponseBody(ByteBuf content) {
        this.content = content;
    }

    public HttpResponseBody(byte[] content) {
        this.content = Unpooled.wrappedBuffer(content);
    }

    public ByteBuf getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "HttpResponseBody{" +
                "content=" + content.toString() +
                '}';
    }
}
