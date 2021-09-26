package com.jsy.charlotterpc.config;

import com.jsy.charlotterpc.register.LocalRegisterHandler;
import com.jsy.charlotterpc.register.LocalRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/25
 */

@Configuration
public class LunaAutoConfiguration {

    @Bean
    public LocalRegistry localRegistry() {
        return new LocalRegistry();
    }

    @Bean
    public LocalRegisterHandler localRegisterHandler() {
        return new LocalRegisterHandler(localRegistry());
    }

}
