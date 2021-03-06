package com.xiaoqiang.chapter3.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author 小强
 * @createTime 2018/10/25 21:48
 */
public class PlainNioServer {
    public void serve(int port) throws IOException {
        System.out.println("plain nio server start, on port " + port);
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket ssocket = serverChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        ssocket.bind(address);
        //打开selector，用于处理channel
        Selector selector = Selector.open();
        //将channel注册到打开的selector，接收连接
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes(Charset.forName("UTF-8")));
        for (; ; ) {
            try {
                //阻塞，等待新事件传入
                selector.select();
            } catch (IOException ex) {
                ex.printStackTrace();
                break;
            }
            //获取接收事件的Selection-Key实例
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            //遍历所有事件
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                try {
                    //是否是新的已就绪且可被接收的连接
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        //注册到selector
                        client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
                        System.out.printf("[%s] %s 接收来自客户端:%s 的连接。\n", new Date(), getClass().getSimpleName(), client);
                    }
                    //检查写入是否就绪
                    if (key.isWritable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        while (buffer.hasRemaining()) {
                            //写入客户端
                            if (client.write(buffer) == 0) {
                                break;
                            }
                        }
                        client.close();
                    }
                } catch (IOException e) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException ex) {
                        //ignore
                    }
                }
            }
        }
    }
}
