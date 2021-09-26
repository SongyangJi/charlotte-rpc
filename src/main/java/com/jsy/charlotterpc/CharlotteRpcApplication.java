package com.jsy.charlotterpc;

import com.jsy.charlotterpc.config.EnableCharlotte;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableCharlotte
@SpringBootApplication
public class CharlotteRpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(CharlotteRpcApplication.class, args);
    }

}
