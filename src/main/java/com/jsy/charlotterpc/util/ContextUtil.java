package com.jsy.charlotterpc.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/25
 */

@Configuration
public class ContextUtil implements ApplicationContextAware {

    ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @PostConstruct
    public void init() {
        System.out.println("ContextUtil bean create successfully...");
    }

}
