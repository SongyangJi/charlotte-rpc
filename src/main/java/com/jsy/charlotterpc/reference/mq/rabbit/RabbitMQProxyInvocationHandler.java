package com.jsy.charlotterpc.reference.mq.rabbit;

import com.jsy.charlotterpc.core.mq.rabbit.RabbitConnectionFactory;
import com.jsy.charlotterpc.protocol.CharlotteRpcRequest;
import com.rabbitmq.client.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/28
 */

public class RabbitMQProxyInvocationHandler implements InvocationHandler {

    private static final String QUEUE_PREFIX = "charlotte";

    RabbitConnectionFactory rabbitConnectionFactory;

    public RabbitMQProxyInvocationHandler(RabbitConnectionFactory rabbitConnectionFactory) {
        this.rabbitConnectionFactory = rabbitConnectionFactory;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Class<?> interfaceClass = method.getDeclaringClass();
        if (!interfaceClass.isInterface()) {
            throw new RuntimeException("not an interface");
        }
        String interfaceName = interfaceClass.getName();

        String rpcQueueName = QUEUE_PREFIX + "." + interfaceName;
        String fullyQualifiedMethodName = interfaceName + "." + method.getName();

        CharlotteRpcRequest rpcRequest = new CharlotteRpcRequest();
        rpcRequest.setMethod(fullyQualifiedMethodName);

        Channel channel = rabbitConnectionFactory.createConnection().createChannel();

        channel.basicPublish("",rpcQueueName,null,"fake rpc request".getBytes(StandardCharsets.UTF_8));

        return null;
    }

//    @PostConstruct
//    public void init() {
//        System.out.println("RabbitMQProxyInvocationHandler create");
//    }

}
