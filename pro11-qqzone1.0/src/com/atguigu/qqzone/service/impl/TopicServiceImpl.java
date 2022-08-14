package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.TopicDao;
import com.atguigu.qqzone.pojo.Reply;
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
        topic.setReplyList(replyService.getReplyByTopicId(id));
        return topic;
    }

    @Override
    public void delTopic(Integer topicId) {
        /*
        先判断日志是否有关联回复及主人回复信息 若有则需先删除 否则日志无法删除掉
         */
        List<Reply> replyList = replyService.getReplyByTopicId(topicId);
        if (replyList != null){
            for (Reply reply:
                 replyList) {
                replyService.deleteReply(reply.getId());
            }
        }
        topicDao.deleteTopic(new Topic(topicId));
    }
}
