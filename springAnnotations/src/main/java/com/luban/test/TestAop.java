package com.luban.test;

import com.luban.entity.CityEntity;
import com.luban.unit.CommUtil;

public class TestAop {
    public static void main(String[] args) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setId("1");
        cityEntity.setName("test");
        String s = CommUtil.buildQuerySqlForEntity(cityEntity);
        //select * from city where id=1 and name = 'test';
        System.out.println(s);
    }
}
