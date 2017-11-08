package cn.cupcat.utils.dao;

import org.apache.commons.validator.GenericValidator;
import system.lib.DataBase;
import system.lib.MvcFunction;
import system.lib.PageQueryInfo;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xy on 2017/10/11.
 * 实现访问数据库的通用功能
 */
public abstract class BasicDAO<T> implements DAO<T> {

    public String tableName = ""; //表名或者视图名称
    public DataBase dataBase = null;//数据库对象

    public BasicDAO(String tableName, DataBase dataBase) {
        this.tableName = tableName;
        this.dataBase = dataBase;
    }


    /**
     * 添加
     *
     * @param paramMap 说明： 其中Map的key应该存放表的字段名；value应该存放key字段名对应的value值
     */
    @Override
    public boolean add(Map<String, Object> paramMap) {
        int result = add(paramMap, true);

        return result > 0 ? true : false;
    }

    @Override
    public int add(Map<String, Object> paramMap, boolean flag) {
        if (paramMap == null) {
            return -1;
        }
        String columnNameSQL = ""; //字段名部分SQL
        String columnValueSQL = "";//字段名对应的值部分SQL
        Object[] columnValueArray = new Object[paramMap.size()];
        int i = 0;
        //遍历Map获取key 和 value，拼接SQL
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            columnNameSQL += entry.getKey() + ",";
            columnValueSQL += " ? ,";
            columnValueArray[i] = entry.getValue();
            i++;
        }
        //去除最后的逗号
        columnNameSQL = columnNameSQL.substring(0, columnNameSQL.length() - 1);
        columnValueSQL = columnValueSQL.substring(0, columnValueSQL.length() - 1);

