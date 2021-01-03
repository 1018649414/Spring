package com.luban.proxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ProxyUtil {

    /**
     * content---->string
     * .java io
     * .class
     * .new 反射
     * @return
     */
    public static Object newInstance(Object target){
        Object proxy = null;
        //通过对象得到接口,接口可以实现多个,所以这里拿取的第一个.这里可以完善.
        Class targetInterface = target.getClass().getInterfaces()[0];
        //下面是通过接口去得到所有的方法名等等
        //获取所有的方法
        Method methods[] = targetInterface.getDeclaredMethods();
        String line="\n";
        String tab="\t";
        //获取到名称
        String infName = targetInterface.getSimpleName();
        String content = "";
        String packageContent ="package com.google;"+line;
        //获取到引入的包名
        String importContent = "import "+targetInterface.getName()+";"+line;
        //放置类名
        String clazzFirstLineContent = "public class $Proxy implements "+infName+"{"+line;
        //放置属性
        String filedContent = tab+"private "+infName+" target;"+line;
        //放置构造方法
        String constructorContent = tab+"public $Proxy ("+infName+" target){"+line+tab+tab+"this.target=target;"+line+tab+"}"+line;

        String methodContent = "";
        for (Method method : methods) {
            //获取到class的方法返回类型
            String returnTypeName = method.getReturnType().getSimpleName();
            //获取到class的方法的名称
            String methodName = method.getName();
            //获取到参数的类型
            //String.class,Long.class
            Class args[] = method.getParameterTypes();
            String argsContent = "";
            String paramsContent = "";
            int flag = 0;
            for (Class arg : args) {
                //获取到参数的类型
                String temp = arg.getSimpleName();
                //String
                //String p1,String p2
                argsContent+=temp+" p"+flag+",";
                paramsContent+="p"+flag+",";
                flag++;
            }
            if (argsContent.length()>0){
                argsContent=argsContent.substring(0,argsContent.lastIndexOf(",")-1);
                paramsContent=paramsContent.substring(0,paramsContent.lastIndexOf(",")-1);
            }
            //描述方法的一行.
            methodContent+=tab+"public "+returnTypeName+" "+methodName+"("+argsContent+") {"+line
                            +tab+tab+"System.out.println(\"log\");"+line;
                            if (returnTypeName.equals("void")){
                                methodContent+=tab+tab+" target."+methodName+"("+paramsContent+");"+line
                                        +tab+"}"+line;
                            }else {
                                methodContent+=tab+tab+" return target."+methodName+"("+paramsContent+");"+line
                                        +tab+"}"+line;
                            }


        }
        content=packageContent
                +importContent
                +clazzFirstLineContent
                +filedContent
                +constructorContent
                +methodContent
                +"}";
        File file = new File("h:\\com\\google\\$Proxy.java");
        File testFile = new File("h:\\com\\google");
        try {
            if (!testFile.exists()) {
                testFile.mkdirs();// 能创建多级目录
            }
            FileWriter fileOutputStream = new FileWriter(file);
            fileOutputStream.write(content);
            fileOutputStream.flush();
            fileOutputStream.close();

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            //文件管理器
            StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null,null,null);
            //把文件放到文件管理器中
            Iterable units = fileMgr.getJavaFileObjects(file);
            //把任务进去
            JavaCompiler.CompilationTask t = compiler.getTask(null,fileMgr,null,null,null,units);
            t.call();
            //loader到网络上的类
            URL[] urls = new URL[]{new URL("file:H:\\\\")};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class clazz = urlClassLoader.loadClass("com.google.$Proxy");
            //这个类是有构造方法的,所以你不能直接new出来,就是不能直接clazz.newInstance()创建一个对象出来.clazz.newInstance()是默认的构造方法.
            //必须先获得构造方法
            Constructor constructor = clazz.getConstructor(targetInterface);
            //构造方法可以直接构造出一个对象.
            proxy = constructor.newInstance(target);
//            clazz.newInstance(); //因为有显性的构造方法,所以直接获得对象的方式是错误的.
            fileMgr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proxy;
    }
}
