package com.jsy.charlotterpc.config.mq;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/27
 */


public class RabbitMQConfiguration {

    RabbitMQProperties rabbitMQProperties;

    @Autowired
    public RabbitMQConfiguration(RabbitMQProperties rabbitMQProperties) {
        this.rabbitMQProperties = rabbitMQProperties;
    }


    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(rabbitMQProperties.getHost());
        connectionFactory.setPort(rabbitMQProperties.getPort());
        connectionFactory.setUsername(rabbitMQProperties.getUsername());
        connectionFactory.setPassword(rabbitMQProperties.getPassword());
        return connectionFactory;
    }



//    @PostConstruct
//    public void init() {
//        System.out.println("RabbitMQConfiguration create");
//    }

}
