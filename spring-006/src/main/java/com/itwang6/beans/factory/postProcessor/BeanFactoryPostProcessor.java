package com.itwang6.beans.factory.postProcessor;

import com.itwang6.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {

    void postProcessorBeanfactory(ConfigurableListableBeanFactory beanFactory);

}
