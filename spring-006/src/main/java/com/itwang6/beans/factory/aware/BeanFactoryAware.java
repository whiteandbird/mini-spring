package com.itwang6.beans.factory.aware;

import com.itwang6.beans.factory.BeanFactory;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:28  21:51
 */
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory);
}
