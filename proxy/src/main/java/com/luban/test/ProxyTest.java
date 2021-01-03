package com.luban.test;


import com.luban.dao.LubanDao;
import com.luban.dao.LubanDaoImpl;
import com.luban.dao.UserDao;
import com.luban.dao.UserDaoImpl;
import com.luban.proxy.ProxyUtil;
import com.luban.util.LubanInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        /*----------------------------自定义的动态代理------------------------------*/
//        测试1
//        Proxy这个是java的代理类.
//        UserDao proxy = (UserDao) ProxyUtil.newInstance(new UserDaoImpl());
//        proxy.query();
//        proxy.query("luban");
//        测试2
//        LubanDao proxy = (LubanDao) ProxyUtil.newInstance(new LubanDaoImpl());
//        proxy.query();
//        System.out.println(proxy.proxy());
//        System.out.println(proxy.proxy1("123555"));
        /*----------------------------自定义的动态代理------------------------------*/
        /*----------------------------JDK的动态代理------------------------------*/
        //ClassLoader loader:就和自定义的代理一样,把类Loader到内存中去.
//        public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,InvocationHandler h);
        LubanDao lubanDao = (LubanDao) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(),new Class[]{LubanDao.class},new LubanInvocationHandler(new LubanDaoImpl()));
        lubanDao.query();
        lubanDao.proxy();
        /*----------------------------JDK的动态代理------------------------------*/
    }
}
