package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.UserBasicDao;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.ArrayList;
import java.util.List;

public class UserBasicServiceImpl implements UserBasicService {
    private UserBasicDao userBasicDao = null;
    @Override
    public UserBasic login(String loginId, String pwd) {
        return userBasicDao.getUserBasicByUnameAndPass(loginId, pwd);
    }

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) {
        List<UserBasic> userBasicList = userBasicDao.getUserBasicID(userBasic);
        List<UserBasic> friendList = new ArrayList<>(userBasicList.size());
        for (UserBasic friend: userBasicList){
            UserBasic friendInfo = userBasicDao.getUserBasicById(friend.getId());
            friendList.add(friendInfo);
        }

        return friendList;
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        return userBasicDao.getUserBasicById(id);
    }


}
