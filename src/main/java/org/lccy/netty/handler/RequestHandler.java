package org.lccy.netty.handler;

import org.lccy.netty.request.NettyRequest;
import org.lccy.netty.response.NettyResponse;

/**
 * 接口名称： <br>
 * 接口描述： <br>
 *
 * @Date: 2024/03/14 12:38 <br>
 * @author: liuchen11
 */
public interface RequestHandler<REQ extends NettyRequest, RES extends NettyResponse> {
    RES handle(REQ request);

    String router();
}
