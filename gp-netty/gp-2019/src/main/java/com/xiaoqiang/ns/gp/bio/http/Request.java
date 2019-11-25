package com.xiaoqiang.ns.gp.bio.http;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Request {

    private final String method;
    private final String url;

    public Request(InputStream in) throws IOException {
        byte[] data = new byte[in.available()];
        in.read(data);
        String body = new String(data, StandardCharsets.UTF_8);
        this.method = body.split("\n")[0].split(" ")[0];
        this.url = body.split("\n")[0].split(" ")[1].split("\\?")[0];
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
