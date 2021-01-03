package com.luban;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
@Lazy //懒加载默认就开启true
public class IndexDaoImpl implements IndexDao{

    public IndexDaoImpl(){
        System.out.println("holle word bean生命周期回调");
    }

    @PostConstruct
    public void init(){
        System.out.println("holle word bean 生命周期注解方式回调");
    }
}
