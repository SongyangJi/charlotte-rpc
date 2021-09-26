package com.jsy.charlotterpc.test;

import com.jsy.charlotterpc.annotation.LunaService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/25
 */


@Component
@LunaService
public class TestLunaService implements InterfaceA, InterfaceB {

    @Override
    public void test() {
        System.out.println("TestLunaService: test() in InterfaceA");
    }

    @Override
    public void test1() {
        System.out.println("TestLunaService: test1() in InterfaceA");
    }

    @Override
    public void test2() {
        System.out.println("TestLunaService: test2() in InterfaceA");
    }

    @Override
    public String toString() {
        return "TestLunaService{}";
    }

    @PostConstruct
    public void init() {
        System.out.println("init TestLunaService");
    }
}

