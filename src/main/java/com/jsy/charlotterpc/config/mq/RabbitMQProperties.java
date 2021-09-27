package com.jsy.charlotterpc.config.mq;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/27
 */

@Data
@ConfigurationProperties(prefix = "charlotte.rabbitmq")
public class RabbitMQProperties {
    private String host = "localhost";

    /**
     * RabbitMQ port. Ignored if an address is set. Default to 5672, or 5671 if SSL is
     * enabled.
     */
    private Integer port = 5672;

    /**
     * Login user to authenticate to the broker.
     */
    private String username = "guest";

    /**
     * Login to authenticate against the broker.
     */
    private String password = "guest";

//    @PostConstruct
//    public void init() {
//        System.out.println(this);
//    }

}
