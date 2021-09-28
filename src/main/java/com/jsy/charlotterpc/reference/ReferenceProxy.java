package com.jsy.charlotterpc.reference;

import com.jsy.charlotterpc.annotation.CharlotteReference;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/28
 */

public abstract class ReferenceProxy implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        //获取所有字段
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(CharlotteReference.class)) {
                System.out.println(field);
                field.setAccessible(true);
                Object proxy = Proxy.newProxyInstance(field.getType().getClassLoader(), new Class<?>[]{field.getType()}, provideInvocationHandler());
                try {
                    field.set(bean, proxy);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }

    protected abstract InvocationHandler provideInvocationHandler();

}
