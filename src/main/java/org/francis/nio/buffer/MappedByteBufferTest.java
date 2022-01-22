package org.francis.nio.buffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Franc1s
 * @date 2022/1/16
 * @apiNote MappedByteBuffer可让文件直接在堆外内存中修改，操作系统不需要再拷贝一次
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");
        FileChannel accessFileChannel = randomAccessFile.getChannel();
        /**
         * arg1: MapMode 使用的模式
         * arg2: position 可以直接修改的位置
         * arg3: size   映射到内存的大小,即将1.txt的多少字节映射到内存
         * 可以直接修改的范围是0~5
         */
        MappedByteBuffer mappedByteBuffer = accessFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, ((byte) 'H'));
        mappedByteBuffer.put(3, ((byte) '9'));
        randomAccessFile.close();
        System.out.println("修改成功");
    }
}
