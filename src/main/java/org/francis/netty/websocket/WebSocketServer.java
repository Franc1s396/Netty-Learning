package org.francis.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.francis.netty.heartbeat.MyServerHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author Franc1s
 * @date 2022/1/22
 * @apiNote
 */
public class WebSocketServer {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(); //8个NioEventLoop
        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    //基于Http协议，使用http编码解码器
                    pipeline.addLast(new HttpServerCodec());
                    pipeline.addLast(new ChunkedWriteHandler());
                    /**
                     * 1.http数据在传输过程中是分段,HttpObjectAggregator,就是可以将多个段聚合
                     * 2.这就是为什么，当浏览器发送大量数据时，就会发出多次HTTP请求
                     */
                    pipeline.addLast(new HttpObjectAggregator(8192));
                    /**
                     * 1.对应websocket，他的数据是以帧(frame)的形式传递
                     * 2.可以看到websocketFrame下面有6个子类
                     * 3.浏览器请求时 ws://localhost:7000/xxx 表示请求的uri
                     * 4.WebSocketServerProtocolHandler 核心功能是将http协议升级为ws协议，保持长连接
                     */
                    pipeline.addLast(new WebSocketServerProtocolHandler("/"));
                }
            });
            //启动服务器
            ChannelFuture channelFuture = serverBootstrap.bind(7000).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
