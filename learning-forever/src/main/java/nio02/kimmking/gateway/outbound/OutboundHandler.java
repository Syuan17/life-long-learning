package nio02.kimmking.gateway.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import nio02.kimmking.gateway.filter.HttpRequestFilter;

/**
 * @Author: Syuan
 * @Date: 2021/8/22 11:53 下午
 */
public interface OutboundHandler {

    void handle(FullHttpRequest fullRequest, ChannelHandlerContext ctx, HttpRequestFilter filter);

}
