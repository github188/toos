package cn.cupcat.dao;

import cn.cupcat.utils.code.Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by xy on 2017/11/8.
 */
public class DAO {

    public static String database = "sqlservler";
    public static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String url = "jdbc:sqlserver://192.168.1.112:1433 ;DatabaseName=WXVPlatform;SelectMethod=cursor";
    public static String username = "WXVPlatform";
    public static String password = "WXVPlatform";

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }








    /**
     *  测试使用
     * */
    public static void main(String[] args) {
        Connection connection = getConnection();
        System.out.println(connection);
    }

}
