package com.luban.test;

import com.luban.service.UserService;
import com.luban.service.UserServiceImpl;
import com.luban.util.BeanFactory;

public class SpringMyTest {
    public static void main(String[] args) {

        BeanFactory beanFactory = new BeanFactory("spring.xml");
        UserService bean = (UserService)beanFactory.getBean("service");
        bean.find();
    }
}
