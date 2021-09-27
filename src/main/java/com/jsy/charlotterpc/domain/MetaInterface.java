package com.jsy.charlotterpc.domain;

import lombok.*;

import java.util.Objects;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/27
 */

@Getter
@Setter
@ToString
public class MetaInterface {

    String fullyQualifiedName;

    Class<?> interfaceClass;

    public MetaInterface(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
        this.fullyQualifiedName = interfaceClass.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetaInterface that = (MetaInterface) o;

        return Objects.equals(fullyQualifiedName, that.fullyQualifiedName);
    }

    @Override
    public int hashCode() {
        return fullyQualifiedName != null ? fullyQualifiedName.hashCode() : 0;
    }
}
