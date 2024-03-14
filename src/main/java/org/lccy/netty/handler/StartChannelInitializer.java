package org.lccy.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.lccy.netty.resolver.ReqResResolver;
import org.lccy.netty.resolver.HttpReqResResolver;
import org.lccy.netty.server.NettyConfig;

/**
 * 类名称： <br>
 * 类描述： <br>
 *
 * @Date: 2024/03/14 11:00 <br>
 * @author: liuchen11
 */
public class StartChannelInitializer extends ChannelInitializer<SocketChannel> {

    private NettyConfig config;

    public StartChannelInitializer(NettyConfig config) {
        this.config = config;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ReqResResolver reqResResolver = null;
        switch (config.getCodec()) {
            case HTTP:
                socketChannel.pipeline()
                        // request 编解码
                        .addLast("HttpServerCodec", new HttpServerCodec())
                        // 合成完整报文
                        .addLast("HttpObjectAggregator", new HttpObjectAggregator(config.getHttpMaxContextLength()));
                reqResResolver = new HttpReqResResolver(config);
                break;
            case CUSTOM:
                socketChannel.pipeline()
                        // request 编解码
                        .addLast("CustomCodec", config.getCustomCodec());
                break;
        }

        socketChannel.pipeline()
                //自定义处理器
                .addLast("dispatcherHandler", new DispatcherHandler(config, reqResResolver))
                //异常处理
                .addLast("exceptionHandler",new GlobalExceptionHandler(reqResResolver));
    }
}
