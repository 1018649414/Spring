package com.luban.unit;

import com.luban.annotation.Entity;

import java.lang.annotation.Annotation;

public class CommUtil {

    /**
     * 通过一个对象构建一条查询的sql语句
     * @param object
     */
    public static String buildQuerySqlForEntity(Object object){
        Class clazz = object.getClass();
        //setup1 判断是否加了这个注解
        System.out.println(clazz.isAnnotationPresent(Entity.class));
        if (clazz.isAnnotationPresent(Entity.class)){
            //setup2 得到注解 如果加s代表得到所有的注解,不加s代表得到一个注解
            Entity entity = (Entity)clazz.getAnnotation(Entity.class);
            //setup3 调用方法
            String entityName = entity.value();
            System.out.println(entityName);
        }
        String sql = "select * from ";
        return "";
    }
}
