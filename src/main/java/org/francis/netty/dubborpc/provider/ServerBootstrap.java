package org.francis.netty.dubborpc.provider;

import org.francis.netty.dubborpc.netty.NettyServer;

/**
 * @author Franc1s
 * @date 2022/1/26
 * @apiNote 服务提供者
 */
public class ServerBootstrap {
    public static void main(String[] args) throws InterruptedException {
        NettyServer.startServer("127.0.0.1",7000);
    }
}
