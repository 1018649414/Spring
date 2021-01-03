package com.luban.util;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BeanFactory {
    /**
     *
     */
    Map<String, Object> map = new HashMap();
    //获取xml配置信息
    public BeanFactory(String xml){
        parseXml(xml);
    }

    //解析xml
    public void parseXml(String xml) throws LubanSpringException{
        //获取到根路径
        String path = this.getClass().getResource("/").getPath()+"/"+xml;
        //得到file
        File file = new File(path);
        //dom4j准备
        SAXReader reader = new SAXReader();
        try {
            //获取到xml中的元素
            Document document = reader.read(file);
            //获取到xml中的所有元素
            Element elementRoot = document.getRootElement();
            //获取到是否有设置自动装配
            Attribute attribute = elementRoot.attribute("default-autowire");
            boolean flag = false;
            if (attribute!=null){
                flag = true;
            }
            //遍历其中的所有元素 beans
            for (Iterator<Element> it = elementRoot.elementIterator();it.hasNext();){
                /**
                 * setup1.实例化对象
                 * 两个bean换了怎么办.
                 */
                //获取到beans的子元素,一级子标签
                Element elementFirstrChil = it.next();
                //根据id获取到属性 id
                Attribute attributeId = elementFirstrChil.attribute("id");
                //根据属性获取到属性的value值 id = "dao"
                String beanName = attributeId.getValue();
                //根据class获取到属性名称 class
                Attribute attributeClazz = elementFirstrChil.attribute("class");
                //根据属性获取到属性的value值 class = "com.luban.userService"
                String clazzName = attributeClazz.getValue();
                //获取到class
                Class<?> clazz = Class.forName(clazzName);
                //这里通过反射类包路径名对象new出来.
                //如果是构造方法的话,就不能进行new了.
//                Object object = clazz.newInstance();
                //放到map中,后面可以直接拿去出来,直接通过set方法注入到依赖的类中
                /**
                 * 维护依赖关系
                 * 1、看这个对象有没有依赖(判断是否有property属性,或者判断类是否有属性)
                 * 判断xml中的name名称是否和依赖类中的名称相同.
                 * 如果有则注入
                 */
                Object object = null;
                //bean中的子标签,第二个子标签
                for (Iterator<Element> itSecond = elementFirstrChil.elementIterator();itSecond.hasNext();){
                    /**
                     * 1、得到ref的value,通过value得到对象(map)
                     * 2、得到name的值,然后根据值获取一个Filed的对象,通过field的set方法se对象
                     * <property name="dao" ref="dao"></property>
                     */
                    //获取到子元素
                    Element elementSecondChil = itSecond.next();
                    //判断子元素中是否存在property
                    if (elementSecondChil.getName().equals("property")){
                        //由于是setter方法注入,没有构造方法
                        object = clazz.newInstance();
                        //获取到ref中的value值
                        String refValue = elementSecondChil.attribute("ref").getValue();
                        //根据ref的那么值从map中获取到依赖的new出来的对象
                        Object injetObject = map.get(refValue);
                        //获取到name的名称
                        String nameVlue = elementSecondChil.attribute("name").getValue();
                        //通过name的值,反射得到对象单个属性
                        Field field = clazz.getDeclaredField(nameVlue);
                        //设置类允许set,以为属性是私有的.
                        field.setAccessible(true);
                        //一个是属性的对象(UserDao),一个是当前对象(UuerDaoImpl)
                        field.set(object,injetObject);
                    }else {//证明有特殊构造方法
                        //获取到ref中的value值
                        String refValue = elementSecondChil.attribute("ref").getValue();
                        //根据ref的那么值从map中获取到依赖的new出来的对象
                        Object injetObject = map.get(refValue);
                        //获取到类型,注入对象
                        Class injetObjectClazz = injetObject.getClass();
                        //获取到构造方法,并进行注入,这里是拿到的类型,应该去拿到接口.
                        Constructor<?> constructor = clazz.getConstructor(injetObjectClazz.getInterfaces()[0]);
                        //通过够着方法new出来当前对象
                        object = constructor.newInstance(injetObject);
                    }

                }

//                //获取到这个类下面的所有的属性
//                Field[] fields = clazz.getDeclaredFields();
//                for (Field field : fields) {
//
//                }
                if (object == null) {
                    if (flag) {
                        if (attribute.getValue().equals("byType")) {
                            //判断是否有依赖
                            //拿到所有的依赖
                            Field[] fields = clazz.getDeclaredFields();
                            //循环所有的依赖
                            for (Field field : fields) {
                                //得到属性的类型,比如String aa,那么这里的field.getType() = String.class
                                Class injectObjectClazz = field.getType();
                                /**
                                 * 由于是byType,所以需要遍历map当中的所有对象
                                 * 判断对象的类型是不是和这个injectObjectClazz相同
                                 */
                                int count = 0;
                                Object injectObject = null;
                                for (String key : map.keySet()) {
                                    Class temp = map.get(key).getClass().getInterfaces()[0];
                                    //判断依赖类中的属性类型和xml中的类型是否相同.
                                    if (temp.getName().equals(injectObjectClazz.getName())) {
                                        injectObject = map.get(key);
                                        //记录找到一个,因为可能找到多个count
                                        count++;
                                    }
                                }

                                if (count > 1) {
                                    throw new LubanSpringException("需要一个对象,但是找到了两个对象");
                                } else {
                                    object = clazz.newInstance();
                                    field.setAccessible(true);
                                    field.set(object, injectObject);
                                }
                            }
                        }
                    }
                }
                if (object == null){//没有标签,没有依赖的时候
                    object = clazz.newInstance();
                }
                map.put(beanName,object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(map);
    }

    public Object getBean(String beanName){
        return map.get(beanName);
    }
}
