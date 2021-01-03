package com.luban.test;

import com.luban.annotation.Annotation;
import com.luban.dao.DaoFactoryBean;
import com.luban.dao.IndexService;
import com.luban.dao.TempDaoFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestFactoryBean {
    public static void main(String[] args) {
//        ClassCastException:类型转换异常
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Annotation.class);
        //daoFactoryBean:这个就是getObject返回回来的类对象
//        TempDaoFactoryBean daoFactoryBean = (TempDaoFactoryBean)ac.getBean("daoFactoryBean");
//        System.out.println(daoFactoryBean.getMsg1());
//        daoFactoryBean.test();

        //&daoFactoryBean:这个就是当前类对象
//        DaoFactroyBean daoFactoryBean1 = (DaoFactroyBean)ac.getBean("&daoFactoryBean");
//        daoFactoryBean1.testBean();

        //这里单独注入,测试第二种。把这个注解注释掉测试@ComponentScan("com")
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
//        ac.getEnvironment().setActiveProfiles(); //可以让某个对象不产生作用
        ac.register(Annotation.class);
        ac.register(IndexService.class);
        //开启扫描的API
        ac.scan("com");
        System.out.println("生成令牌");
        ac.refresh();

        System.out.println(ac.getBean(IndexService.class).getClass().getName());
    }
}
