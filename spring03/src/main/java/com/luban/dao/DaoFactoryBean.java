package com.luban.dao;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * FactoryBean和BeanFactory的区别作用
 * FactoryBean：当我们的一类的依赖关系很复杂的时候，我们想对外提供简单的关系和使用配置出来的话可以使用，经典的场景就是我们Mybatis当中
 * 的SqlSessionFactoryBean这么一个对象，还有hibernate中SessionFactoryBean。
 * 在需要配置外部代码的时候可以使用，这样直接进行注入到Spring容器中，也可以提供给外部一定的参数自己封装。
 *
 * BeanFactory：是一个spring的bean的描述类。就像Class类一样描述类
 *
 * 如果你的类实现了FactoryBean
 * 那么Spring容器当中存在两个对象
 * 一个叫getObject()返回的对象
 * 还有一个是当前对象
 *
 * getObject得到的对象存的是当前类指定的名字:daoFactoryBean.
 * 当前类对象是"&"+当前类的名字.
 *
 * 相当于spring自己创建了一个beanId.
 * Mybatis充分利用了这个技术.Hibernate也充分利用了.
 */
//@Component("daoFactoryBean")
public class DaoFactoryBean implements FactoryBean {
    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void testBean(){
        System.out.println("testBean");
    }
    public Object getObject() throws Exception {
        TempDaoFactoryBean tempDaoFactoryBean = new TempDaoFactoryBean();
        String[] split = msg.split(",");
        tempDaoFactoryBean.setMsg1(split[0]);
        tempDaoFactoryBean.setMsg2(split[1]);
        tempDaoFactoryBean.setMsg3(split[2]);
        return tempDaoFactoryBean;
    }

    public Class<?> getObjectType() {
        return TempDaoFactoryBean.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
