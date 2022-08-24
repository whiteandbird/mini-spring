package com.itwang3.factory.bean;

public class UserService {

    private String name;

    public UserService(String name){
        this.name = name;
    }

    public UserService(){

    }

    public void queryUserInfo(){
        System.out.println("查询用户信息");
    }

    @Override
    public String toString() {

        return "this is to String：UserService{" +
                "name='" + name + '\'' +
                '}';
    }
}
