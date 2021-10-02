package com.jsy.charlotterpc.register.mq.rabbit;

import com.jsy.charlotterpc.core.mq.rabbit.RabbitConnectionFactory;
import com.jsy.charlotterpc.domain.MetaFunction;
import com.jsy.charlotterpc.exception.MetaFunctionNotFoundException;
import com.jsy.charlotterpc.protocol.CharlotteRpcRequest;
import com.jsy.charlotterpc.protocol.CharlotteRpcResponse;
import com.jsy.charlotterpc.register.Registry;
import com.jsy.charlotterpc.register.RemoteRegisterHandler;
import com.jsy.charlotterpc.util.JsonUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/26
 */

public class RabbitMQRegisterHandler extends RemoteRegisterHandler {

    private static final String QUEUE_PREFIX = "charlotte";

    RabbitConnectionFactory rabbitConnectionFactory;

    public RabbitMQRegisterHandler(Registry localRegistry, RabbitConnectionFactory rabbitConnectionFactory) {
        super(localRegistry);
        this.rabbitConnectionFactory = rabbitConnectionFactory;
    }

    @Override
    protected void register(Registry registry) {
        System.out.println("向 rabbitmq 注册服务");
        System.out.println(registry.getAllInterfaceNames());
        try {
            doRegister(registry.getAllInterfaceNames());
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    private void doRegister(List<String> interfaceNames) throws IOException, TimeoutException {
        Connection connection = rabbitConnectionFactory.createConnection();
        Channel channel = connection.createChannel();
        for (String interfaceName : interfaceNames) {
            String rpcQueueName = QUEUE_PREFIX + "." + interfaceName;
            channel.queueDeclare(rpcQueueName, true, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                System.out.println("consumerTag : " + consumerTag);
                System.out.println("message : " + message);
                String rpcRequestStr = new String(message.getBody(), StandardCharsets.UTF_8);
                CharlotteRpcRequest rpcRequest = JsonUtil.parse(rpcRequestStr, CharlotteRpcRequest.class);
                System.out.println(rpcRequest);

                String methodId = rpcRequest.getMethodId();
                Object[] params = rpcRequest.getParams();
                Object result = null;
                try {
                    MetaFunction metaFunction = this.localRegistry.access(methodId);
                    Method method = metaFunction.getMethod();
                    Object handler = metaFunction.getHandler();
                    result = method.invoke(handler, params);

                } catch (MetaFunctionNotFoundException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                } finally {
                    CharlotteRpcResponse rpcResponse = new CharlotteRpcResponse(result);

                    channel.basicPublish("", message.getProperties().getReplyTo(), message.getProperties(),
                            JsonUtil.stringfy(rpcResponse).getBytes(StandardCharsets.UTF_8));
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                }

            };
            channel.basicConsume(rpcQueueName, false, deliverCallback, consumerTag -> {
            });
        }
    }


}