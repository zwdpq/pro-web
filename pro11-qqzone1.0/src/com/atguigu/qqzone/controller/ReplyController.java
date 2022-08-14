package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.util.Date;

public class ReplyController {
    private ReplyService replyService;

    public String addReply(Integer topicId, String content, HttpSession session){
        Reply reply = new Reply(content, new Date(), (UserBasic) session.getAttribute("userBasic"), new Topic(topicId));
        replyService.addReply(reply);

        return "redirect:topic.do?operate=topicDetail&id="+topicId;
    }

    public String delReply(Integer replyId, Integer topicId){
        replyService.deleteReply(replyId);

        return "redirect:topic.do?operate=topicDetail&id="+topicId;
    }
}
