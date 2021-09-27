package com.jsy.charlotterpc.rpcclient.service;

import com.jsy.charlotterpc.annotation.CharlotteService;
import com.jsy.charlotterpc.register.LocalRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/27
 */

@CharlotteService
@Component
public class TestService implements TestInterfaceA, TestInterfaceB {


    public LocalRegistry localRegistry;

    @Autowired
    public TestService(LocalRegistry localRegistry) {
        this.localRegistry = localRegistry;
    }

    @Override
    public void test1() {
        System.out.println("TestService test1");
    }

    @Override
    public void test2() {
        System.out.println("TestService test2");
    }

    @PostConstruct
    public void init() {
        System.out.println("TestService bean created...");
    }

}
