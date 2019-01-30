package com.xiaoqiang.netty.study.http;

public class ServletFactory {

    public Servlet newServlet(){
        return new Servlet() {
            @Override
            public void service(Request request, Response response) {

            }
        };
    }
}
