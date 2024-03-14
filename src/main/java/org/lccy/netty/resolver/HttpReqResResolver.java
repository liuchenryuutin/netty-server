package org.lccy.netty.resolver;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import org.lccy.netty.request.NettyRequest;
import org.lccy.netty.request.http.NettyHttpRequest;
import org.lccy.netty.response.NettyResponse;
import org.lccy.netty.server.NettyConfig;
import org.lccy.netty.util.StringUtil;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 类名称： <br>
 * 类描述： <br>
 *
 * @Date: 2024/03/15 01:09 <br>
 * @author: liuchen11
 */
public class HttpReqResResolver implements ReqResResolver<FullHttpRequest, FullHttpResponse> {

    private NettyConfig config;

    public HttpReqResResolver(NettyConfig config) {
        this.config = config;
    }

    @Override
    public NettyRequest resolveReq(FullHttpRequest httpRequest) throws Exception {
        NettyHttpRequest request = new NettyHttpRequest();

        URI uri = new URI(httpRequest.uri());
        request.setUri(uri);
        request.setRouter(NettyHttpRequest.buildRouter(uri.getPath(), httpRequest.method()));
        request.setBody(httpRequest.content().toString(StandardCharsets.UTF_8));

        request.setRequest(httpRequest);
        return request;
    }

    @Override
    public FullHttpResponse resolveRes(NettyResponse res) {
        Object body = res.getBody();
        String bodyStr;
        if (body instanceof String) {
            bodyStr = StringUtil.conver2String(body);
        } else {
            bodyStr = JSON.toJSONString(body);
        }
        byte[] bytes = bodyStr.getBytes(StandardCharsets.UTF_8);
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(bytes));
        HttpHeaders headers = response.headers();
        headers.set(HttpHeaderNames.CONTENT_TYPE, "application/json; charset=UTF-8");
        headers.set(HttpHeaderNames.CONTENT_LENGTH, bytes.length);
        if (config.isAllowCors()) {
            headers.set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        }
        Map<String, String> customHeaders = res.getHeaders();
        if (customHeaders != null && !customHeaders.isEmpty()) {
            customHeaders.forEach((name, value) -> headers.set(name, value));
        }
        return response;
    }
}
