package com.jsy.charlotterpc.register.mq.rabbit;

import com.jsy.charlotterpc.core.mq.rabbit.RabbitConnectionFactory;
import com.jsy.charlotterpc.register.Registry;
import com.jsy.charlotterpc.register.RemoteRegisterHandler;
import com.rabbitmq.client.*;

import java.io.IOException;
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

            channel.basicConsume(rpcQueueName,new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println(new String(body));
                }
            });
        }
    }



}