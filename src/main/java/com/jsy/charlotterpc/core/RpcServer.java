package com.jsy.charlotterpc.core;

import com.jsy.charlotterpc.protocol.CharlotteRpcRequest;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/25
 */
public interface RpcServer {
    void process(CharlotteRpcRequest charlotteRpcRequest);
}
