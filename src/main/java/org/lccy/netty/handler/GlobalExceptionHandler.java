package org.lccy.netty.handler;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.lccy.netty.exception.NettyException;
import org.lccy.netty.resolver.ReqResResolver;
import org.lccy.netty.response.NettyResponse;
import org.lccy.netty.util.ResponseHelper;

/**
 * 类名称： <br>
 * 类描述： <br>
 *
 * @Date: 2024/03/14 13:06 <br>
 * @author: liuchen11
 */
@Slf4j
public class GlobalExceptionHandler extends ChannelInboundHandlerAdapter {

    private ReqResResolver reqResResolver;

    public GlobalExceptionHandler(ReqResResolver reqResResolver) {
        this.reqResResolver = reqResResolver;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("Exception: {}", cause.getMessage(), cause);

        String msg = ResponseHelper.FAIL_MSG;
        if (cause instanceof NettyException) {
            msg = cause.getMessage();
        }
        ctx.writeAndFlush(reqResResolver.resolveRes(new NettyResponse(ResponseHelper.fail(ResponseHelper.FAIL, msg)))).addListener(ChannelFutureListener.CLOSE);
    }
}