package com.luban.test;

import com.luban.config.Appconfig;
import com.luban.dao.IndexDaoImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestBean {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Appconfig.class);
        IndexDaoImpl indexDaoImpl = (IndexDaoImpl) applicationContext.getBean(IndexDaoImpl.class);
    }
}
