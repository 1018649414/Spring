package com.luban.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LubanInvocationHandler implements InvocationHandler {

    Object target;
    public LubanInvocationHandler(Object target){
        this.target = target;
    }
    /**
     * @description: 代理对象
     * @param proxy : 代理对象
     * @param method : 目标对象
     * @param args : 
     * @author: yan.luo
     * @date: 2020/12/29
     * @time: 13:53
     * @return : java.lang.Object
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("LubanInvocationHandler jdk");
        return method.invoke(target,args);
    }
}
