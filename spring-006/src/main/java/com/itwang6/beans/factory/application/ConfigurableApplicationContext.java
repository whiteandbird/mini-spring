package com.itwang6.beans.factory.application;

import com.itwang6.BeansException;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:27  9:29
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    void refresh() throws BeansException;
    void registerShutHook();
    void close();
}
