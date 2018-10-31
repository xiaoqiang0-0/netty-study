package com.xiaoqiang.netty.gp.homework.chatroom;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 小强
 * @createTime 2018/10/31 23:05
 */
public class ClientSocketHolder {

    private static final List<Socket> sockets = new ArrayList<>();

    private ClientSocketHolder() {
    }

    public synchronized void login(Socket socket) {
        if (sockets.contains(socket)) return;
        sockets.add(socket);
    }

    public synchronized void logout(Socket socket) {
        sockets.remove(socket);
    }

}
