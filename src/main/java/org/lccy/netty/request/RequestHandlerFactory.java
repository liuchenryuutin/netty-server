package org.lccy.netty.request;

import org.lccy.netty.exception.NettyException;
import org.lccy.netty.handler.RequestHandler;
import org.lccy.netty.util.StringUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类名称： <br>
 * 类描述： <br>
 *
 * @Date: 2024/03/14 12:26 <br>
 * @author: liuchen11
 */
public class RequestHandlerFactory {

    private static Map<String, RequestHandler> handlerMap = new ConcurrentHashMap<>();

    public static RequestHandler getHandler(String router) {
        RequestHandler requestHandler = handlerMap.get(router);
        return requestHandler;
    }

    public static void registHandler(RequestHandler handler) throws NettyException {
        String key = handler.router();
        if(StringUtil.isEmpty(key)) {
            throw new NettyException("handler router is empty, please check.");
        }
        if (handlerMap.containsKey(key)) {
            throw new NettyException("Exists duplicate handlers in the context, please check.");
        }
        handlerMap.put(key, handler);
    }

    public static void registHandler(List<RequestHandler> handlers) throws NettyException {
        String key;
        for(RequestHandler handler : handlers) {
            key = handler.router();
            if(StringUtil.isEmpty(key)) {
                throw new NettyException("handler router is empty, please check.");
            }
            if (handlerMap.containsKey(key)) {
                throw new NettyException("Exists duplicate handlers in the context, please check.");
            }
            handlerMap.put(key, handler);
        }

    }
}