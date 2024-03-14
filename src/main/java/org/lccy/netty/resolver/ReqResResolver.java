package org.lccy.netty.resolver;

import org.lccy.netty.request.NettyRequest;
import org.lccy.netty.response.NettyResponse;

/**
 * 类名称： <br>
 * 类描述： <br>
 *
 * @Date: 2024/03/15 01:05 <br>
 * @author: liuchen11
 */
public interface ReqResResolver<REQ, RES> {

    NettyRequest resolveReq(REQ req) throws Exception;

    RES resolveRes(NettyResponse res);
}
