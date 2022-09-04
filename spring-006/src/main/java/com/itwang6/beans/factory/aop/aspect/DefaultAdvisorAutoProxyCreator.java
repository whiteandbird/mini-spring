package com.itwang6.beans.factory.aop.aspect;

import com.itwang6.beans.factory.BeanFactory;
import com.itwang6.beans.factory.aop.AdvisedSupport;
import com.itwang6.beans.factory.aop.PointCut;
import com.itwang6.beans.factory.aop.TargetSource;
import com.itwang6.beans.factory.aop.advise.ProxyFactory;
import com.itwang6.beans.factory.aop.advisor.Advisor;
import com.itwang6.beans.factory.aop.advisor.AspectJExpressionPointCutAdvisor;
import com.itwang6.beans.factory.aware.BeanFactoryAware;
import com.itwang6.beans.factory.postProcessor.InstantiationAwareBeanPostProcessor;
import com.itwang6.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:09:04  9:46
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    // 重写这个即可 这个是代理的处理流程
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClazz, String beanName) {
        // 如果是内置的 避免AspectJExpressionPointCutAdvisor 读取陷入无限递归
        if(isInfrastructureClass(beanClazz)) return null;
        Map<String, AspectJExpressionPointCutAdvisor> beansOfType = beanFactory.getBeansOfType(AspectJExpressionPointCutAdvisor.class);
        for(AspectJExpressionPointCutAdvisor advisor : beansOfType.values()){
            PointCut pointCut = advisor.getPointCut();
            // 代理是以类为基础的 类是最小的单元
            // 判断能不能切到这个类
            if(!pointCut.getClassFilter().matches(beanClazz)){
                continue;
            }
            // 进行封装了
            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = null;
            try{
                targetSource = new TargetSource(beanClazz.getDeclaredConstructor().newInstance());
            }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
                e.printStackTrace();
            }
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodMatcher(pointCut.getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            return new ProxyFactory(advisedSupport).getProxy();
        }
        return null;
    }

    @Override
    public Object postProcessorBeforeInitializtion(Object object, String beanName) {
        return null;
    }

    @Override
    public Object postProcessorAfterInitialization(Object object, String beanName) {
        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass)
                || PointCut.class.isAssignableFrom(beanClass)
                || Advisor.class.isAssignableFrom(beanClass);

    }
}
