package com.itwang6.beans.factory.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * aop 配置管理
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:30  22:27
 */
public class AdvisedSupport {
    // 被代理的目标对象
    private TargetSource targetSource;

    private MethodMatcher methodMatcher;

    private MethodInterceptor methodInterceptor;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }
}
