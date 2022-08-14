package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.HostReply;

public interface HostReplyDao {
    HostReply getHostReplyByReplyId(Integer id);

    Integer deleteHostReply(HostReply hostReply);
}
