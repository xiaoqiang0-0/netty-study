package com.xiaoqiang.netty.study.http;

public interface Servlet {
    default void service(Request request, Response response) {
    }

    ;
}
