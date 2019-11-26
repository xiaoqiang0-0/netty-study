package com.xiaoqiang.sp.core.codec;

import io.netty.channel.CombinedChannelDuplexHandler;

public class SPSCodec extends CombinedChannelDuplexHandler<SPSDecoder, SPSEncoder> {
    protected SPSCodec() {
        super(new SPSDecoder(), new SPSEncoder());
    }
}
