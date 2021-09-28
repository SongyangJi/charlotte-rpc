package com.jsy.charlotterpc.reference.mq.rabbit;

import com.jsy.charlotterpc.reference.ReferenceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationHandler;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/28
 */

public class RabbitMQReferenceProxy extends ReferenceProxy {

    RabbitMQProxyInvocationHandler rabbitMQProxyInvocationHandler;

    public RabbitMQReferenceProxy(RabbitMQProxyInvocationHandler rabbitMQProxyInvocationHandler) {
        this.rabbitMQProxyInvocationHandler = rabbitMQProxyInvocationHandler;
    }

    @Override
    protected InvocationHandler provideInvocationHandler() {
        return rabbitMQProxyInvocationHandler;
    }

//    @PostConstruct
//    public void init() {
//        System.out.println("RabbitMQReferenceProxy create");
//    }

}
