package com.atguigu.myssm.basedao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connectino工具
 * 负责产生线程唯一的Connection
 */
public class ConnUtil {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    private static String DRIVER = "com.mysql.jdbc.Driver" ;
    private static String URL = "jdbc:mysql://localhost:3306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static String USER = "root";
    private static String PWD = "1924618914";

    private static Connection createConnection(){
        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Connection getConnection(){
        if (threadLocal.get() == null){
            threadLocal.set(createConnection());
        }
        return threadLocal.get();
    }
    public static void closeConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection == null){
        }
        connection.close();
        threadLocal.set(null); // 注意别忘了在关闭连接的时候 要把ThreadLocal中的Connection给清空
    }
}
