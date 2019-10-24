package com.xiaoqiang.ns.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;

public class CloneDemo {
    public static void main(String[] args) {
        ByteBuf buf = UnpooledByteBufAllocator.DEFAULT.buffer(4, 1024);
        buf.writeInt(1);
        buf.writeInt(2);
        System.out.printf("buf readable:[%d], cap:[%d]\n", buf.readableBytes(), buf.capacity());
        System.out.println(buf.readInt());
        ByteBuf newBuf = buf.copy();
        System.out.printf("new buf readable:[%d], cap:[%d]\n", newBuf.readableBytes(), newBuf.capacity());
    }
}
