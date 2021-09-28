package com.jsy.charlotterpc.config.mq;

import com.jsy.charlotterpc.core.mq.rabbit.PooledChannelConnectionFactory;
import com.jsy.charlotterpc.core.mq.rabbit.RabbitConnectionFactory;
import com.jsy.charlotterpc.reference.mq.rabbit.RabbitMQProxyInvocationHandler;
import com.jsy.charlotterpc.reference.mq.rabbit.RabbitMQReferenceProxy;
import com.jsy.charlotterpc.register.LocalRegistry;
import com.jsy.charlotterpc.register.mq.rabbit.RabbitMQRegisterHandler;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/27
 */

@Configuration
@EnableConfigurationProperties(RabbitMQProperties.class)
public class RabbitMQConfiguration {

    RabbitMQProperties rabbitMQProperties;

    @Autowired
    public RabbitMQConfiguration(RabbitMQProperties rabbitMQProperties) {
        this.rabbitMQProperties = rabbitMQProperties;
    }

    @ConditionalOnMissingBean
    @Bean
    public ConnectionFactory connectionFactory() {
//        System.out.println("to create connectionFactory");
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


    @ConditionalOnBean(LocalRegistry.class)
    @Configuration
    public static class RegisterToRabbitMQ {

        LocalRegistry localRegistry;

        RabbitConnectionFactory rabbitConnectionFactory;

        @Autowired
        public RegisterToRabbitMQ(LocalRegistry localRegistry, RabbitConnectionFactory rabbitConnectionFactory) {
            this.localRegistry = localRegistry;
            this.rabbitConnectionFactory = rabbitConnectionFactory;
        }

        @Bean
        public RabbitMQRegisterHandler rabbitMQRegisterHandler() {
            return new RabbitMQRegisterHandler(localRegistry, rabbitConnectionFactory);
        }

    }

    @Configuration
    public static class CallToRabbitMQ {
        RabbitConnectionFactory rabbitConnectionFactory;

        @Autowired
        public CallToRabbitMQ(RabbitConnectionFactory rabbitConnectionFactory) {
            this.rabbitConnectionFactory = rabbitConnectionFactory;
        }

        @Bean
        public RabbitMQProxyInvocationHandler rabbitMQProxyInvocationHandler() {
            return new RabbitMQProxyInvocationHandler(rabbitConnectionFactory);
        }

        @Bean
        public RabbitMQReferenceProxy rabbitMQReferenceProxy() {
            return new RabbitMQReferenceProxy(rabbitMQProxyInvocationHandler());
        }

    }









}
