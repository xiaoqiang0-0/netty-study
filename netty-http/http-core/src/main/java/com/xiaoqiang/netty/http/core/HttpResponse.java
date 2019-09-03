package com.xiaoqiang.netty.http.core;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

public class HttpResponse {
    private HttpVersion version;
    private HttpResponseStatus status;
    private HttpHeaders headers;
    private HttpResponseBody body;

    public HttpVersion getVersion() {
        return version;
    }

    public void setVersion(HttpVersion version) {
        this.version = version;
    }

    public HttpResponseStatus getStatus() {
        return status;
    }

    public void setStatus(HttpResponseStatus status) {
        this.status = status;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public HttpResponseBody getBody() {
        return body;
    }

    public void setBody(HttpResponseBody body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "version=" + version +
                ", status=" + status +
                ", headers=" + headers +
                ", body=" + body +
                '}';
    }
}
