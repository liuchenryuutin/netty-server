package org.lccy.netty.request;

import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.Setter;

/**
 * 类名称： <br>
 * 类描述： <br>
 *
 * @Date: 2024/03/14 12:28 <br>
 * @author: liuchen11
 */
@Getter
@Setter
public class NettyRequest {

    private String router;

    private ChannelHandlerContext ctx;

}
