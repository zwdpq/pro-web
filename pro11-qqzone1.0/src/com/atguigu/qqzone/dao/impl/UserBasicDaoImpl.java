package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.dao.UserBasicDao;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

public class UserBasicDaoImpl extends BaseDAO<UserBasic> implements UserBasicDao {
    @Override
    public UserBasic getUserBasicByUnameAndPass(String loginId, String pwd) {
        return super.load("select * from t_user_basic where loginId = ? and pwd = ?", loginId,pwd);
    }

    @Override
    public List<UserBasic> getUserBasicID(UserBasic userBasic) {
        return super.executeQuery("select fid as id from t_friend where uid = ?",userBasic.getId());
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        return super.load("select * from t_user_basic where id = ?", id);
    }
}
