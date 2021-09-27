package com.jsy.charlotterpc.register;

import com.jsy.charlotterpc.domain.MetaFunction;
import com.jsy.charlotterpc.domain.MetaInterface;
import com.jsy.charlotterpc.exception.MetaFunctionNotFoundException;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/25
 */

public class LocalRegistry implements Registry {


    protected final Map<String, MetaFunction> registry = new ConcurrentHashMap<>();

//    protected final Map<String, List<MetaFunction>> interfaceIdMapFunctions = new ConcurrentHashMap<>();

    protected Set<MetaInterface> metaInterfaces = new HashSet<>();


    // TODO
    private boolean check(Method method) {
        Class<?> interfaceClass = method.getDeclaringClass();
        if (!interfaceClass.isInterface()) {
            return false;
        }
        return true;
    }


    protected void register(String functionId, MetaFunction function) {
        registry.put(functionId, function);
    }

    @Override
    public MetaFunction access(String functionId) throws MetaFunctionNotFoundException {
        if (!registry.containsKey(functionId)) {
            throw new MetaFunctionNotFoundException();
        }
        return registry.get(functionId);
    }

    @Override
    public void register(MetaFunction function) {
        this.register(function.getFullyQualifiedName(), function);
        MetaInterface metaInterface = new MetaInterface(function.getInterfaceClass());
        metaInterfaces.add(metaInterface);
    }


    @Override
    public List<String> getAllFunctionNames() {
        return registry.values().stream().map(MetaFunction::getFullyQualifiedName).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllInterfaceNames() {
        return metaInterfaces.stream().map(MetaInterface::getFullyQualifiedName).collect(Collectors.toList());
    }


    public List<Class<?>> getAllInterfaces() {
        return metaInterfaces.stream().map(MetaInterface::getInterfaceClass).collect(Collectors.toList());
    }

    public void describe() {
        registry.forEach((s, metaFunction) -> System.out.println(s + ":" + metaFunction));
    }

//    @PostConstruct
//    public void init() {
//        System.out.println("LocalRegistry bean create successfully...");
//    }

}
