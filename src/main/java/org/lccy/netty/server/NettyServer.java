package org.lccy.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.lccy.netty.handler.StartChannelInitializer;
import org.lccy.netty.util.StringUtil;

/**
 * 类名称： Netty Server<br>
 * 类描述： <br>
 *
 * @Date: 2024/03/14 10:43 <br>
 * @author: liuchen11
 */
@Slf4j
public class NettyServer {

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private Channel serverChannel;
    private NettyConfig config;

    public NettyServer(NettyConfig config) {
        this.config = config;
    }

    public ChannelFuture start() {
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup(config.getWorkThreads());
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new StartChannelInitializer(config));
        log.info("Startup netty server");
        ChannelFuture channelFuture;
        String host = config.getHost();
        int port = config.getPort();
        if (StringUtil.isNotEmpty(host)) {
            channelFuture = bootstrap.bind(host, port).addListener(future -> log.info("Netty server started listening on {}:{} ", host, port));
        } else {
            channelFuture = bootstrap.bind(port).addListener(future -> log.info("Netty server started listening on {}", port));
        }
        serverChannel = channelFuture.channel();
        return channelFuture;
    }

    public void close() {
        log.info("Shutting down netty server");
        if (serverChannel == null) {
            log.info("Cannot close a non running server");
        } else {
            serverChannel.close().syncUninterruptibly();
        }
        if (bossGroup != null) {
            bossGroup.shutdownGracefully().syncUninterruptibly();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully().syncUninterruptibly();
        }
    }
}
