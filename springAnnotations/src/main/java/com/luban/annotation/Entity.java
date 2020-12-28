package com.luban.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 如何确定注解存放位置,那么就需要原注解
 * @Target:原注解
 * @Target(ElementType.TYPE)表示这个注解存放的位置,
 * Type:表示出现在类上面
 * METHOD:表示出现正在方法上
 * FIELD:表示出现在属性和类上面
 * @Target({ElementType.PARAMETER,ElementType.FIELD}):用逗号隔开表示可以多个存放.
 *
 * 注解类中除了value名称的方法在用注解的时候不用标明value直接填入值就可以了,其他的都要标明名称后才能填值.
 * 如果注解类中有两个或两个以上的方法且其中有一个方法名为value的时候,那么value就必须标明后才能填值.
 *
 * 注解是有生命周期的,默认情况下只是存在源码中,当JVM编译源码的时候会自动消失.
 * @Retention(RetentionPolicy.CLASS):设置在运行时编译都情况,可以是运行时存在,可以在源代码中存在,可以编译后存在.
 * SOURCE:表示仅仅存在于源代码当中,进行编译后不会存在class文件.
 * CLASS:表示只会存在class文件中,在java运行的时候还是会被java虚拟机忽略
 * RUNTIME:表示只有在运行时才可以在java虚拟机认识.
 *
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {
     String value();//这就是一个方法
     String type() default "";//加入 defaule表示默认值
//     boolean name() default true;//
}
