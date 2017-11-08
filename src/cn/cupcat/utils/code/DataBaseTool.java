package cn.cupcat.utils.code;

import java.sql.*;
import java.util.HashMap;

/**
 *  访问数据库工具类，该类创建的目的是为了服务工具访问数据使用；不要应用于项目的其他地方
 *  @author zxy
 *  @since 2017年10月25日16:24:25
 *  @version 1.0
 * */
    public class DataBaseTool {


    public static Connection conn = null;

    static {
        conn = getConnection();
    }


    /**
     * 获取数据链接
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(Factory.driver);
            conn = DriverManager.getConnection(Factory.url, Factory.username, Factory.password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }





    public static boolean executeProc(String sSql, Object[] Params, Object[] OutParams, int[] OutParamTypes) {

        if (conn == null) {
            conn = getConnection();
        }
        CallableStatement cstmt = null;
        boolean bFlag = false;

        try {
            cstmt = conn.prepareCall(sSql);
            int i = 1;
            Object[] var11 = Params;
            int kk = Params.length;

            int len;
            for (len = 0; len < kk; ++len) {
                Object p = var11[len];
                cstmt.setObject(i, p);
                ++i;
            }

            len = OutParams.length;

            for (kk = 0; kk < len; ++kk) {
                cstmt.registerOutParameter(i, OutParamTypes[kk]);
                ++i;
            }

            cstmt.executeUpdate();
            int m = i;

            for (int j = 0; m < i; ++j) {
                OutParams[j] = cstmt.getObject(m);
                ++m;
            }

            bFlag = true;
        } catch (SQLException var21) {
            var21.printStackTrace();
        } finally {
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException var20) {
                    var20.printStackTrace();
                }
                cstmt = null;
            }


        }

        return bFlag;

    }

    public static HashMap<String, Object> getOne(String sSql, Object[] Params) {

        if (conn == null) {
            conn = getConnection();
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        HashMap result = null;

        try {
            ps = conn.prepareStatement(sSql);
            int i = 1;
            Object[] var11 = Params;
            int var10 = Params.length;

            int j;
            for (j = 0; j < var10; ++j) {
                Object p = var11[j];
                ps.setObject(i, p);
                ++i;
            }

            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                result = new HashMap();

                for (j = 1; j <= columnCount; ++j) {
                    result.put(rsmd.getColumnName(j), rs.getObject(j));
                }
            }
        } catch (SQLException var24) {
            var24.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException var23) {
                    var23.printStackTrace();
                }

                rs = null;
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException var22) {
                    var22.printStackTrace();
                }

                ps = null;
            }
        }

        return result;

    }

}
