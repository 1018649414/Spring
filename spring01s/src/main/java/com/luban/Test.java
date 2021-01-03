package com.luban;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        //这里解析的是xml配置文件类
//        ClassPathXmlApplicationContext
//                context
//                = new ClassPathXmlApplicationContext("classpath:spring.xml");
//                这里是用xml的方式
//      IndexService service = (IndexService)context.getBean("service");
//                这里使用annotation注解的方式和xml一起使用
//        xml负责开启注解(必须)也可以配置依赖关系.annotation只能配置依赖关系,不能开启注解
//        IndexService service = (IndexService)context.getBean("indexService");
//        service.service();

        //这是解析注解配置的类
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(Spring.class);
        //这是直接使用java Configration的方法调用
        //使用的是javaConfig(用注解开启注解必须)和annotation(注解配置类之间的依赖关系)这两种方式
        IndexService service = (IndexService)ac.getBean("indexService");
        service.service();
        IndexService service1 = (IndexService)ac.getBean("indexService");
        service1.service();
        System.out.println(service.hashCode());
        System.out.println(service1.hashCode());

    }
}
