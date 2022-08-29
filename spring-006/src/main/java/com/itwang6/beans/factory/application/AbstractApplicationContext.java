package com.itwang6.beans.factory.application;

import com.itwang6.BeansException;
import com.itwang6.beans.factory.ConfigurableListableBeanFactory;
import com.itwang6.beans.factory.aware.ApplicationContextPostProcessor;
import com.itwang6.beans.factory.event.*;
import com.itwang6.beans.factory.postProcessor.BeanFactoryPostProcessor;
import com.itwang6.beans.factory.postProcessor.BeanPostProcessor;
import com.itwang6.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:27  9:44
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext, ApplicationEventPublisher {



    private ApplicationEventMulticaster applicationEventMulticaster;


    @Override
    public void refresh() throws BeansException {
        // 此处由子类创建beanfactory
        refreshBeanFactory();

        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 使用postProcessor提供applicationContext 在执行bean的创建的时候会执行这个方法
        // 这个是第一个被加入的PostProcessor 所以会被第一个执行
        beanFactory.addPostProcessor(new ApplicationContextPostProcessor(this));

        // 执行几大流程
        // 1 执行beanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);

        // 提前注册beanPostProcessor类型的bean 因为其他bean在执行的时候 需要这些bean
        invokeBeanPostProcessor(beanFactory);

        // 注册广播器
        registerMulicaster();

        // 注册监听器
        registerApplicationListener();

        // 提前初始化单例
        beanFactory.preInstantiateSingletons();

        // 差发布事件了
        finishRefresh();



    }

    private void finishRefresh() {
        publishEvent(new ContextClosedEvent(this));
    }

    private void registerApplicationListener() {
        Map<String, ApplicationListener> beansOfType = getBeansOfType(ApplicationListener.class);
        for(ApplicationListener<?> listener : beansOfType.values()){
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    private void registerMulicaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster  = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton("MULTI_CASTER", applicationEventMulticaster);

    }

    private void invokeBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        // 拿到就是已经被实例化的了
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for(BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()){
            beanFactory.addPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public void publishEvent(ApplicationEvent applicationEvent) {
        applicationEventMulticaster.multicastEvent(applicationEvent);
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
