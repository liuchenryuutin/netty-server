package org.lccy.netty.handler;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import org.lccy.netty.exception.NettyException;
import org.lccy.netty.interceptor.HandlerInterceptor;
import org.lccy.netty.request.NettyRequest;
import org.lccy.netty.resolver.ReqResResolver;
import org.lccy.netty.request.RequestHandlerFactory;
import org.lccy.netty.response.NettyResponse;
import org.lccy.netty.server.NettyConfig;

/**
 * 类名称： <br>
 * 类描述： <br>
 *
 * @Date: 2024/03/14 12:17 <br>
 * @author: liuchen11
 */
@ChannelHandler.Sharable
public class DispatcherHandler extends SimpleChannelInboundHandler<Object> {

    private HandlerInterceptor interceptor;
    private ReqResResolver reqResResolver;

    public DispatcherHandler(NettyConfig config, ReqResResolver reqResResolver) {
        this.interceptor = config.getInterceptor();
        this.reqResResolver = reqResResolver;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        NettyRequest request = null;
        if (msg instanceof FullHttpRequest) {
            try {
                request = reqResResolver.resolveReq(msg);
                request.setCtx(ctx);
            } catch (Exception e) {
                ctx.fireExceptionCaught(new NettyException("http uri error"));
                return ;
            }
        }

        if(request == null) {
            ctx.fireExceptionCaught(new NettyException("receiver message fail, the complete request was not processed"));
            return ;
        }
        RequestHandler requestHandler = RequestHandlerFactory.getHandler(request.getRouter());
        if(requestHandler == null) {
            ctx.fireExceptionCaught(new NettyException("handle message fail, the complete request was not processed"));
            return ;
        }

        NettyResponse response;
        try {
            if (interceptor != null) {
                interceptor.preHandle(ctx, request, requestHandler);
            }
            response = requestHandler.handle(request);
            if (interceptor != null) {
                interceptor.postHandle(ctx, request, response, requestHandler);
            }

            Object result = reqResResolver.resolveRes(response);
            ctx.writeAndFlush(result).addListener(ChannelFutureListener.CLOSE);
        } catch (Exception e) {
            ctx.fireExceptionCaught(e);
        }
    }
}