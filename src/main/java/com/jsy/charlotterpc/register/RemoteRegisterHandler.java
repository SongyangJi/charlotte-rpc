package com.jsy.charlotterpc.register;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author: SongyangJi
 * @description:
 * @since: 2021/9/27
 */
public abstract class RemoteRegisterHandler implements ApplicationListener<ContextRefreshedEvent> {

    Registry localRegistry;

    public RemoteRegisterHandler(Registry localRegistry) {
        this.localRegistry = localRegistry;
    }

    protected abstract void register(Registry registry);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        register(localRegistry);
    }
}

