package com.jsy.charlotterpc.register;

import com.jsy.charlotterpc.exception.MetaFunctionNotFoundException;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/25
 */

public class LocalRegistry {

    private final Map<String, MetaFunction> registry = new ConcurrentHashMap<>();


    // TODO
    private boolean check(Method method) {
        Class<?> interfaceClass = method.getDeclaringClass();
        if (!interfaceClass.isInterface()) {
            return false;
        }
        return true;
    }

    public void register(String functionId, MetaFunction function) {
        registry.put(functionId, function);
    }

    public MetaFunction access(String functionId) throws MetaFunctionNotFoundException {
        if (!registry.containsKey(functionId)) {
            throw new MetaFunctionNotFoundException();
        }
        return registry.get(functionId);
    }


    @PostConstruct
    public void init() {
        System.out.println("LocalRegistry bean create successfully...");
    }


}
