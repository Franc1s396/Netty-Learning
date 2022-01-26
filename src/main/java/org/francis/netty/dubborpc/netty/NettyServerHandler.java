package org.francis.netty.dubborpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.francis.netty.dubborpc.provider.HelloServiceImpl;

/**
 * @author Franc1s
 * @date 2022/1/26
 * @apiNote
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("msg = " + msg);
        String message = msg.toString();
        if (message.startsWith("HelloService#hello#")) {
            String result = new HelloServiceImpl().hello(message.substring(message.lastIndexOf("#") + 1));
            ctx.writeAndFlush(result);
        }else {
            ctx.writeAndFlush("协议错误");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
