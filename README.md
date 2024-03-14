# Netty Server

提供基于Netty通信的基础Server，支持Dispatcher进行请求分发。

## 代码
```java
// 定义请求处理器
@Slf4j
public class DemoController extends AbstractHttpRequestHandler<NettyHttpRequest> {
    @Override
    public NettyHttpResponse handle(NettyHttpRequest request, NettyResponse response) {
        log.info(JSON.parseObject(request.getBody()).toJSONString());
        return ResponseHelper.success("成功");
    }

    @Override
    public String path() {
        return "/netty/demo";
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.GET;
    }
}

// 启动NettyServer,使用http协议
public class BooststrapTest {

    public static void main(String[] args) {
        RequestHandler controller = new DemoController();
        RequestHandlerFactory.registHandler(controller);
        Bootstrap.startServer("localhost", 10613, 1, NettyConfig.CodecType.HTTP);
    }
}
```