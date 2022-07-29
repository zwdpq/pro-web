package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.TopicDao;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {
    private TopicDao topicDao = null;
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        List<Topic> topicList = topicDao.getTopicList(userBasic);
        return topicList;
    }
}
