package com.xiaoqiang.netty.http.core;

import io.netty.handler.codec.LineBasedFrameDecoder;

public class LineDecoder extends LineBasedFrameDecoder {
    public LineDecoder(int maxLength) {
        super(maxLength);
    }

    public LineDecoder(int maxLength, boolean stripDelimiter, boolean failFast) {
        super(maxLength, stripDelimiter, failFast);
    }
}
