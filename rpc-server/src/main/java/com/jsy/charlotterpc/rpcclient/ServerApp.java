package com.jsy.charlotterpc.rpcclient;

import com.jsy.charlotterpc.config.EnableCharlotte;
import com.jsy.charlotterpc.config.mq.RabbitMQProperties;
import com.jsy.charlotterpc.rpcclient.service.TestService;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/25
 */

@EnableCharlotte
@SpringBootApplication
public class ServerApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ServerApp.class, args);
//        TestService testService = context.getBean(TestService.class);
//        testService.localRegistry.describe();
        RabbitMQProperties properties = context.getBean(RabbitMQProperties.class);
        System.out.println(properties);
        ConnectionFactory connectionFactory = context.getBean(ConnectionFactory.class);
        System.out.println(connectionFactory);
    }
}
