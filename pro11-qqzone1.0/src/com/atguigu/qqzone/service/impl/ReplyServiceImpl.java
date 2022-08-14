package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.ReplyDao;
import com.atguigu.qqzone.pojo.HostReply;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.HostReplyService;
import com.atguigu.qqzone.service.ReplyService;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {
    private ReplyDao replyDao;
    private HostReplyService hostReplyService;
    private UserBasicService userBasicService;

    @Override
    public List<Reply> getReplyByTopicId(Integer id) {
        List<Reply> replyList = replyDao.getReplyByTopic(new Topic(id));

        for(int i =0; i < replyList.size(); i++){
            Reply reply = replyList.get(i);
            //1.将回复作者添加到回复信息中
            UserBasic author = userBasicService.getUserBasicById(reply.getAuthor().getId());
            reply.setAuthor(author);

            //2.将主人回复添加到回复信息中
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            reply.setHostReply(hostReply);
        }

        return replyList;
    }

    @Override
    public Integer addReply(Reply reply) {
        return replyDao.addReply(reply);
    }

    @Override
    public void deleteReply(Integer id) {
        /*
        先判断是否有主人回复，如果有则删除之，否则关联有主人回复的回复信息删除不掉
         */
        HostReply hostReply = hostReplyService.getHostReplyByReplyId(id);
        if (hostReply!= null){
            hostReplyService.delHostReply(hostReply);
        }
        replyDao.deleteReply(new Reply(id));
    }
}
