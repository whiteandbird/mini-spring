package com.itwang6.beans.factory.aop.advise;

import com.itwang6.beans.factory.aop.AdvisedSupport;
import com.itwang6.beans.factory.aop.proxy.AopProxy;
import com.itwang6.beans.factory.aop.proxy.Cglib2AopProxy;
import com.itwang6.beans.factory.aop.proxy.JdkDynamicAopProxy;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:09:04  8:01
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport){
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy(){
        return createProxy().getProxy();
    }

    private AopProxy createProxy() {
        if(advisedSupport.isProxyTargetClass()){
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
