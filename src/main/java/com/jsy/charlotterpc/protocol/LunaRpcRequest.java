package com.jsy.charlotterpc.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LunaRpcRequest {

    String jsonRpc;

    String method;

    Object[] params;

    String id;

}
