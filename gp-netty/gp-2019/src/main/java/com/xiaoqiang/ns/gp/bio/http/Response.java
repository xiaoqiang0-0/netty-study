package com.xiaoqiang.ns.gp.bio.http;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Response {
    private final OutputStream outputStream;

    public Response(OutputStream out) {
        outputStream = out;
    }

    public void write(String s) throws IOException {
        outputStream.write(("http/1.1" + " " + "200" + " " + "ok" + "\r\n" + "\r\n" + s).getBytes(StandardCharsets.UTF_8));
    }
}
