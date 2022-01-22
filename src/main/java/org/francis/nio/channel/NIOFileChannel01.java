package org.francis.nio.channel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Franc1s
 * @date 2022/1/16
 * @apiNote
 */
public class NIOFileChannel01 {
    public static void main(String[] args) throws IOException {
        String str="Hello,Javaboy";
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\file01.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());
        buffer.flip();
        fileChannel.write(buffer);
        fileOutputStream.close();
    }
}
