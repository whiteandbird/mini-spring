package com.itwang6;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:27  12:34
 */
public class UserService {
    private String name;

    private IUserDao userDao;

    public void sayHello(){
        System.out.println(userDao.hashCode());
        System.out.println(" ====== say hello ="+userDao.queryUserName("1")+"=========");
    }

    public void init1(){
        System.out.println("===============this is init 1======================");
    }

    public void destroy(){
        System.out.println("==============destroy==============================");
    }
}
