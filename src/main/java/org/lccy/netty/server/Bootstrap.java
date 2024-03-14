package org.lccy.netty.server;

/**
 * 类名称： <br>
 * 类描述： <br>
 *
 * @Date: 2024/03/14 11:07 <br>
 * @author: liuchen11
 */
public class Bootstrap {

    public static void startServer(String host, int port, int workThreads, NettyConfig.CodecType type) {
        NettyConfig config = new NettyConfig();
        config.setCodec(type);
        config.setHost(host);
        config.setPort(port);
        config.setWorkThreads(workThreads);
        NettyServer server = new NettyServer(config);
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> server.close()));
    }

    public static void startServer(String host, int port, int workThreads) {
        startServer(host, port, workThreads, NettyConfig.CodecType.HTTP);
    }

}
