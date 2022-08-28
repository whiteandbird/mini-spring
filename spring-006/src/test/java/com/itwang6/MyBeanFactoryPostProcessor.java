package com.itwang6;

import com.itwang6.beans.factory.ConfigurableListableBeanFactory;
import com.itwang6.beans.factory.config.BeanDefinition;
import com.itwang6.beans.factory.postProcessor.BeanFactoryPostProcessor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:27  15:20
 */
@Slf4j
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessorBeanfactory(ConfigurableListableBeanFactory beanFactory) {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        log.info("========当前注入的bean==============");
        for (String beanName : beanDefinitionNames){
            System.out.println(beanName);
        }
        BeanDefinition userService = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = userService.getPropertyValues();
        PropertyValue name = propertyValues.getPropertyValue("name");
        name.setPropertyValue("新的名字");
    }
}
