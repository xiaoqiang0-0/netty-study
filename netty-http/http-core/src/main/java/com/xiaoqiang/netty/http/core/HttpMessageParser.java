package com.xiaoqiang.netty.http.core;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.ByteProcessor;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static io.netty.util.ByteProcessor.FIND_CR;

public interface HttpMessageParser {
    Charset UTF8 = StandardCharsets.UTF_8;

    HttpVersion parseVersion(ByteBuf in);

    ByteBuf parseContent(ByteBuf in);

    default HttpHeaders parseHeaders(ByteBuf in) {
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
}
