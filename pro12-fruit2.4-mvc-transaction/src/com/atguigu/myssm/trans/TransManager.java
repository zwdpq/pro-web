package com.atguigu.myssm.trans;

import com.atguigu.myssm.basedao.ConnUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 事务管理器
 */
public class TransManager {
    public static void begingTrans() throws SQLException {
        ConnUtil.getConnection().setAutoCommit(false);
    }
    public static void commitTrans() throws SQLException {
        ConnUtil.getConnection().commit();
        ConnUtil.closeConnection();
    }
    public static void rollbackTrans() throws SQLException {
        ConnUtil.getConnection().rollback();
        ConnUtil.closeConnection();
    }
}
