package com.itwang6;

import com.itwang6.beans.factory.application.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:09:04  14:00
 */
public class TestAop {

    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:TestAop.xml");
        IUserService userService = context.getBean("userService", UserService.class);
        userService.sayHello();
    }
}
