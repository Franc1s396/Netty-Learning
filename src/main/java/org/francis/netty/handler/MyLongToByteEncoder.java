package org.francis.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Franc1s
 * @date 2022/1/24
 * @apiNote
 */
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Long aLong, ByteBuf byteBuf) throws Exception {
        System.out.println("MyLongToByteEncoder encode被调用");
        System.out.println("msg="+aLong);
        byteBuf.writeLong(aLong);
    }
}
