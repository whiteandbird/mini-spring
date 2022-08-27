package com.itwang5.beans.factory.application;

import com.itwang5.beans.factory.ConfigurableListableBeanFactory;
import com.itwang5.beans.factory.support.DefaultListableBeanFactory;
import com.itwang5.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:27  11:49
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshApplicationContext{
    @Override
    protected void loadBeanDefinition(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        xmlBeanDefinitionReader.loadBeanDefinitions(getConfigLocations());
    }

    protected abstract String[] getConfigLocations();
}
