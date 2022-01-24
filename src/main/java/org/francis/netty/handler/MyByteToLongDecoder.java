package org.francis.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author Franc1s
 * @date 2022/1/24
 * @apiNote
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {
    /**
     * @param channelHandlerContext 上下文
     * @param byteBuf               入站的Bytebuf
     * @param list                  将解码后的数据传给下一个handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() >= 8) {
            list.add(byteBuf.readLong());
        }
    }
}
