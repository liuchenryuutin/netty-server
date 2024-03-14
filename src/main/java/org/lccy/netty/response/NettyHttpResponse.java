package org.lccy.netty.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 类名称： <br>
 * 类描述： <br>
 *
 * @Date: 2024/03/15 01:50 <br>
 * @author: liuchen11
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NettyHttpResponse<T> {

    private String code;

    private String msg;

    private T data;

    public NettyHttpResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
