package org.francis.nio.channel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author Franc1s
 * @date 2022/1/16
 * @apiNote
 */
public class NIOFileChannel04 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D:\\a.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\a2.jpg");
        FileChannel channel01 = fileInputStream.getChannel();
        FileChannel channel02 = fileOutputStream.getChannel();
        channel02.transferFrom(channel01,0,channel01.size());
        channel01.close();
        channel02.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
