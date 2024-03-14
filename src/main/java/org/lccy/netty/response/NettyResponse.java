package org.lccy.netty.response;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名称： <br>
 * 类描述： <br>
 *
 * @Date: 2024/03/14 12:41 <br>
 * @author: liuchen11
 */
@Getter
@Setter
public class NettyResponse {

    private Map<String, String> headers;

    private Object body;

    public NettyResponse() {
    }

    public NettyResponse(Object body) {
        this.body = body;
    }

    public NettyResponse(Map<String, String> headers, Object body) {
        this.headers = headers;
        this.body = body;
    }

    public void addHeader(String name, String value) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        headers.put(name, value);
    }

}
