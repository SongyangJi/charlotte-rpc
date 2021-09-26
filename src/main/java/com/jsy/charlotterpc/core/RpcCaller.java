package com.jsy.charlotterpc.core;

import com.jsy.charlotterpc.protocol.LunaRpcRequest;
import com.jsy.charlotterpc.protocol.LunaRpcResponse;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/25
 */
public interface RpcCaller {
    LunaRpcResponse call(LunaRpcRequest lunaRpcRequest);
}
