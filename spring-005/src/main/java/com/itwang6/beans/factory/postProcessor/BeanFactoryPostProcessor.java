package com.itwang5.beans.factory.postProcessor;

import com.itwang5.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {

    void postProcessorBeanfactory(ConfigurableListableBeanFactory beanFactory);

}
