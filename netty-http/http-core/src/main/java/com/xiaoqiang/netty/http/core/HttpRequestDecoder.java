package com.xiaoqiang.netty.http.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;

import java.nio.charset.Charset;
import java.util.List;

public class HttpRequestDecoder extends ReplayingDecoder<HttpRequest> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        HttpRequest request = new HttpRequest();

        request.setMethod(parseMethod(in));
        request.setUri(parseUri(in));
        request.setProtocolVersion(parseVersion(in));
        request.setHeaders(parseHeaders(in));
        out.add(request);
        in.release();
        in = null;
    }

    private HttpHeaders parseHeaders(ByteBuf in) {
        return null;
    }

    private HttpVersion parseVersion(ByteBuf in) {
        StringBuilder version = new StringBuilder();
        while (in.isReadable()) {
            CharSequence c = in.readCharSequence(1, Charset.defaultCharset());
            if (c.equals(" ")||c.equals("\r")) {
                in.readChar();
                return HttpVersion.valueOf(version.toString());
            }
            version.append(c);
        }
        return null;
    }

    private HttpMethod parseMethod(ByteBuf in) {
        StringBuilder method = new StringBuilder();
        while (in.isReadable()) {
            CharSequence c = in.readCharSequence(1, Charset.defaultCharset());
            if (c.equals(" ")) {
                return HttpMethod.valueOf(method.toString());
            }
            method.append(c);
        }
        return null;
    }

    private String parseUri(ByteBuf in) {
        StringBuilder uri = new StringBuilder();
        while (in.isReadable()) {
            CharSequence c = in.readCharSequence(1, Charset.defaultCharset());
            if (c.equals(" ")) {
                return uri.toString();
            }
            uri.append(c);
        }
        return "";
    }

}
