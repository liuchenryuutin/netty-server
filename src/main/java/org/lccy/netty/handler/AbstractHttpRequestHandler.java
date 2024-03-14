package org.lccy.netty.handler;

import io.netty.handler.codec.http.HttpMethod;
import org.lccy.netty.request.NettyRequest;
import org.lccy.netty.request.http.NettyHttpRequest;
import org.lccy.netty.response.NettyHttpResponse;
import org.lccy.netty.response.NettyResponse;

/**
 * 类名称： <br>
 * 类描述： <br>
 *
 * @Date: 2024/03/15 02:05 <br>
 * @author: liuchen11
 */
public abstract class AbstractHttpRequestHandler<REQ extends NettyRequest> implements RequestHandler<REQ, NettyResponse> {

    @Override
    public NettyResponse handle(REQ request) {
        NettyResponse response = new NettyResponse();
        response.setBody(handle(request, response));
        return response;
    }

    @Override
    public String router() {
        return NettyHttpRequest.buildRouter(path(), method());
    }

    public abstract NettyHttpResponse handle(REQ request, NettyResponse response);

    public abstract String path();

    public abstract HttpMethod method();
}
