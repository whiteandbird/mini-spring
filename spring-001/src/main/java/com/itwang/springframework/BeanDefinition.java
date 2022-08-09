package com.itwang.springframework;

import lombok.Data;

@Data
public class BeanDefinition {
    /**
     * 直接保存实例
     */
    private Object bean;

    public BeanDefinition(Object bean){
        this.bean = bean;
    }
}
