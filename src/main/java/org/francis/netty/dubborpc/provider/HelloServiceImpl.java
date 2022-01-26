package org.francis.netty.dubborpc.provider;

import org.francis.netty.dubborpc.publicinterface.HelloService;

/**
 * @author Franc1s
 * @date 2022/1/26
 * @apiNote
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String message) {
        System.out.println("收到客户端消息 = " + message);
        if (message!=null) {
            return "你好客户端，我已经收到你的消息 ["+message+"]";
        }
        return "你好客户端，我已经收到你的消息";
    }
}
