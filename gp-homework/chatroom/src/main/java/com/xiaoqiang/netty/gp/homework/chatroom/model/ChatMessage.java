package com.xiaoqiang.netty.gp.homework.chatroom.model;

import lombok.Data;

/**
 * @author 小强
 * @createTime 2018/10/31 22:59
 */
@Data
public class ChatMessage {
    private Integer id;
    private Address from;
    private Address to;
}
