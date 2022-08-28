package com.itwang5.beans.factory.application;

import com.itwang5.beans.factory.ConfigurableListableBeanFactory;
import com.itwang5.beans.factory.support.DefaultListableBeanFactory;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:27  10:49
 */
public abstract class AbstractRefreshApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() {
        // 不应该由决定工厂的创建
        beanFactory = createBeanFactory();
        // 进行beanDefinition 积累
        loadBeanDefinition(beanFactory);

    }

    protected abstract void loadBeanDefinition(DefaultListableBeanFactory beanFactory);

    private DefaultListableBeanFactory createBeanFactory(){
        return new DefaultListableBeanFactory();
    }


    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
