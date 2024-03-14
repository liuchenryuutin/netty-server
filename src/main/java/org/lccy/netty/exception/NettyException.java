package org.lccy.netty.exception;

/**
 * 类名称： <br>
 * 类描述： <br>
 *
 * @Date: 2024/03/14 13:02 <br>
 * @author: liuchen11
 */
public class NettyException extends RuntimeException {

    public NettyException(String message) {
        super(message);
    }

    public NettyException(Exception oriEx) {
        super(oriEx);
    }

    public NettyException(Throwable oriEx) {
        super(oriEx);
    }

    public NettyException(String message, Exception oriEx) {
        super(message, oriEx);
    }

    public NettyException(String message, Throwable oriEx) {
        super(message, oriEx);
    }
}
