package com.itwang6;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:30  21:44
 */
public class UserServiceInteceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long start = System.currentTimeMillis();
        try{
            return methodInvocation.proceed();
        }finally {
            System.out.println(" 监控 begin aop");
            System.out.println("方法名称 "+methodInvocation.getMethod());
            System.out.println("方法耗时 "+(System.currentTimeMillis()-start));
            System.out.println("监控 -end");
        }
    }
}
