package com.jsy.charlotterpc.config.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/27
 */

@ConditionalOnProperty(prefix = "charlotte.rabbitmq", name = {"host", "port"})
@EnableConfigurationProperties(RabbitMQProperties.class)
@Import(RabbitMQConfiguration.class)
public class RabbitMQAutoConfiguration {

    RabbitMQProperties rabbitMQProperties;

    @Autowired
    public RabbitMQAutoConfiguration(RabbitMQProperties rabbitMQProperties) {
        this.rabbitMQProperties = rabbitMQProperties;
    }

//    @PostConstruct
//    public void init() {
//        System.out.println("RabbitMQAutoConfiguration create");
//    }

}

