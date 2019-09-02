package com.xiaoqiang.netty.http.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.ByteProcessor;

import java.nio.charset.Charset;
import java.util.List;

public class HttpRequestDecoder extends ReplayingDecoder<HttpRequest> {
    private static final Charset UTF8=Charset.forName("UTF-8");
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        HttpRequest request = new HttpRequest();

        request.setMethod(parseMethod(in));
        request.setUri(parseUri(in));
        request.setProtocolVersion(parseVersion(in));
        request.setHeaders(parseHeaders(in));
        request.setContent(parseContent(in));
        out.add(request);
        in.release();
        in = null;
    }

    private ByteBuf parseContent(ByteBuf in) {
        return in.copy();
    }

    private HttpHeaders parseHeaders(ByteBuf in) {
        HttpHeaders headers = new DefaultHttpHeaders();
        for (int i = 0; i < in.readableBytes(); i++) {
            int startIndex = in.readerIndex();
            int valueStartIndex = in.forEachByte(new ByteProcessor.IndexOfProcessor((byte) ':'))-startIndex;
            int lineEndIndex=in.forEachByte(new ByteProcessor.IndexOfProcessor((byte) '\r'))-startIndex;
            String headerName = in.readCharSequence(valueStartIndex, Charset.forName("UTF-8")).toString();
            in.skipBytes(2);
            String headerValue = in.readCharSequence(lineEndIndex-valueStartIndex-2, Charset.forName("UTF-8")).toString();
            in.skipBytes(2);
            headers.add(headerName, headerValue);
            if (in.getByte(i)=='\r') {
                in.skipBytes(2);
                break;
            }
            i = in.readerIndex()-startIndex;
        }
        return headers;
    }

    private HttpVersion parseVersion(ByteBuf in) {
        int startIndex = in.readerIndex();
        int blankIndex = in.forEachByte(new ByteProcessor.IndexOfProcessor((byte) '\r'));
        String versionName = in.readCharSequence(blankIndex - startIndex, UTF8).toString();
        HttpVersion httpVersion = HttpVersion.valueOf(versionName);
        in.skipBytes(2);
        return httpVersion;
    }

    private HttpMethod parseMethod(ByteBuf in) {
        int startIndex = in.readerIndex();
        int blankIndex = in.forEachByte(new ByteProcessor.IndexOfProcessor((byte) ' '));
        String methodName = in.readCharSequence(blankIndex - startIndex, UTF8).toString();
        HttpMethod method = HttpMethod.valueOf(methodName);
        in.skipBytes(1);
        return method;
    }

    private String parseUri(ByteBuf in) {
        int startIndex = in.readerIndex();
        int blankIndex = in.forEachByte(new ByteProcessor.IndexOfProcessor((byte) ' '));
        String uri = in.readCharSequence(blankIndex-startIndex, UTF8).toString();
        in.skipBytes(1);
        return uri;
    }

}
