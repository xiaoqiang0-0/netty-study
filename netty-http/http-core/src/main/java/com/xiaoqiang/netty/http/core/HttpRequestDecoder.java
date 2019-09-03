package com.xiaoqiang.netty.http.core;

import com.xiaoqiang.netty.http.utils.ResponseUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.ByteProcessor;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static io.netty.util.ByteProcessor.FIND_ASCII_SPACE;
import static io.netty.util.ByteProcessor.FIND_CR;


/**
 * http request decoder
 */
public class HttpRequestDecoder extends ReplayingDecoder<HttpRequest> {
    private static final Charset UTF8 = StandardCharsets.UTF_8;
    public static final int MAX_REQUEST_LEN = 1024 * 1024 * 10;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() > MAX_REQUEST_LEN) {
            out.add(ResponseUtils.ok());
            in.release();
            in = null;
            return;
        }
        HttpRequest request = new HttpRequest();

        request.setMethod(parseMethod(in));
        request.setUri(parseUri(in));
        request.setProtocolVersion(parseVersion(in));
        request.setHeaders(parseHeaders(in));
        request.setContent(parseContent(in));
        out.add(request);
    }

    private ByteBuf parseContent(ByteBuf in) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(128);
        buf.writeBytes(in);
        return buf;
    }

    private HttpHeaders parseHeaders(ByteBuf in) {
        HttpHeaders headers = new DefaultHttpHeaders();
        for (; ; ) {
            int startIndex = in.readerIndex();
            int valueStartIndex = in.forEachByte(new ByteProcessor.IndexOfProcessor((byte) ':')) - startIndex;
            int lineEndIndex = in.forEachByte(FIND_CR) - startIndex;
            String headerName = in.readCharSequence(valueStartIndex, UTF8).toString();
            in.skipBytes(2);
            String headerValue = in.readCharSequence(lineEndIndex - valueStartIndex - 2, UTF8).toString();
            in.skipBytes(2);
            headers.add(headerName, headerValue);
            if (in.getByte(in.readerIndex()) == (int) '\r') {
                in.skipBytes(2);
                break;
            }
        }
        return headers;
    }

    private HttpVersion parseVersion(ByteBuf in) {
        int startIndex = in.readerIndex();
        int blankIndex = in.forEachByte(FIND_CR);
        String versionName = in.readCharSequence(blankIndex - startIndex, UTF8).toString();
        HttpVersion httpVersion = HttpVersion.valueOf(versionName);
        in.skipBytes(2);
        return httpVersion;
    }

    private HttpMethod parseMethod(ByteBuf in) {
        int startIndex = in.readerIndex();
        int blankIndex = in.forEachByte(FIND_ASCII_SPACE);
        String methodName = in.readCharSequence(blankIndex - startIndex, UTF8).toString();
        HttpMethod method = HttpMethod.valueOf(methodName);
        in.skipBytes(1);
        return method;
    }

    private String parseUri(ByteBuf in) {
        int startIndex = in.readerIndex();
        int blankIndex = in.forEachByte(FIND_ASCII_SPACE);
        String uri = in.readCharSequence(blankIndex - startIndex, UTF8).toString();
        in.skipBytes(1);
        return uri;
    }

}
