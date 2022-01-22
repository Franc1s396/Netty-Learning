package org.francis.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Franc1s
 * @date 2022/1/14
 * @apiNote
 */
public class BIOServer {
    /**
     * 和客户端通讯
     *
     * @param socket
     */
    public static void handler(Socket socket) {
        try {
            System.out.println("Thread.currentThread().getId() = " + Thread.currentThread().getId());
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动了");
        while (true) {
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            executorService.execute(() -> {
                handler(socket);
            });
        }
    }
}
