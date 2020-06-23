package com.xiaoqiang.hss;

import com.xiaoqiang.netty.http.core.HttpRequest;
import com.xiaoqiang.netty.http.core.HttpResponse;
import com.xiaoqiang.netty.http.core.HttpResponseBody;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.List;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;

public class StaticResourceEncoder extends MessageToMessageEncoder<StaticResource> {
    @Override
    protected void encode(ChannelHandlerContext ctx, StaticResource msg, List<Object> out) throws Exception {
        HttpResponse response = new HttpResponse();
        HttpRequest request = msg.getRequest();
        HttpHeaders headers = new DefaultHttpHeaders();
        headers.add(CONTENT_TYPE, ResourceType.typeOf(msg.getType()));
        response.setHeaders(headers);
        response.setStatus(HttpResponseStatus.OK);
        response.setVersion(request.getProtocolVersion());
        response.setBody(new HttpResponseBody(msg.getData()));
        out.add(response);
    }
}
