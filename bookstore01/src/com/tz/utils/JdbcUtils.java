package com.tz.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> threadLocalConns = new ThreadLocal<>();

    static {
        try {
            Properties properties = new Properties();
            // 读取 jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 从流中加载数据
            properties.load(inputStream);
            // 创建 数据库连接 池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
//            System.out.println(dataSource.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public static void main(String[] args) {
//
//    }

    /**
     * 获取数据库连接池中的连接
     *
     * @return 如果返回null, 说明获取连接失败<br />有值就是获取连接成功
     */
    public static Connection getConnection() {

//        Connection conn = null;
//
//        try {
//            conn = dataSource.getConnection();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return conn;
        Connection conn = threadLocalConns.get();
        try {
            if (conn == null) {
                conn = dataSource.getConnection();
                threadLocalConns.set(conn);
                conn.setAutoCommit(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * 关闭连接，放回数据库连接池
     *
     * @param conn
     */
//    public static void close(Connection conn) {
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    /**
     * 提交事务，并关闭释放连接
     */
    public static void commitAndClose() {
        Connection connection = threadLocalConns.get();
        try {
            if (connection != null) { // 如果不等于 null，说明 之前使用过连接，操作过数据库
                connection.commit(); // 提交 事务
                connection.close(); // 关闭连接，资源资源
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 一定要执行 remove 操作，否则就会出错。（因为 Tomcat 服务器底层使用了线程池技术）
        threadLocalConns.remove();
    }

    /**
     * 回滚事务，并关闭释放连接
     */
    public static void rollbackAndClose() throws SQLException {
        Connection connection = threadLocalConns.get();
        try {
            if (connection != null) { // 如果不等于 null，说明 之前使用过连接，操作过数据库
                connection.rollback();//回滚事务
                connection.close(); // 关闭连接，资源资源
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 一定要执行 remove 操作，否则就会出错。（因为 Tomcat 服务器底层使用了线程池技术）
        threadLocalConns.remove();
    }
}
