package com.jsy.charlotterpc.reference.mq.rabbit;

import com.jsy.charlotterpc.core.mq.rabbit.RabbitConnectionFactory;
import com.jsy.charlotterpc.domain.MetaFunction;
import com.jsy.charlotterpc.protocol.CharlotteRpcRequest;
import com.jsy.charlotterpc.protocol.CharlotteRpcResponse;
import com.jsy.charlotterpc.util.JsonUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

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

        Channel channel = rabbitConnectionFactory.createConnection().createChannel();

        CharlotteRpcRequest rpcRequest = new CharlotteRpcRequest();
        rpcRequest.setMethodId(MetaFunction.getFunctionId(method));
        rpcRequest.setParams(args);


        String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .replyTo(replyQueueName)
                .build();

        channel.basicPublish("",rpcQueueName,props, JsonUtil.stringfy(rpcRequest).getBytes(StandardCharsets.UTF_8));

        BlockingQueue<String> response = new ArrayBlockingQueue<>(1);

        String cTag = channel.basicConsume(replyQueueName,true, (consumerTag, delivery) -> {
            response.offer(new String(delivery.getBody(), StandardCharsets.UTF_8));
        }, consumerTag -> {});

        String rpcResponseStr = response.take();
        CharlotteRpcResponse rpcResponse = JsonUtil.parse(rpcResponseStr, CharlotteRpcResponse.class);
//        System.out.println(rpcResponse);

        channel.basicCancel(cTag);

        return rpcResponse.getResult();
    }

//    @PostConstruct
//    public void init() {
//        System.out.println("RabbitMQProxyInvocationHandler create");
//    }

}
