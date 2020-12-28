package com.luban.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
//@Scope("singleton")//默认就是singleton,拿到的hashCode永远是一个，也就是单例模式
@Scope("prototype")//修改成prototype，HashCode就不是相同的了。
public class IndexService {

//    如果使用@Autowired默认使用的byType，如果byType找不到那么就会根据byName来注入。
//    @Autowired
//    如果使用的@Resource默认使用的byName，根据属性名称来注入的，
//    不再是xml是根据set后面的英文名称来的了
    @Resource
    private IndexDao dao;//这里使用的是首字母小写的。

//    public IndexService(IndexDao dao){
//        this.dao = dao;
//    }

//    private IndexDao dao;

    //如果使用byName，那么就和set后面的英文有关系，把set去掉，把第一个英文字母变成小写
//    然后进行匹配xml中bean的id，然后进行注入。和上面的属性名称没有一点关系。
    //如果是setDao那么打印的就是impl0，如果是setLuban那么打印的就是impl1
    public void setLuban(IndexDao dao) {
        this.dao = dao;
    }


    public void service(){
        dao.test();
    }
}
