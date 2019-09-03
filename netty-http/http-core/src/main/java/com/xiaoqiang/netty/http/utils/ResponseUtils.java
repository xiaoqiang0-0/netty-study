package com.xiaoqiang.netty.http.utils;

import com.xiaoqiang.netty.http.core.HttpResponse;
import com.xiaoqiang.netty.http.core.HttpResponseBody;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

public class ResponseUtils {
    public static HttpResponse ok(){
        HttpResponse response = new HttpResponse();
        response.setVersion(HttpVersion.HTTP_1_1);
        response.setStatus(HttpResponseStatus.OK);
        response.setBody(new HttpResponseBody(Unpooled.buffer()));
        response.setHeaders(new DefaultHttpHeaders());
        return response;
    }
}
