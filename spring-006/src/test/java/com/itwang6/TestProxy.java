package com.itwang6;

import com.itwang6.beans.factory.aop.AdvisedSupport;
import com.itwang6.beans.factory.aop.MethodMatcher;
import com.itwang6.beans.factory.aop.TargetSource;
import com.itwang6.beans.factory.aop.aspect.AspectJExpressionPointCut;
import com.itwang6.beans.factory.aop.proxy.Cglib2AopProxy;
import com.itwang6.beans.factory.aop.proxy.JdkDynamicAopProxy;
import com.itwang6.beans.factory.aop.proxy.ReflectInvocation;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:30  21:26
 */
public class TestProxy {
    @Test
    public void testProxy(){
        Object targetObj = new UserService();
        IUserService userService = (IUserService)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler(){

            MethodMatcher methodMatcher = new AspectJExpressionPointCut("execution(* com.itwang6.UserService.*(..))");

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(methodMatcher.matches(method, targetObj.getClass())){
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try{
                            Thread.sleep(100);
                            return invocation.proceed();
                        }finally {
                            System.out.println("监控 by AOP");
                            System.out.println("方法名称: "+invocation.getMethod().getName());
                            System.out.println("方法耗时: "+(System.currentTimeMillis()-start));
                            System.out.println("监控 - END\r\n");
                        }
                    };

                    return methodInterceptor.invoke(new ReflectInvocation(targetObj, method, args));

                }
                return method.invoke(targetObj, args);
            }
        });
        userService.sayHello();
    }

    @Test
    public void test_aop() throws Exception{
        AspectJExpressionPointCut pointCut = new AspectJExpressionPointCut("execution(* com.itwang6.UserService.*(..))");
        Class<UserService> clazz = UserService.class;

        System.out.println(pointCut.matches(clazz));
        Method hello = clazz.getDeclaredMethod("hello");
        System.out.println(pointCut.matches(hello, clazz));
    }

    @Test
    public void test_proxy(){
        UserService userService = new UserService();
        AspectJExpressionPointCut aspectJExpressionPointCut = new AspectJExpressionPointCut("execution(* com.itwang6.IUserService.*(..))");
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(userService);


        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation methodInvocation) throws Throwable {
                System.out.println("执行切面方法之前");
                Object res =  methodInvocation.proceed();
                System.out.println("执行切面方法之后");
                return res;
            }
        };

        advisedSupport.setMethodInterceptor(methodInterceptor);
        advisedSupport.setMethodMatcher(aspectJExpressionPointCut);
        advisedSupport.setTargetSource(targetSource);

        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        IUserService proxy = (IUserService)jdkDynamicAopProxy.getProxy();
        proxy.sayHello();
    }

    @Test
    public void testCglib(){
        UserService userService = new UserService();
        AspectJExpressionPointCut aspectJExpressionPointCut = new AspectJExpressionPointCut("execution(* com.itwang6.UserService.*(..))");
        TargetSource targetSource = new TargetSource(userService);

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(targetSource);

        advisedSupport.setMethodMatcher(aspectJExpressionPointCut);
        advisedSupport.setMethodInterceptor(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation methodInvocation) throws Throwable {
                System.out.println("cglibt 执行方法钱");
                Object proceed = methodInvocation.proceed();
                System.out.println("cglib 执行方法后");
                return proceed;
            }
        });

        UserService userService1 = (UserService)new Cglib2AopProxy(advisedSupport).getProxy();
        userService1.sayHello();
    }

}
