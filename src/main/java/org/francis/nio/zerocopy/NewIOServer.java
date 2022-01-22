package org.francis.nio.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author Franc1s
 * @date 2022/1/18
 * @apiNote
 */
public class NewIOServer {
    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7001);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(inetSocketAddress);
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            int readCount=0;
            while(-1!=readCount){
                try {
                    readCount=socketChannel.read(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                buffer.rewind();
            }
        }
    }
}
