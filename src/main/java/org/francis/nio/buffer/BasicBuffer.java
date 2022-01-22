package org.francis.nio.buffer;

import java.nio.IntBuffer;

/**
 * @author Franc1s
 * @date 2022/1/14
 * @apiNote NIO Buffer使用
 */
public class BasicBuffer {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);//创建一个Buffer,大小为5,即可以存放5个int
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);//向buffer存放数据
        }
        intBuffer.flip();//buffer读写切换
        while (intBuffer.hasRemaining()) {
            System.out.println("intBuffer.get() = " + intBuffer.get());
        }
    }
}
