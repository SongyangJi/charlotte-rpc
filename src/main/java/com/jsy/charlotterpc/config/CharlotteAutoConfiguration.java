package com.jsy.charlotterpc.config;

import com.jsy.charlotterpc.reference.ReferenceProxy;
import com.jsy.charlotterpc.register.LocalRegisterHandler;
import com.jsy.charlotterpc.register.LocalRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/25
 */

@EnableConfigurationProperties({CharlotteProperties.class})
@Configuration
public class CharlotteAutoConfiguration {

    @ConditionalOnProperty(name = "charlotte.server", havingValue = "true")
    @Bean
    public LocalRegistry localRegistry() {
        return new LocalRegistry();
    }

    @ConditionalOnBean(LocalRegistry.class)
    @Bean
    public LocalRegisterHandler localRegisterHandler() {
        return new LocalRegisterHandler(localRegistry());
    }


}
