package com.itwang6.beans.factory.aop.inteceptors;

import com.itwang6.beans.factory.aop.advise.MethodAfterAdvice;
import com.itwang6.beans.factory.aop.advise.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:09:04  13:49
 */
public class MethodAroundInterceptor implements MethodInterceptor {

    private MethodBeforeAdvice before;

    private MethodAfterAdvice after;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        if(null != before) before.before(methodInvocation.getThis(), methodInvocation.getMethod(), methodInvocation.getArguments());
        Object proceed = methodInvocation.proceed();
        if(null != after) after.after(methodInvocation.getThis(), methodInvocation.getMethod(), methodInvocation.getArguments());
        return proceed;
    }
}
