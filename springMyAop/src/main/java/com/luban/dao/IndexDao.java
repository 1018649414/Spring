package com.luban.dao;

import com.luban.anno.Luban;
import org.springframework.stereotype.Repository;

@Repository("indexDao")
public class IndexDao implements Dao{
    public void query1(String ages){
        System.out.println("query1");
        System.out.println(ages);
    }


    public void query2(){
        System.out.println("query2");
    }

    @Luban
    public void query3(){
        System.out.println("query3");
    }
}
