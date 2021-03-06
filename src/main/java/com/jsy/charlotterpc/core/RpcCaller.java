package com.jsy.charlotterpc.core;

import com.jsy.charlotterpc.protocol.CharlotteRpcRequest;
import com.jsy.charlotterpc.protocol.CharlotteRpcResponse;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/25
 */
public interface RpcCaller {
    CharlotteRpcResponse call(CharlotteRpcRequest charlotteRpcRequest);
}
