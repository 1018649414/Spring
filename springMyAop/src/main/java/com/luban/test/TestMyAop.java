package com.luban.test;

import com.luban.app.Appconfig;
import com.luban.dao.Dao;
import com.luban.dao.IndexDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Proxy;

public class TestMyAop {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Appconfig.class);
        //这里要报错,因为加了AOP就会有动态代理,动态代理后出来的类和IndexDao的名称是不一样的.所以没有这个类型的IndexDao.
//        IndexDao dao = applicationContext.getBean(IndexDao.class);

        //Appconfig类中@EnableAspectJAutoProxy(proxyTargetClass = true),这样就不会报错了.否则会报错.原因如下:
        //如果这里是true那么就是CGLIB的动态代理
        //如果这里是false那么就是JDK的动态代理
        Dao dao = (Dao) applicationContext.getBean("indexDao");
/*--------------------------测试@DeclareParents-------------------------------------*/
//        Dao dao1 = (Dao) applicationContext.getBean("orderDao");
//        dao1.query3();
//        System.out.println(dao1);
/*--------------------------测试@DeclareParents-------------------------------------*/

//        System.out.println(dao instanceof IndexDao);
        //JDK动态代理就是用的接口,基于接口的方式实现的
//        System.out.println(dao instanceof Dao);
        //JDK动态代理,其实是继承了Proxy这个类
//        System.out.println(dao instanceof Proxy);
//        dao.query1("123");
//        System.out.println("-----------------------------");
//        dao.query2();
        //测试注解
        dao.query3();
        dao.query3();

        //获取动态代理的类
//        Class<?>[] classes = new Class[]{Dao.class};
//        //字节数组,写到内存中.进行运行使用.
//        byte[] lubanAas = ProxyGenerator.generateProxyClass("lubanAa", classes);
//        File file = new File("H:\\Test.class");
//        try {
//            FileOutputStream fileWriter = new FileOutputStream(file);
//            fileWriter.write(lubanAas);
//            fileWriter.flush();
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        /*
        动态代理两种实现方式:1.聚合接口,2.继承
        JDK动态代理用的是继承的方式.
        问:为什么JDK动态代理是用继承而不是用聚合接口呢?
        答:
         */
    }
}
