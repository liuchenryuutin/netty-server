package org.lccy.netty.server;

import io.netty.channel.CombinedChannelDuplexHandler;
import lombok.Getter;
import lombok.Setter;
import org.lccy.netty.interceptor.HandlerInterceptor;

/**
 * 类名称： <br>
 * 类描述： <br>
 *
 * @Date: 2024/03/14 11:21 <br>
 * @author: liuchen11
 */
@Getter
@Setter
public class NettyConfig {

    private String host = "127.0.0.1";
    private int port = 10613;
    private int workThreads = Runtime.getRuntime().availableProcessors();
    private CodecType codec = CodecType.HTTP;
    private CombinedChannelDuplexHandler customCodec;
    private HandlerInterceptor interceptor;
    private int httpMaxContextLength = 10 * 1024 * 1024;
    private boolean allowCors = false;

    public NettyConfig() {

    }

    public enum CodecType {
        HTTP,
        CUSTOM
    }
}
