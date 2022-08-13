package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.ReplyDao;
import com.atguigu.qqzone.pojo.HostReply;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.service.HostReplyService;
import com.atguigu.qqzone.service.ReplyService;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {
    private ReplyDao replyDao;
    private HostReplyService hostReplyService;

    @Override
    public List<Reply> getReplyByTopicId(Integer id) {
        List<Reply> replyList = replyDao.getReplyByTopic(new Topic(id));

        for(int i =0; i < replyList.size(); i++){
            Reply reply = replyList.get(i);
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            reply.setHostReply(hostReply);
        }

        return replyList;
    }
}
