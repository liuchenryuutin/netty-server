package org.lccy.netty.request.http;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import lombok.Getter;
import lombok.Setter;
import org.lccy.netty.request.NettyRequest;

import java.net.URI;

/**
 * 类名称： <br>
 * 类描述： <br>
 *
 * @Date: 2024/03/14 23:53 <br>
 * @author: liuchen11
 */
@Getter
@Setter
public class NettyHttpRequest extends NettyRequest {

    private FullHttpRequest request;
    private String body;
    private URI uri;

    public static String buildRouter(String path, HttpMethod method) {
        return path + "&_&" + method.name();
    }
}
