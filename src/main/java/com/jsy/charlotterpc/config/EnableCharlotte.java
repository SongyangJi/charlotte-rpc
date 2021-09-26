package com.jsy.charlotterpc.config;

import org.springframework.context.annotation.Import;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/27
 */

@Import(CharlotteAutoConfiguration.class)
public @interface EnableCharlotte {
}
