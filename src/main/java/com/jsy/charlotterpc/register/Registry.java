package com.jsy.charlotterpc.register;

import com.jsy.charlotterpc.domain.MetaFunction;
import com.jsy.charlotterpc.exception.MetaFunctionNotFoundException;

import java.util.List;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/27
 */
public interface Registry {

    void register(MetaFunction function);

    MetaFunction access(String functionId) throws MetaFunctionNotFoundException;

    List<String> getAllInterfaceNames();

    List<String> getAllFunctionNames();

}
