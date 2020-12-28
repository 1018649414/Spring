package com.luban.entity;

import com.luban.annotation.Entity;

//注解可以放在类上面,属性上面,方法上面
//value就是注解类中的一个方法
//@Entity(value = "你好",name = false)
@Entity(value = "city")
public class CityEntity {
//    @Entity(value = "city")
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
