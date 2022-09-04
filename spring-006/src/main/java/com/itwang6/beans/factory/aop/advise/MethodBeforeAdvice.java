package com.itwang6.beans.factory.aop.advise;

import java.lang.reflect.Method;

public interface MethodBeforeAdvice extends BeforeAdvice{

    void before(Object target, Method method, Object[] args);
}
