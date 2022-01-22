package org.francis.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Franc1s
 * @date 2022/1/16
 * @apiNote
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        // 创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //得到一个Selector对象
        Selector selector = Selector.open();
        //绑定一个端口,在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //把ServerSocketChannel注册到 selector 关心事件为OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //循环等待客户端连接
        while (true) {
            if (selector.select(1000) == 0) { //if没有事件发生
                System.out.println("服务器等待了一秒，无连接");
                continue;
            }
            //如果返回>0,就获取相关的selectionKey集合
            //1.如果返回>0，表示已经获取到关注的事件
            //2.selector.selectionKeys()返回关注事件的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                //获取到SelectionKey
                SelectionKey selectionKey = iterator.next();
                //根据Key对应的通道发生的事件做相应的处理
                if (selectionKey.isAcceptable()) {//如果是OP_ACCEPT事件,有新的客户端连接
                    //给该客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("有一个客户端连接！"+socketChannel.hashCode());
                    socketChannel.configureBlocking(false);
                    //将socketChannel 注册到selector,关注事件为OP_READ,同时给socketChannel
                    //关联一个buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (selectionKey.isReadable()) {//如果是OP_READ事件
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    //获取该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    socketChannel.read(buffer);
                    System.out.println("from Client:"+new String(buffer.array()));
                }
                //手动从集合中移动当前的selectionKey，防止重复操作
                iterator.remove();
            }
        }
    }
}
