package com.itwang.factory;

import com.itwang.factory.bean.UserService;
import com.itwang.factory.config.BeanDefinition;
import com.itwang.factory.support.DefaultListableBeanFactory;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;


public class ApiTest {
    @Test
    public void test(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class));
        UserService userService  = (UserService)beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
    @Test
    public void testEnhancer(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object o = enhancer.create(new Class[]{String.class}, new Object[]{"wang"});
        System.out.println(o);
    }

    public static void main(String[] args) {

    }
}
