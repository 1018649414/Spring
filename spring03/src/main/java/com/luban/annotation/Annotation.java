package com.luban.annotation;

import com.luban.dao.DaoFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
//@ComponentScan("com")
//@ImportResource("classpath:spring.xml")
public class Annotation {
    //通过这个方法和xml中的bean配置匹配。
//    @Bean
//    public DaoFactoryBean daoFactoryBean(){
//        DaoFactoryBean daoFactoryBean = new DaoFactoryBean();
//        daoFactoryBean.setMsg("msg1,msg2,msg3");
//        return daoFactoryBean;
//    }
}
