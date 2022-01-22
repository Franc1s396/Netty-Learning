package org.francis.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @author Franc1s
 * @date 2022/1/21
 * @apiNote
 */
public class NettyByteBuf02 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("Hello,World!",CharsetUtil.UTF_8);
        if (byteBuf.hasArray()) {
            byte[] bytes = byteBuf.array();
            System.out.println(new String(bytes, CharsetUtil.UTF_8));
        }
        for (int i = 0; i < byteBuf.readableBytes(); i++) {
            byteBuf.getByte(i);
        }
    }
}
