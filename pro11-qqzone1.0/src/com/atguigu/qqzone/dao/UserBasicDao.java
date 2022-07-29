package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

public interface UserBasicDao {
    //根据用户名与密码获取用户信息
    UserBasic getUserBasicByUnameAndPass(String loginId, String pwd);
    //获取好友ID信息
    List<UserBasic> getUserBasicID(UserBasic userBasic);
    //根据ID获取用户信息
    UserBasic getUserBasicById(Integer id);
}
