package com.xiaoqiang.hss;

import com.xiaoqiang.netty.http.core.HttpRequest;
import org.apache.commons.lang.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StaticResource {
    private static String staticPathPrefix = StaticResource.class.getResource("/").getPath();

    static {
        String envPrefix = System.getenv("resource_path");
        if (StringUtils.isNotEmpty(envPrefix)) {
            staticPathPrefix = envPrefix;
        }
    }

    private String path;
    private String type;
    private byte[] data;
    private HttpRequest request;

    public StaticResource(String path) throws IOException {
        this(path, path.substring(path.lastIndexOf(".") + 1));
    }

    public StaticResource(String path, String type) throws IOException {
        this.path = path;
        this.type = type;

        InputStream in = new FileInputStream(staticPathPrefix + path);
        int size = in.available();
        this.data = new byte[size];
        in.read(this.data);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }
}
