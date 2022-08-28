package com.itwang6.beans.factory.application;

import com.itwang6.BeansException;
import com.itwang6.beans.factory.ConfigurableListableBeanFactory;
import com.itwang6.beans.factory.postProcessor.BeanFactoryPostProcessor;
import com.itwang6.beans.factory.postProcessor.BeanPostProcessor;
import com.itwang6.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:27  9:44
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext{


    @Override
    public void refresh() throws BeansException {
        // 此处由子类创建beanfactory
        refreshBeanFactory();

        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 执行几大流程
        // 1 执行beanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);

        // 提前注册beanPostProcessor类型的bean 因为其他bean在执行的时候 需要这些bean
        invokeBeanPostProcessor(beanFactory);

        // 提前初始化单例
        beanFactory.preInstantiateSingletons();


    }

    private void invokeBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        // 拿到就是已经被实例化的了
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for(BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()){
            beanFactory.addPostProcessor(beanPostProcessor);
        }
    }

    private void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        // 拿到即已被实例化
        beanFactoryPostProcessorMap.values().forEach(bean -> {
            bean.postProcessorBeanfactory(beanFactory);
        });
    }


    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> tClass) throws BeansException {
        return getBeanFactory().getBeansOfType(tClass);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String beanName) {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requirdType) throws BeansException {
        return getBeanFactory().getBean(name, requirdType);
    }

    protected abstract void  refreshBeanFactory();

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    @Override
    public void registerShutHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        this.getBeanFactory().destroySingletons();
    }
}
