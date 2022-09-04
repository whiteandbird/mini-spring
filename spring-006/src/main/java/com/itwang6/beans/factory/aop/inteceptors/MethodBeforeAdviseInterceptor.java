package com.itwang6.beans.factory.aop.inteceptors;

import com.itwang6.beans.factory.aop.advise.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MethodBeforeAdviseInterceptor implements MethodInterceptor {

    private MethodBeforeAdvice methodBeforeAdvice;

    public MethodBeforeAdviseInterceptor(MethodBeforeAdvice beforeAdvice){
        this.methodBeforeAdvice = beforeAdvice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        methodBeforeAdvice.before(methodInvocation.getThis(), methodInvocation.getMethod(), methodInvocation.getArguments());
        return methodInvocation.proceed();
    }
}
