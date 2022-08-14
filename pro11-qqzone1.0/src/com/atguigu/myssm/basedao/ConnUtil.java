package com.atguigu.myssm.basedao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnUtil {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    /*
    方式一
     */
    /*
 public static String DRIVER;
    public static String URL;
    public static String USER;
    public static String PWD;

    static {
        InputStream is = ConnUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
            DRIVER = properties.getProperty("jdbc.driver");
            URL = properties.getProperty("jdbc.url");
            USER = properties.getProperty("jdbc.user");
            PWD = properties.getProperty("jdbc.pwd");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Connection createConn() {
        try {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName(DRIVER);
            dataSource.setUrl(URL);
            dataSource.setUsername(USER);
            dataSource.setPassword(PWD);

            dataSource.setMaxWait(5000);
            dataSource.setMinIdle(3);
            dataSource.setMaxActive(10);

            return dataSource.getConnection();
            //1.加载驱动
            //Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            // return DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
     */
    /*
    方式二  通过DruidDateSourceFactory 去管理Connection对象
     */
    static Properties properties;
    static {
        InputStream is = ConnUtil.class.getClassLoader().getResourceAsStream("jdbc_druid.properties");
        properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Connection createConn() {
        try {
            DataSource druidDateSource = DruidDataSourceFactory.createDataSource(properties);
            return druidDateSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Connection getConn() {
        Connection conn = threadLocal.get();
        if (conn == null) {
            conn = createConn();
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }

    public static void closeConn() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn == null) {
            return;
        }
        if (!conn.isClosed()) {
            conn.close();
            //threadLocal.set(null);
            threadLocal.remove();
        }
    }
}
