package com.jsy.charlotterpc.core.mq.rabbit;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/27
 */

public abstract class RabbitConnectionFactory implements ApplicationContextAware {
    ApplicationContext applicationContext;
    com.rabbitmq.client.ConnectionFactory bareConnectionFactory;

    public RabbitConnectionFactory(ConnectionFactory connectionFactory) {
        this.bareConnectionFactory = connectionFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public abstract Connection createConnection() throws IOException, TimeoutException;


}
