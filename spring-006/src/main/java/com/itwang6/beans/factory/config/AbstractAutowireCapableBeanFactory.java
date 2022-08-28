package com.itwang6.beans.factory.config;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.itwang6.BeansException;
import com.itwang6.PropertyValue;
import com.itwang6.PropertyValues;
import com.itwang6.beans.factory.lifescope.DisposableAdapter;
import com.itwang6.beans.factory.lifescope.DisposableBean;
import com.itwang6.beans.factory.lifescope.InitializeBean;
import com.itwang6.beans.factory.postProcessor.BeanPostProcessor;
import com.itwang6.beans.factory.support.AbstractBeanFactory;
import com.itwang6.beans.factory.support.InstantiationStrategy;
import com.itwang6.beans.factory.support.SimpleInstantiationStrategy;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@Slf4j
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory{

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try{
            bean = createBeanInstance(beanName, beanDefinition, args);
            // 优先填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
            // 进行初始化操作
            bean = initializeBean(bean, beanName, beanDefinition);
        }catch (Exception e){
            e.printStackTrace();
            throw new BeansException("创建bean失败: "+beanName);

        }
        // 注册销毁方法
        registryDisposableBeanIfNessary(bean, beanName, beanDefinition);
        addSingleton(beanName, bean);
        return bean;
    }

    protected void registryDisposableBeanIfNessary(Object bean, String beanName, BeanDefinition beanDefinition){
        if(bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())){
            registryDisposableBean(beanName, new DisposableAdapter(bean, beanName, beanDefinition));
        }
    }

    @Override
    public Object applyBeanPostProcessorAfterInitialization(Object bean, String beanName) {
        Object res = bean;
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
        for(BeanPostProcessor beanPostProcessor : beanPostProcessors){
            Object cur = beanPostProcessor.postProcessorAfterInitialization(bean, beanName);
            if(cur == null)return res;
            res = cur;
        }
        return res;
    }

    @Override
    public Object applyBeanPostProcessorBeforeInitialzation(Object bean, String beanName) {
        Object res = bean;
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
        for(BeanPostProcessor beanPostProcessor : beanPostProcessors){
            Object cur = beanPostProcessor.postProcessorBeforeInitializtion(bean, beanName);
            if(cur == null)return res;
            res = cur;
        }
        return res;
    }

    protected Object initializeBean(Object bean, String beanName, BeanDefinition definition){
        Object wrapperBean = bean;
        try{
            wrapperBean = applyBeanPostProcessorBeforeInitialzation(bean, beanName);
            invokeInitMethod(beanName, wrapperBean, definition);
            wrapperBean = applyBeanPostProcessorAfterInitialization(bean, beanName);
        }catch (Exception e){
            log.info("initilizeation Bean error");
            e.printStackTrace();
        }
        return wrapperBean;
    }

    protected void invokeInitMethod(String beanName, Object wrapperBean, BeanDefinition definition) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        log.info("暂时不实现initMethod");
        if(wrapperBean instanceof InitializeBean){
            ((InitializeBean)wrapperBean).afterPropertiesSet();
        }
        String initializationMethodName = definition.getInitializationMethodName();
        if(StrUtil.isNotEmpty(initializationMethodName)){
            Method declaredMethod = wrapperBean.getClass().getDeclaredMethod(initializationMethodName);
            if(null ==  declaredMethod){
                throw new BeansException(beanName + "no this method");
            }
            declaredMethod.invoke(wrapperBean);
        }
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args){
        log.info(beanName+"===start create Instance");
        Constructor constructorTOUse = null;
        Class beanClass = beanDefinition.getBeanClass();
        Constructor[] constructors = beanClass.getConstructors();
        for(Constructor con : constructors){
            if( args!= null && con.getParameterTypes().length == args.length){
                constructorTOUse = con;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructorTOUse, args);
    }


    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    //bean 属性填充就是能注入一些东西
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
        try{
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for(PropertyValue propertyValue : propertyValues.getPropertyValues()){
                // 填充bean
                String propertyName = propertyValue.getPropertyName();
                Object value = propertyValue.getPropertyValue();
                // 引用其他的bean
                if(value instanceof BeanReference){
                    value = getBean(((BeanReference)value).getBeanName());
                }
                BeanUtil.setFieldValue(bean, propertyName, value);
            }
        }catch (Exception e){
            log.info("填充属性异常 beanName: {}, {}",beanName,e);
        }
    }
}
