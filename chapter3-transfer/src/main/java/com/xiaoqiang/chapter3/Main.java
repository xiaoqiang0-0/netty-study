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
        String poiop = System.getProperty("poiop", "10000");
        String pniop = System.getProperty("pniop", "10001");
        String noiop = System.getProperty("noiop", "10002");
        String nniop = System.getProperty("nniop", "10003");

        new Thread(()->{
            try {
                new PlainOioServer().serve(Integer.parseInt(poiop));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                new PlainNioServer().serve(Integer.parseInt(pniop));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                new NettyOioServer().server(Integer.parseInt(noiop));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                new NettyNioServer().server(Integer.parseInt(nniop));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
