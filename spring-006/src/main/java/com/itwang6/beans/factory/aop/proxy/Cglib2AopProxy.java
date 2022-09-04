package com.itwang6.beans.factory.aop.proxy;

import com.itwang6.beans.factory.aop.AdvisedSupport;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:09:03  8:21
 */
@Slf4j
public class Cglib2AopProxy implements AopProxy{

    private AdvisedSupport advisedSupport;

    public Cglib2AopProxy(AdvisedSupport advisedSupport){
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(advisedSupport.getTargetSource().getClasses());
        enhancer.setSuperclass(advisedSupport.getTargetSource().getTarget().getClass());
        enhancer.setCallback(new CglibInterceptor(advisedSupport));
        return enhancer.create();
    }

    private static class CglibInterceptor  implements MethodInterceptor {

        private AdvisedSupport advisedSupport;

        public CglibInterceptor(AdvisedSupport advisedSupport){
            this.advisedSupport = advisedSupport;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            log.info("===cglib 的方法======methodName:{} methodClass:{}", method.getName(), method.getDeclaringClass().getName());
            CglibReflection cglibReflection = new CglibReflection(advisedSupport.getTargetSource().getTarget(), method, objects, methodProxy);
            if(advisedSupport.getMethodMatcher().matches(method, advisedSupport.getTargetSource().getTarget().getClass())){
                return advisedSupport.getMethodInterceptor().invoke(cglibReflection);
            }
            return cglibReflection.proceed();
        }
    }

    private static class CglibReflection extends ReflectInvocation{

        private MethodProxy methodProxy;

        public CglibReflection(Object target, Method method, Object[] args) {
            super(target, method, args);
        }

        public CglibReflection(Object target, Method method, Object[] args, MethodProxy methodProxy){
            super(target, method, args);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            log.info("调用cglib的方法重写");
            log.info("method class"+methodProxy.getClass());
            return methodProxy.invoke(this.getTarget(), this.getArguments());
        }
    }
}
