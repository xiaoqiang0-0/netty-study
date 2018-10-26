package com.xiaoqiang.chapter3.java.oio;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author 小强
 * @createTime 2018/10/25 21:30
 */
public class PlainOioServer {
    public void serve(int port) throws IOException {
        System.out.println("plain oio server start, on port " + port);
        var socket = new ServerSocket(port);
        for (; ; ) {
            var client = socket.accept();
            System.out.printf("[%s] %s 接收来自客户端:%s 的连接。\n", new Date(), getClass().getSimpleName(), client.getRemoteSocketAddress());
            new Thread(() -> {
                try (var out = client.getOutputStream()) {
                    out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
