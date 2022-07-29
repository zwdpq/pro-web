package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicDao {
    //获取用户所有日志信息
    List<Topic> getTopicList(UserBasic userBasic);
    //新增日志
    Integer addTopic(Topic topic);
    //删除日志
    Integer deleteTopic(Topic topic);
}
