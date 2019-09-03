package com.xiaoqiang.netty.http.core;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.ByteProcessor;

public interface HttpResponseParser extends HttpMessageParser {

    default ByteBuf parseContent(ByteBuf in) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        buf.writeBytes(in);
        return buf;
    }
    default HttpVersion parseVersion(ByteBuf byteBuf) {
        int endIndex = byteBuf.forEachByte(ByteProcessor.FIND_ASCII_SPACE);
        HttpVersion version = HttpVersion.valueOf(byteBuf.readCharSequence(endIndex - byteBuf.readerIndex(), UTF8).toString());
        byteBuf.skipBytes(1);
        return version;
    }

    default HttpResponseStatus parseStatus(ByteBuf byteBuf) {
        int endIndex = byteBuf.forEachByte(ByteProcessor.FIND_ASCII_SPACE);
        HttpResponseStatus status = HttpResponseStatus.valueOf(Integer.parseInt(byteBuf.readCharSequence(endIndex - byteBuf.readerIndex(), UTF8).toString()));
        byteBuf.skipBytes(status.reasonPhrase().length() + 3);
        return status;
    }
}
