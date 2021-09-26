package com.jsy.charlotterpc.register;

import com.jsy.charlotterpc.annotation.CharlotteService;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/25
 */

public class LocalRegisterHandler implements BeanPostProcessor {

    LocalRegistry localRegistry;


    public LocalRegisterHandler(LocalRegistry localRegistry) {
        this.localRegistry = localRegistry;
    }

    @SneakyThrows
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Class<?> beanClass = bean.getClass();

        if (beanClass.isAnnotationPresent(CharlotteService.class)) {


            System.out.println("rpc service : " + bean);
            System.out.println("rpc service class : " + bean.getClass());

            Class<?>[] interfaces = beanClass.getInterfaces();

            for (Class<?> interfaceClass : interfaces) {
                // TODO 向注册中心注册

                // TODO 在本地注册

                System.out.println("  -  interfaceClass : " + interfaceClass);
                System.out.println("  -  interfaceClass name : " + interfaceClass.getName());

                Method[] declaredMethods = interfaceClass.getDeclaredMethods();
                System.out.println(" -  Method[] : " + Arrays.toString(declaredMethods));

                for (Method method : declaredMethods) {
                    String metaFunctionKey = interfaceClass.getName() + "." + method.getName();
                    System.out.println("key: " + metaFunctionKey);
                    MetaFunction metaFunction = new MetaFunction(method, bean, beanClass, interfaceClass);
                    System.out.println(metaFunction);
                    method.invoke(bean);
                    localRegistry.register(metaFunctionKey, metaFunction);
                }

            }
        }
        return bean;
    }

    @PostConstruct
    public void init() {
        System.out.println("LocalRegisterHandler bean create successfully...");
    }
}
