package com.xiaoqiang.netty.gp.homework.chatroom;

import java.net.Socket;

/**
 * @author 小强
 * @createTime 2018/10/31 22:57
 */
public class MessageHandler implements Runnable {

    private Socket socket;

    public MessageHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

    }
}
