package com.itwang6.beans.factory.aop.advise;

import java.lang.reflect.Method;

public interface MethodAfterAdvice extends AfterAdvice{

    void after(Object target, Method method, Object[] args);
}
