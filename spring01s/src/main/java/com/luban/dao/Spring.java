package com.luban.dao;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
//开始扫描和开启注解,这里使用的javaConfig
@ComponentScan("com.luban")
//三种混合使用,这里使用的xml
//@ImportResource("classpath:spring.xml")
public class Spring {
}
