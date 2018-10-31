package com.xiaoqiang.netty.gp.homework.chatroom;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 小强
 * @createTime 2018/10/31 22:47
 */
@Slf4j
public class Server {

    private static int DEFAULT_SERVER_PORT = 12345;
    private static ServerSocket serverSocket;

    public static void start() {
        start(DEFAULT_SERVER_PORT);
    }

    public synchronized static void start(int port) {
        if (serverSocket != null) return;
        try {
            serverSocket = new ServerSocket(port);
            log.info("服务器启动，端口为：{}", port);
            while (true) {
                Socket socket = serverSocket.accept();
                log.info("收到来自{}的连接", socket.getReuseAddress());
                new Thread(new MessageHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            serverSocket = null;
        }
    }
}
