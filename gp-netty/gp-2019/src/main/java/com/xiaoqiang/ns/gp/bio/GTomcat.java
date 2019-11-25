package com.xiaoqiang.ns.gp.bio;

import com.xiaoqiang.ns.gp.bio.http.Servlet;
import com.xiaoqiang.ns.gp.bio.http.Request;
import com.xiaoqiang.ns.gp.bio.http.Response;
import com.xiaoqiang.ns.gp.bio.http.WebServlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GTomcat {
    private Map<String, Servlet> servletMap = new ConcurrentHashMap<>();
    private final String host;
    private final int port;

    public GTomcat() {
        this("localhost", 8080);
    }

    public GTomcat(String host, int port) {
        this.port = port;
        this.host = host;

        init();
    }

    private void init() {
        try {
            List<Class> classes = scanClass((this.getClass().getResource("/")).getPath().substring(1), (this.getClass().getResource("/")).getPath().substring(1));
            for (Class aClass : classes) {
                WebServlet ann = (WebServlet) aClass.getAnnotation(WebServlet.class);
                if (ann != null) {
                    servletMap.put(ann.name(), (Servlet) aClass.getConstructor().newInstance());
                }
            }
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(host, port));
            while (true) {
                process(serverSocket.accept());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process(Socket accept) throws IOException {
        InputStream in = accept.getInputStream();
        OutputStream out = accept.getOutputStream();
        Response response = new Response(out);
        try{
            Request request = new Request(in);
            if (servletMap.get(request.getUrl()) == null) {
                response.write("404 - Not found!");
            } else {
                servletMap.get(request.getUrl()).service(request, response);
            }
        } catch (Exception e)
        {
            response.write("500 Server Error!");
        }
        out.close();
        in.close();
        accept.close();
    }

    private static final List<Class> scanClass(String starter, String path) throws ClassNotFoundException {
        List<Class> result = new ArrayList<>();

        File file = new File(path);
        if (path.endsWith(".class")) {
            result.add(Class.forName(path.replace("\\", "/").replace(starter, "").replace("/", ".").replace(".class", "")));
        }
        if (file.isFile()) {
            return result;
        }
        if (file.listFiles() == null) {
            return result;
        }
        for (File listFile : file.listFiles()) {
            result.addAll(scanClass(starter, listFile.getAbsolutePath()));
        }

        return result;
    }

    public static void main(String[] args) {
        new GTomcat().start();
    }
}
