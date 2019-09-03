package com.xiaoqiang.netty.http.core;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;

import static io.netty.util.ByteProcessor.FIND_ASCII_SPACE;
import static io.netty.util.ByteProcessor.FIND_CR;

public interface HttpRequestParser extends HttpMessageParser {

    default ByteBuf parseContent(ByteBuf in) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        buf.writeBytes(in);
        return buf;
    }

    default HttpVersion parseVersion(ByteBuf in) {
        int startIndex = in.readerIndex();
        int blankIndex = in.forEachByte(FIND_CR);
        String versionName = in.readCharSequence(blankIndex - startIndex, UTF8).toString();
        HttpVersion httpVersion = HttpVersion.valueOf(versionName);
        in.skipBytes(2);
        return httpVersion;
    }

    default HttpMethod parseMethod(ByteBuf in) {
        int startIndex = in.readerIndex();
        int blankIndex = in.forEachByte(FIND_ASCII_SPACE);
        String methodName = in.readCharSequence(blankIndex - startIndex, UTF8).toString();
        HttpMethod method = HttpMethod.valueOf(methodName);
        in.skipBytes(1);
        return method;
    }

    default String parseUri(ByteBuf in) {
        int startIndex = in.readerIndex();
        int blankIndex = in.forEachByte(FIND_ASCII_SPACE);
        String uri = in.readCharSequence(blankIndex - startIndex, UTF8).toString();
        in.skipBytes(1);
        return uri;
    }
}
