package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.dao.HostReplyDao;
import com.atguigu.qqzone.pojo.HostReply;

public class HostReplyDaoImpl extends BaseDAO<HostReply> implements HostReplyDao {
    @Override
    public HostReply getHostReplyByReplyId(Integer id) {
        return super.load("select * from t_host_reply where reply = ?", id);
    }
}
