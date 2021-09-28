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
public class CharlotteRpcResponse {

//    String jsonRpcVersion;

    Object result;

    Exception exception;

    Error error;

//    String id;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Error {

        int code;

        String message;

        String data;

    }

}
