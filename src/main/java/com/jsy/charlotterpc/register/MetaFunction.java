package com.jsy.charlotterpc.register;

import lombok.*;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/25
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MetaFunction {
    Method method;
    Object handler;
    Class<?> handlerClass;
    Class<?> interfaceClass;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetaFunction that = (MetaFunction) o;

        if (!Objects.equals(method, that.method)) return false;
        if (!Objects.equals(handler, that.handler)) return false;
        return Objects.equals(handlerClass, that.handlerClass);
    }

    @Override
    public int hashCode() {
        int result = method != null ? method.hashCode() : 0;
        result = 31 * result + (handler != null ? handler.hashCode() : 0);
        result = 31 * result + (handlerClass != null ? handlerClass.hashCode() : 0);
        return result;
    }
}
