package com.itwang6.beans.factory.aop.proxy;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:09:03  9:08
 */
public class ReflectInvocation implements MethodInvocation {

    private Object target;

    private Method method;

    private Object[] args;

    public ReflectInvocation(Object target, Method method, Object[] args){
        this.args = args;
        this.target = target;
        this.method = method;
    }

    public Object getTarget() {
        return target;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, args);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
