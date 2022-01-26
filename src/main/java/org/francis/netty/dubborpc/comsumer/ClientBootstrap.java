package org.francis.netty.dubborpc.comsumer;

import org.francis.netty.dubborpc.netty.NettyClient;
import org.francis.netty.dubborpc.publicinterface.HelloService;

/**
 * @author Franc1s
 * @date 2022/1/26
 * @apiNote
 */
public class ClientBootstrap {
    public static final String providerName="HelloService#hello#";
    public static void main(String[] args) {
        NettyClient client = new NettyClient();
        HelloService service = (HelloService) client.getBean(HelloService.class, providerName);
        String hello = service.hello("你好 dubboRPC");
        System.out.println("调用的结果 res="+hello);
    }
}
