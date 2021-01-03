package com.luban.dao;

public class LubanDaoImpl implements LubanDao{
    public void  query(){
        System.out.println("假装查询数据库");
    }

    public String proxy() {
        return "proxy";
    }

    @Override
    public String proxy1(String s) {
        return s;
    }

}
