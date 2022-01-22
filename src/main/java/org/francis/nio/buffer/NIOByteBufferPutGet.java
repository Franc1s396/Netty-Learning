package org.francis.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author Franc1s
 * @date 2022/1/16
 * @apiNote Buffer中的数据需要按顺序获取
 */
public class NIOByteBufferPutGet {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.putInt(100);
        buffer.putLong(9);
        buffer.putChar('j');
        buffer.putShort((short) 4);
        buffer.flip();
        System.out.println();
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
    }
}
