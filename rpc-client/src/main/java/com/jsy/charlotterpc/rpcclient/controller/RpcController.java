package com.jsy.charlotterpc.rpcclient.controller;

import com.jsy.charlotterpc.rpcclient.service.TestRpcCall;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/28
 */

@RestController
public class RpcController {

    @Resource
    TestRpcCall rpcCall;

    @GetMapping("rpc1")
    public void test1() {
        rpcCall.test1();
    }

}
