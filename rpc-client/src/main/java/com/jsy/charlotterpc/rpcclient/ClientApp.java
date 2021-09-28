package com.jsy.charlotterpc.rpcclient;

import com.jsy.charlotterpc.config.EnableCharlotte;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/25
 */
@EnableCharlotte
@SpringBootApplication
public class ClientApp{
    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class,args);
    }
}
