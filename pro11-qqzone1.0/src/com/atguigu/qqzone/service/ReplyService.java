package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.Reply;

import java.util.List;

public interface ReplyService {
    List<Reply> getReplyByTopicId(Integer id);
}
