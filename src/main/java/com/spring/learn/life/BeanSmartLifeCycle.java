package com.spring.learn.life;

import org.springframework.context.SmartLifecycle;

public class BeanSmartLifeCycle implements SmartLifecycle {
    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isAutoStartup() {
        return SmartLifecycle.super.isAutoStartup();
    }

    @Override
    public void stop(Runnable callback) {
        SmartLifecycle.super.stop(callback);
    }

    @Override
    public int getPhase() {
        return SmartLifecycle.super.getPhase();
    }
}
