package org.lccy.netty.interceptor;

import io.netty.channel.ChannelHandlerContext;
import org.lccy.netty.request.NettyRequest;
import org.lccy.netty.handler.RequestHandler;
import org.lccy.netty.response.NettyResponse;

/**
 * 类名称： <br>
 * 类描述： <br>
 *
 * @Date: 2024/03/14 12:51 <br>
 * @author: liuchen11
 */
public interface HandlerInterceptor {

    default boolean preHandle(ChannelHandlerContext ctx, NettyRequest request, RequestHandler handler)
            throws Exception {
        return true;
    }

    default void postHandle(ChannelHandlerContext ctx, NettyRequest request, NettyResponse response, RequestHandler handler) throws Exception {
    }
}
