package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;

import java.util.List;

public interface ReplyDao {
    //获取日志关联的所有回复信息
    List<Reply> getReplyByTopic(Topic topic);
    //新增回复
    Integer addReply(Reply reply);
    //删除回复
    Integer deleteReply(Reply reply);
}
