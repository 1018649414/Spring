package com.luban.app;

import com.luban.dao.Dao;
import com.luban.dao.IndexDao;
import com.luban.dao.TestDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 *
 *
 *
 *
 *
 * 2018-10-30(18)-springAOP jdk动态代理-子路课程
 * 第50分钟(重难点)
 *
 *
 *
 *
 *
 *
 *
 */
//切面默认是单例模式,也就是bean是单例的一样,scope默认单例.
@Component
@Aspect
public class lubanAspectj {

    //声明一个父类,去继承扩展,引入.
    //找到包下面的所有的类,引入某个接口的所有实现.
    /**
     * 找到com.luban.dao下面的所有类,引入Dao中IndexDao实现Dao的所有方法,
     * 也就相当于复制了一遍IndexDao的实现.
     * @DeclareParents(value = "com.luban.dao.*",defaultImpl = IndexDao.class)
     * public static Dao dao;
     */

    /**
     * 这里把IndexDao.class改成了TestDaoc.class,这个意思也就是说把TestDao中实现Dao接口
     * 的所有方法复制给com.luban.dao下面所有没有实现的类中.这些类都可以调用TestDao中实现的
     * 方法.
     * @DeclareParents(value = "com.luban.dao.*",defaultImpl = TestDao.class)
     * public static Dao dao;
     */
//    @DeclareParents(value = "com.luban.dao.*",defaultImpl = TestDao.class)
//    public static Dao dao;

//    execution最小力度能定义到方法的参数,返回类型,修饰符,力度比within更小.
    @Pointcut("execution(* com.luban.dao.*.*(..))") //声明一个切点
    public void pointCutExecution(){

    }
//    within只能够定义到类不能在往下定义了.
    @Pointcut("within(com.luban.dao.*)")
    public void pointCutWithin(){

    }
    //使用args的方式,定义到方法的参数
    @Pointcut("args(java.lang.String)")
    public void pointCutArgs(){

    }

    //使用注解的方式
    @Pointcut("@annotation(com.luban.anno.Luban)")
    public void pointCutAnnotation(){

    }

    //使用注解的方式
    @Pointcut("within(com.luban.dao.*))")
    public void pointCutAround(){

    }

    /**
     * JoinPoint
     *      这个类中包含了对连接的描述,比如连接点的名称,返回类型,当前对象,目标对象等等信息
     */
    //@Before前置通知
    @Before("pointCutWithin()")
    public void befor(JoinPoint joinPoint){
        System.out.println("before");
//        //getThist是代理对象
//        System.out.println(joinPoint.getThis());
//        //getTarget是目标对象
//        System.out.println(joinPoint.getTarget());
    }

    //后置通知
    @After("pointCutWithin()")
    public void after(){
        System.out.println("after");
    }

    /**
     * JoinPoint
     *      这个类中包含了对连接的描述,比如连接点的名称,返回类型,当前对象,目标对象等等信息
     * ProceedingJoinPoint继承了JoinPoint类.拥有更多的方法.包括proceed,还有修改连接点参数值
     */
    //环绕通知,ProceedingJoinPoint来表示连接点(连接点就是方法)
    @Around("pointCutWithin()")
    public void around(ProceedingJoinPoint  pdj){
        /*----------------------测试单例模式-----------------------------*/
        System.out.println(this.hashCode());
        /*----------------------测试单例模式-----------------------------*/
        /*---------------------------------------------------*/
        //getArgs表示连接点的参数信息,可以直接获得
        Object[] args = pdj.getArgs();
        if (args!=null && args.length>0){
            for (int i = 0; i < args.length; i++) {
                args[i]+="world";
            }
        }
        //getArgs表示连接点的参数信息,可以直接获得
        /*---------------------------------------------------*/

        System.out.println("环绕通知1");
        try {
            //这个proceed方法代表执行了连接点
            pdj.proceed();
            //这里的args是改变参数后的值,传给连接点
            //pdj.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("环绕通知2");
    }

    //@Before前置通知
//    @Before("pointCutAnnotation()")
//    public void beforeWithin(){
//        System.out.println("before");
//    }


//    @Before("pointCutArgs()")
//    public void beforeWithin(){
//        System.out.println("before");
//    }

   //带表达式的
//    @Before("pointCutWithin() &&pointCutArgs()")
//    public void beforeWithin(){
//        System.out.println("before");
//    }

    //通知要关联切点,这样才能通知到
//    @Before("pointCutWithin()")
//    public void beforeWithin(){
//        System.out.println("before");
//    }
    /**
     * location通知位置
     * logic通知内容
     */

    //通知要关联切点,这样才能通知到
//    @Before("pointCutExecution()")
//    public void before(){
//        System.out.println("before");
//    }

}
