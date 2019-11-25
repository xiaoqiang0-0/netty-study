package com.xiaoqiang.ns.gp.bio.serlvet;

import com.xiaoqiang.ns.gp.bio.http.Request;
import com.xiaoqiang.ns.gp.bio.http.Response;
import com.xiaoqiang.ns.gp.bio.http.Servlet;
import com.xiaoqiang.ns.gp.bio.http.WebServlet;

import java.io.IOException;

@WebServlet(name = "/")
public class HelloWorldServlet implements Servlet {
    @Override
    public void doPost(Request request, Response response) throws IOException {
        doGet(request, response);
    }

    @Override
    public void doGet(Request request, Response response) throws IOException {
        response.write("Hello World!");
    }
}
