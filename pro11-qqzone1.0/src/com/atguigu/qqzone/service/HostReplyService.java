package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.HostReply;

public interface HostReplyService {
    HostReply getHostReplyByReplyId(Integer id);

    Integer delHostReply(HostReply hostReply);
}
