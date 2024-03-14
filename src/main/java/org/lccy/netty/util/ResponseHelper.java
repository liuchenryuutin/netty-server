package org.lccy.netty.util;

import org.lccy.netty.response.NettyHttpResponse;

/**
 * 类名称： <br>
 * 类描述： <br>
 *
 * @Date: 2024/03/14 21:22 <br>
 * @author: liuchen11
 */
public class ResponseHelper {

    public static final String SUCCESS = "000000";
    public static final String SUCCESS_MSG = "success";
    public static final String FAIL = "999999";
    public static final String FAIL_MSG = "system error";

    public static <T> NettyHttpResponse success(T data) {
        return success(SUCCESS, SUCCESS_MSG, data);
    }

    public static <T> NettyHttpResponse success(String code, String msg, T data) {
        return new NettyHttpResponse(code, msg, data);
    }

    public static <T> NettyHttpResponse fail() {
        return fail(FAIL, FAIL_MSG);
    }

    public static <T> NettyHttpResponse fail(String code, String msg) {
        return new NettyHttpResponse(code, msg);
    }
}
