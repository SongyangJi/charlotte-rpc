package com.jsy.charlotterpc.config.mq;

import com.jsy.charlotterpc.core.mq.rabbit.PooledChannelConnectionFactory;
import com.jsy.charlotterpc.core.mq.rabbit.RabbitConnectionFactory;
import com.jsy.charlotterpc.register.LocalRegistry;
import com.jsy.charlotterpc.register.mq.rabbit.RabbitMQRegisterHandler;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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
@Import(RabbitMQConfiguration.class)
public class RabbitMQAutoConfiguration {

}

