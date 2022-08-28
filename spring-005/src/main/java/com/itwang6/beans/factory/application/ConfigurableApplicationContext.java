package com.itwang5.beans.factory.application;

import com.itwang5.BeansException;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:27  9:29
 */
public interface ConfigurableApplicationContext extends ApplicatioContext{

    void refresh() throws BeansException;
}
