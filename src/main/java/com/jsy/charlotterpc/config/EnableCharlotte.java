package com.jsy.charlotterpc.config;

import com.jsy.charlotterpc.config.mq.RabbitMQAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/27
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({CharlotteAutoConfiguration.class, RabbitMQAutoConfiguration.class})
public @interface EnableCharlotte {
}
