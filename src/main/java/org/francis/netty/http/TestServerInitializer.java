package org.francis.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author Franc1s
 * @date 2022/1/20
 * @apiNote
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("myHttpServerCodec",new HttpServerCodec());
        pipeline.addLast("myTestHttpServerHandler",new TestHttpServerHandler());
    }
}
