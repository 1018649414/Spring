package com.luban.service;

import com.luban.dao.UserDao;
import com.luban.dao.UserDaoImpl;

public class UserServiceImpl implements UserService{

    UserDao dao;

    public void setDao(UserDao dao) {
        this.dao = dao;
    }
//
//    public UserServiceImpl(UserDao dao){
//        this.dao = dao;
//    }

    public void find() {
        dao.query();
        System.out.println("find");
    }
}
