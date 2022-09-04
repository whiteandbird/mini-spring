package com.itwang6.beans.factory.aop.inteceptors;

import com.itwang6.beans.factory.aop.advise.MethodAfterAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MethodAfterAdviseInterceptor implements MethodInterceptor {

    private MethodAfterAdvice methodAfterAdvice;

    public MethodAfterAdviseInterceptor(MethodAfterAdvice methodAfterAdvice){
        this.methodAfterAdvice = methodAfterAdvice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object res = methodInvocation.proceed();
        methodAfterAdvice.after(methodInvocation.getThis(), methodInvocation.getMethod(), methodInvocation.getArguments());
        return res;
    }
}
