package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.dao.ReplyDao;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;

import java.util.List;

public class ReplyDaoImpl extends BaseDAO<Reply> implements ReplyDao {
    @Override
    public List<Reply> getReplyByTopic(Topic topic) {
        return super.executeQuery("select * from t_reply where topic = ?", topic.getId());
    }

    @Override
    public Integer addReply(Reply reply) {
        return null;
    }

    @Override
    public Integer deleteReply(Reply reply) {
        return null;
    }
}
