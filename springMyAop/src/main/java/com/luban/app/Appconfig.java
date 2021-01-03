package com.luban.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
//@EnableAspectJAutoProxy(proxyTargetClass = false)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("com")
public class Appconfig {
}
