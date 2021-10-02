package com.jsy.charlotterpc.rpcclient.service;

import com.jsy.charlotterpc.annotation.CharlotteReference;
import com.jsy.charlotterpc.api.TestInterfaceA;
import org.springframework.stereotype.Component;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/28
 */
@Component
public class TestRpcCall {

    @CharlotteReference
    TestInterfaceA testInterfaceA;


    public void test1() {
        testInterfaceA.test1();
    }

    public String test2() {
        return testInterfaceA.test2();
    }


}
