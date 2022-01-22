package org.francis.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Franc1s
 * @date 2022/1/21
 * @apiNote
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[Client]" + channel.remoteAddress() + " join the chat room"
                + LocalDateTime.now().format(DATE_TIME_FORMATTER) + "\n");
        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channelGroup.writeAndFlush("[Client]" + ctx.channel().remoteAddress() + "exit the chat room"
                + LocalDateTime.now().format(DATE_TIME_FORMATTER) + "\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "is online!" + LocalDateTime.now().format(DATE_TIME_FORMATTER));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "is offline!" + LocalDateTime.now().format(DATE_TIME_FORMATTER));
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel channel = channelHandlerContext.channel();
        channelGroup.forEach(channel1 -> {
            if (channel != channel1) {
                channel1.writeAndFlush("[Client]" + channel.remoteAddress() + " says:" + s +"  "
                        + LocalDateTime.now().format(DATE_TIME_FORMATTER) + "\n");
            } else {
                channel1.writeAndFlush("[ME]" + channel.remoteAddress() + " says:" + s +"  "
                        + LocalDateTime.now().format(DATE_TIME_FORMATTER) + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
