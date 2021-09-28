package com.jsy.charlotterpc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/28
 */

@Data
@ConfigurationProperties(prefix = "charlotte")
public class CharlotteProperties {
    boolean server;
}
