package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.dao.TopicDao;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

public class TopicDaoImpl extends BaseDAO<Topic> implements TopicDao {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return super.executeQuery("select * from t_topic where author = ?", userBasic.getId());
    }

    @Override
    public Integer addTopic(Topic topic) {
        return null;
    }

    @Override
    public Integer deleteTopic(Topic topic) {
        return null;
    }

    @Override
    public Topic getTopicById(Integer id) {
        return super.load("select * from t_topic where id = ?", id);
    }
}
