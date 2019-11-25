package com.xiaoqiang.ns.gp.bio.http;

import java.io.IOException;

public interface Servlet {
    default void service(Request request, Response response) throws IOException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    void doPost(Request request, Response response) throws IOException;


    void doGet(Request request, Response response) throws IOException;
}
