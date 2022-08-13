package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.HostReplyDao;
import com.atguigu.qqzone.pojo.HostReply;
import com.atguigu.qqzone.service.HostReplyService;

public class HostReplyServiceImpl implements HostReplyService {
    private HostReplyDao hostReplyDao;

    @Override
    public HostReply getHostReplyByReplyId(Integer id) {
        return hostReplyDao.getHostReplyByReplyId(id);
    }
}
