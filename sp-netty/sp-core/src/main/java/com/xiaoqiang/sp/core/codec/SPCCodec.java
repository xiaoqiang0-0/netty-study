package com.xiaoqiang.sp.core.codec;

import io.netty.channel.CombinedChannelDuplexHandler;

public class SPCCodec extends CombinedChannelDuplexHandler<SPCDecoder, SPCEncoder> {
    protected SPCCodec() {
        super(new SPCDecoder(), new SPCEncoder());
    }
}