        String sql = "INSERT INTO " + tableName + " ( " + columnNameSQL + " ) VALUES (" + columnValueSQL + ") ";
        if (flag){ //添加不需要返回下一个主键的时候使用；String类型的主键，使用executeAndGetLastKey方法将会报错
            return dataBase.execute(sql, columnValueArray) ? 1 : -1;
        }
        int result = ((BigDecimal) dataBase.executeAndGetLastKey(sql, columnValueArray)).intValue();
        return result;

    }

    /**
     * 修改
     */
    @Override
    public boolean edit(Map<String, Object> paramMap, T id) {
        return edit(paramMap, id, null);
    }

    @Override
    public boolean edit(Map<String, Object> paramMap, T id, String PKColumnName) {

        if (PKColumnName == null || PKColumnName.trim().equals("")) {
            PKColumnName = "ID";
        }

        if (paramMap == null) {
            throw new IllegalArgumentException("参数：paramMap不能为null ！ ");
        }
        String tempSQL = ""; //保存临时拼接SQL
        Object[] columnValueArray = new Object[paramMap.size() + 1];
        int i = 0;
        //遍历Map获取key 和 value，拼接SQL
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            tempSQL += entry.getKey() + " = ? ,";
            columnValueArray[i] = entry.getValue();
            i++;
        }
        //去除最后的逗号
        tempSQL = tempSQL.substring(0, tempSQL.length() - 1);
        columnValueArray[i] = id;
        String sql = "UPDATE " + tableName + " SET " + tempSQL + " WHERE " + PKColumnName + " = ? ";
        return dataBase.execute(sql, columnValueArray);
    }


    /**
     * 单个删除
     *
     * @param id
     */

    @Override
    public boolean delete(T id) {
        String sql = "DELETE FROM " + tableName + " WHERE ID = ? ";
        int flag = dataBase.executeByAffect(sql, new Object[]{id});
        return flag < 0 ? false : true;
    }

    @Override
    public boolean delete(T id, String PKColumnName) {
        String sql = "DELETE FROM " + tableName + " WHERE " + PKColumnName + " = ? ";
        int flag = dataBase.executeByAffect(sql, new Object[]{id});
        return flag < 0 ? false : true;

    }

    /**
     * 批量删除
     *
     * @param idArray
     */
    @Override
    public boolean batchDelete(String[] idArray) {
        String sql = "DELETE FROM  " + tableName + " WHERE ID = ? ";
        return commonBatchDelete(sql, idArray);
    }

    @Override
    public boolean batchDelete(String ids) {
        return commonBatchDelete(ids, "");
    }

    @Override
    public boolean batchDelete(String[] idArray, String PKColumnName) {
        String sql = "DELETE FROM  " + tableName + " WHERE " + PKColumnName + " = ? ";
        return commonBatchDelete(sql, idArray);
    }

    @Override
    public boolean batchDelete(String ids, String PKColumnName) {
        return commonBatchDelete(ids, PKColumnName);
    }

    /***
     *  传入id数组，公用删除方法
     * */
    private boolean commonBatchDelete(String sql, String[] idArray) {
        dataBase.BeginTransaction(); //开启事务
        try {
            dataBase.prepareBatch(sql);
            int i = 0;
            int length = idArray.length;
            while (i < length) {
                dataBase.setBatchParam(1, Integer.parseInt(idArray[i]));
                dataBase.addBatchParam();
                i++;
            }
            dataBase.batchExecute();
            dataBase.EndTransaction(); //结束事务
        } catch (SQLException e) {
            e.printStackTrace();
            dataBase.FailRollBack();//回滚
            return false;
        }
        return true;
    }

    /**
     * 传入id字符串，公用删除方法
     */
    private boolean commonBatchDelete(String ids, String PKColumnName) {

        if (ids.substring(ids.length() - 1) == ",") {//如果参数结尾是逗号(,);去除
            ids = ids.substring(0, ids.length() - 1);
        }
        String sql = "DELETE FROM " + tableName + " WHERE ";
        if (PKColumnName == null || PKColumnName.trim().equals("")) { //传 ""，默认为ID
            sql += " ID IN ( " + ids + " ) ";
        } else {
            sql += PKColumnName + " IN ( " + ids + " ) ";
        }

        return dataBase.execute(sql, new Object[]{});
    }

    /**
     * 获得单条
     *
     * @param id
     */
    @Override
    public HashMap<String, Object> getOne(T id) {
        String sql = "SELECT * FROM " + tableName + " WHERE ID = ? ";
        return dataBase.getOne(sql, new Object[]{id});
    }

    @Override
    public HashMap<String, Object> getOne(T id, String PKColumnName) {
        String sql = "SELECT * FROM " + tableName + " WHERE " + PKColumnName + " = ? ";
        return dataBase.getOne(sql, new Object[]{id});
    }


    /**
     * 获取全部数据
     */
    @Override
    public ArrayList<HashMap<String, Object>> getAll() {
        String sql = "SELECT * FROM " + tableName;
        return dataBase.getList(sql, new Object[]{});
    }

    /**
     * 获取指定列的全部数据
     * 说明：若传入参数columns为null或者长度为0，则查询全部
     */
    @Override
    public ArrayList<HashMap<String, Object>> getAll(String[] columns) {
        if (columns == null || columns.length == 0) { //如果传入null或者长度为0，则查询全部字段
            return getAll();
        }
        String tempSQL = "";//用于存放动态拼接的列名
        for (int i = 0; i < columns.length; i++) {
            if (i == columns.length - 1) { //最后一个,不需要最后添加逗号
                tempSQL += columns[i];
            } else {
                tempSQL += columns[i] + ",";
            }
        }
        String sql = "SELECT " + tempSQL + " FROM " + tableName;
        return dataBase.getList(sql, new Object[]{});
    }

    @Override
    public ArrayList<HashMap<String, Object>> getAll(String[] columns, Map<String, String> paramMap, String customSQL, String SQLConnector) {
        ArrayList<HashMap<String, Object>> result = null;
        SQLConnector = SQLConnector == null ? "AND" : SQLConnector;//传入null,替换为AND

        String columnSQL = "";
        if (columns == null || columns.length == 0) { //为空，长度为0，则查询全部
            columnSQL += " * ";
        } else { //查询指定列
            for (int i = 0; i < columns.length; i++) {
                if (i == columns.length - 1) { //最后一个,不需要最后添加逗号
                    columnSQL += columns[i];
                } else {
                    columnSQL += columns[i] + ",";
                }
            }
        }

        String filterSQL = getFilterSQL(paramMap, customSQL, SQLConnector);
        String sql = " SELECT ";
        sql += (columnSQL + " FROM " + tableName + " WHERE 1 =1 " + filterSQL);
        result = dataBase.getList(sql, new Object[]{});
        return result;
    }

    /**
     * 获取where子句后的过滤条件
     */
    private String getFilterSQL(Map<String, String> paramMap, String customSQL, String SQLConnector) {

        String tempSQL = "";//临时变量保存拼装sql
        boolean isFirst = true;
        StringBuilder queryCondition = new StringBuilder(512);
        if (paramMap != null) { //遍历传来的参数，配装SQL
            String key = "";
            Object value = null;
            for (Map.Entry<String, String> map : paramMap.entrySet()) {
                key = map.getKey();//列名
                value = map.getValue();//参数值
                if (value != null && value instanceof String) {
                    String sValue = (String) value;
                    if (!GenericValidator.isBlankOrNull(sValue)) { //参数值不为空或者null时，拼装SQL
                        if (isFirst) { //第一次前面添加 AND
                            tempSQL = "AND ( " + key + " LIKE '%" + MvcFunction.formatSqlString(sValue).trim() + "%' ";
                            isFirst = false;
                        } else {
                            tempSQL = " " + SQLConnector + " " + key + " LIKE '%" + MvcFunction.formatSqlString(sValue).trim() + "%' ";
                        }
                        queryCondition.append(tempSQL);
                    }
                }
            }
        }
        if (customSQL != null && customSQL.trim().length() != 0) {//若传入customSQL，添加上到过滤条件
            queryCondition.append(customSQL);
        }
        if (!isFirst) { //补齐右括号
            queryCondition.append(" ) ");
        }
        return queryCondition.toString();
    }

    @Override
    public ArrayList<HashMap<String, Object>> getTopN(Map<String, Object> paramMap, Integer n) {
        return getTopN(null, paramMap, n);
    }

    @Override
    public ArrayList<HashMap<String, Object>> getTopN(String[] columns, Map<String, Object> paramMap, Integer n) {
        String sql = "";
        if (columns == null || columns.length == 0) { //若columns为null或者长度为0，则查询全部列
            sql = "SELECT TOP " + n + " * FROM " + tableName + "WHERE 1 = 1 ";
        } else {
            String tempSQL = "";
            for (int i = 0; i < columns.length; i++) {
                if (i == columns.length - 1) {//最后一个
                    tempSQL += columns[i];
                } else {
                    tempSQL += columns[i] + ",";
                }
            }
            sql = "SELECT TOP " + n + tempSQL + "  FROM " + tableName + "WHERE 1 = 1 ";
        }

        StringBuilder queryCondition = new StringBuilder();
        String tempSQL = "";
        if (paramMap != null) { //遍历传来的参数，配装SQL
            String key = "";
            Object value = null;
            for (Map.Entry<String, Object> map : paramMap.entrySet()) {
                key = map.getKey();//列名
                value = map.getValue();//参数值
                if (value != null && value instanceof String) {
                    String sValue = (String) value;
                    if (!GenericValidator.isBlankOrNull(sValue)) { //参数值不为空或者null时，拼装SQL
                        tempSQL = " AND " + key + " LIKE '%" + MvcFunction.formatSqlString(sValue) + "%' ";
                        queryCondition.append(tempSQL);
                    }
                }
            }
        }
        sql += queryCondition.toString();
        return dataBase.getList(sql, new Object[]{});
    }

    /**
     * 获取查询数量
     *
     * @param columnArray 数据库表列的数组
     * @param valueArray  前台传来值的数组
     * @return String     符合条件的数量
     */
    @Override
    public String getCount(String[] columnArray, String[] valueArray) {
        String count = "0";//函数返回
        String sql = "SELECT COUNT(*) AS NUM FROM " + tableName;
        int columnArrayLength = 0;
        int valueArrayLength = 0;
        //当其中一个参数为null 或者 长度为0时，查询全部
        if (columnArray == null || valueArray == null || (columnArrayLength = columnArray.length) == 0 || (valueArrayLength = valueArray.length) == 0) {

        } else if (columnArrayLength != columnArrayLength) {
            throw new IllegalArgumentException("error：args columnArray's length must be equals to valueArray's length,but now columnArray's length = " + columnArrayLength + ",valueArray's length =" + valueArrayLength);
        } else {
            sql += " WHERE 1 = 1 ";
            for (int i = 0; i < columnArrayLength; i++) {
                sql += " AND " + columnArray[i] + " =  ? ";
            }
        }
        HashMap<String, Object> one = dataBase.getOne(sql, valueArray);
        if (one != null) {
            count = (String) one.get("NUM");
        }
        return count;
    }

    /**
     * 分页查询通用方法
     *
     * @param orderField      排序字段
     * @param orderDirection  排序规则
     * @param pageNum         每页显示条数
     * @param pageSize        页码
     * @param pageQueryInfo   分页对象
     * @param paramMap        提供模糊查询的Map;key为列名，value为该列所对应的值
     * @param tableOrViewName 表或者视图的名称
     * @param customSQL       自定义sql，若用使用sql中需要等号（=）匹配，则应该使用自定义SQL;注意使用自定SQL应该使用 AND 开头
     * @param SQLConnector    指定过滤条件连接符；例如 AND OR 等,若传入null，默认为AND
     * @param dataBase        数据库执行对象
     * @return ArrayList<HashMap<String,Object>>
     */
    public ArrayList<HashMap<String, Object>> getListByPage(String orderField, String orderDirection, Integer pageNum, Integer pageSize, PageQueryInfo pageQueryInfo,
                                                            Map<String, String> paramMap, String tableOrViewName, String customSQL, String SQLConnector, DataBase dataBase) {
        ArrayList<HashMap<String, Object>> result = null;//返回查询结果
        int iOrder = orderDirection.equalsIgnoreCase("asc") ? 0 : 1;//0 -- 升序； 1 -- 倒叙
        SQLConnector = SQLConnector == null ? "AND" : SQLConnector;
        //获取where子句的参数
        String filterSQL = getFilterSQL(paramMap, customSQL, SQLConnector);

        String sql = "{call dbo.PageQuery('" + tableOrViewName + "','*',?,?,?,?,?,'ID',0,?,?)}";
        Object[] param = {pageSize, pageNum, orderField, iOrder, filterSQL};
        Object[] OutParams = new Object[2];
        int[] types = {java.sql.Types.INTEGER, java.sql.Types.INTEGER};
        result = dataBase.getListByProc(sql, param, OutParams, types);
        pageQueryInfo.pagesCount = (Integer) OutParams[0];
        pageQueryInfo.recordCount = (Integer) OutParams[1];

        return result;

    }

    @Override
    public boolean markDelete(T id, String statusColumnName, String statusValue) {
       Map<String,Object> paramMap = new HashMap<String,Object>();
       paramMap.put(statusColumnName,statusValue);
        return edit(paramMap,id);
    }

    @Override
    public boolean markBatchDelete(String ids, String statusColumnName, String statusValue) {
        if (ids.substring(ids.length() - 1) == ",") {//如果参数结尾是逗号(,);去除
            ids = ids.substring(0, ids.length() - 1);
        }
        String params = "";
        String[] strings = ids.split(",");
        for (int i = 0 ; i < strings.length; i++ ){ //给每一项都添加上单引号
            if (i == strings.length-1){
                params += "'"+strings[i]+"'";
            }else{
                params += "'"+strings[i]+"'"+",";
            }
        }

        String sql = "update "+tableName+"  set "+statusColumnName + " = "+ MvcFunction.formatSqlString(statusValue) + " where ID in ( "+ params+")";
        return dataBase.execute(sql, new Object[]{});



    }



    @Override
    public boolean addBatch(ArrayList<HashMap<String, Object>> list) {
        Boolean flag = true;
        if (list.size() == 0) {
            return false;
        }
        if (list.get(0) == null) {
            return false;
        }

        String columnNameSQL = ""; // 字段名部分SQL
        String columnValueSQL = "";// 字段名对应的值部分SQL


        int i = 0;
        // 遍历Map获取key 和 value，拼接SQL
        for (Map.Entry<String, Object> entry : list.get(0).entrySet()) {
            columnNameSQL += entry.getKey() + ",";
            columnValueSQL += " ? ,";
            i++;
        }
        // 去除最后的逗号
        columnNameSQL = columnNameSQL.substring(0, columnNameSQL.length() - 1);
        columnValueSQL = columnValueSQL.substring(0,
                columnValueSQL.length() - 1);

        String sql = "INSERT INTO " + tableName + " ( " + columnNameSQL
                + " ) VALUES (" + columnValueSQL + ") ";


        int j = 0;
        int length = 0;
        dataBase.BeginTransaction();
        try {
            dataBase.prepareBatch(sql);
            for (int h = 0; h <= list.size() - 1; h++) {
                length = list.get(h).size();
                Object[] columnValueArray = new Object[length];
                int n = 0;
                for (Map.Entry<String, Object> entry : list.get(h).entrySet()) {
                    columnValueArray[n] = entry.getValue();
                    n++;
                }
                for (int m = 0; m < columnValueArray.length; m++) {
                    dataBase.setBatchParam(m + 1, columnValueArray[m]);
                }
                dataBase.addBatchParam();
            }

            dataBase.batchExecute();
        } catch (SQLException e) {
            dataBase.FailRollBack();
            flag = false;
            e.printStackTrace();

        } finally {
            dataBase.EndTransaction();

        }
        return flag;
    }
}
