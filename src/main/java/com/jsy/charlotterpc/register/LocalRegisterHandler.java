package com.jsy.charlotterpc.register;

import com.jsy.charlotterpc.annotation.CharlotteService;
import com.jsy.charlotterpc.domain.MetaFunction;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;

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


//            System.out.println("rpc service : " + bean);
//            System.out.println("rpc service class : " + bean.getClass());

            Class<?>[] interfaces = beanClass.getInterfaces();

            for (Class<?> interfaceClass : interfaces) {

//                System.out.println("  -  interfaceClass : " + interfaceClass);
//                System.out.println("  -  interfaceClass name : " + interfaceClass.getName());

                Method[] declaredMethods = interfaceClass.getDeclaredMethods();
//                System.out.println(" -  Method[] : " + Arrays.toString(declaredMethods));

                for (Method method : declaredMethods) {
//                    String metaFunctionKey = interfaceClass.getName() + "." + method.getName();
//                    System.out.println("key: " + metaFunctionKey);
                    MetaFunction metaFunction = new MetaFunction(method, bean);
                    System.out.println(metaFunction);
//                    method.invoke(bean);
                    localRegistry.register(metaFunction);
                }

            }
        }
        return bean;
    }

//    @PostConstruct
//    public void init() {
//        System.out.println("LocalRegisterHandler bean create successfully...");
//    }

}
