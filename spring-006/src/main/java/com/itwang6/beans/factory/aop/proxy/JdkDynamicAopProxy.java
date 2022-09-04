package com.itwang6.beans.factory.aop.proxy;

import com.itwang6.beans.factory.aop.AdvisedSupport;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:09:03  8:22
 */
@Slf4j
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport){
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), advisedSupport.getTargetSource().getClasses(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(advisedSupport.getMethodMatcher().matches(method, advisedSupport.getTargetSource().getClass())){
            // 进入切面 此切面是封装的
            log.info("使用了代理的方法 methodName: "+method.getName());
//            new ReflectInvocation(advisedSupport.getTargetSource().getTarget(), method, args).
            return advisedSupport.getMethodInterceptor().invoke(new ReflectInvocation(advisedSupport.getTargetSource().getTarget(), method, args));
        }
        log.info("正常的方法:methodName: "+method.getName());
        return method.invoke(advisedSupport.getTargetSource().getTarget(), args);
    }
}
