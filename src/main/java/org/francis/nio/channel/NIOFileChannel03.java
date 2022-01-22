package org.francis.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Franc1s
 * @date 2022/1/16
 * @apiNote
 */
public class NIOFileChannel03 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel fileChannel01 = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(512);
        while (true) {
            buffer.clear();
            int read = fileChannel01.read(buffer);
            if (read==-1){
                break;
            }
            buffer.flip();
            fileChannel02.write(buffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
