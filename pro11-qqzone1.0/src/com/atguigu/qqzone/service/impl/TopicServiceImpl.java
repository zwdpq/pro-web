package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.TopicDao;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.ReplyService;
import com.atguigu.qqzone.service.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {
    private TopicDao topicDao = null;
    private ReplyService replyService = null;
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        List<Topic> topicList = topicDao.getTopicList(userBasic);
        return topicList;
    }

    @Override
    public Topic getTopicById(Integer id) {
        Topic topic = topicDao.getTopicById(id);
        topic.setReplys(replyService.getReplyByTopicId(id));
        return topic;
    }
}
