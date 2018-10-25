package com.xiaoqiang.chapter3;

import com.xiaoqiang.chapter3.java.nio.PlainNioServer;
import com.xiaoqiang.chapter3.java.oio.PlainOioServer;
import com.xiaoqiang.chapter3.netty.nio.NettyNioServer;
import com.xiaoqiang.chapter3.netty.oio.NettyOioServer;

import java.io.IOException;

/**
 * @author 小强
 * @createTime 2018/10/25 22:43
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        var poiop = System.getProperty("poiop", "10000");
        var pniop = System.getProperty("poiop", "10001");
        var noiop = System.getProperty("poiop", "10002");
        var nniop = System.getProperty("poiop", "10003");

        new PlainOioServer().serve(Integer.parseInt(poiop));
        new PlainNioServer().serve(Integer.parseInt(pniop));
        new NettyOioServer().server(Integer.parseInt(noiop));
        new NettyNioServer().server(Integer.parseInt(nniop));
    }
}
