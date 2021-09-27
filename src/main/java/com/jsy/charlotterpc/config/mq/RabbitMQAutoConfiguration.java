package com.jsy.charlotterpc.config.mq;

import com.jsy.charlotterpc.core.mq.rabbit.PooledChannelConnectionFactory;
import com.jsy.charlotterpc.core.mq.rabbit.RabbitConnectionFactory;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/27
 */

@Configuration
@ConditionalOnProperty(prefix = "charlotte.rabbitmq", name = {"host", "port"})
@EnableConfigurationProperties(RabbitMQProperties.class)
public class RabbitMQAutoConfiguration {

    RabbitMQProperties rabbitMQProperties;

    @Autowired
    public RabbitMQAutoConfiguration(RabbitMQProperties rabbitMQProperties) {
        this.rabbitMQProperties = rabbitMQProperties;
    }

    @Bean
    public RabbitMQConfiguration rabbitMQConfiguration() {
        return new RabbitMQConfiguration(rabbitMQProperties);
    }

    @ConditionalOnMissingBean
    @Bean
    public ConnectionFactory connectionFactory() {
        System.out.println("to create connectionFactory");

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(rabbitMQProperties.getHost());
        connectionFactory.setPort(rabbitMQProperties.getPort());
        connectionFactory.setUsername(rabbitMQProperties.getUsername());
        connectionFactory.setPassword(rabbitMQProperties.getPassword());
        return connectionFactory;
    }

    @Bean
    public RabbitConnectionFactory rabbitConnectionFactory() {
        return new PooledChannelConnectionFactory(connectionFactory());
    }


//    @PostConstruct
//    public void init() {
//        System.out.println("RabbitMQAutoConfiguration create");
//    }

}

