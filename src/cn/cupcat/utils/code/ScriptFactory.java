package cn.cupcat.utils.code;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * 该类主要功能是将ModuleFactory生成的方法自动注册到系统中，并且默认赋给管理员新增功能权限
 *
 * @author zxy
 * @version 1.0
 * @since 2017年10月25日12:40:42
 */
public class ScriptFactory extends Factory {

    public static String D = "";
    public static int logFlag = 0; //默认功能不记录日志
    public static String logInfo = "";
    public static int funcLevel = 3;//默认功能级别为3

    /**
     * 获取功能名称，用于添加到数据库功能
     */
    public static HashMap<String, String> getFunctionDescription() {
        HashMap<String, String> hashMap = new HashMap<String, String>(10);
        hashMap.put(init.trim(), init_function_description);
        hashMap.put(getListByPage.trim(), getListByPage_function_description);
        hashMap.put(showAdd.trim(), showAdd_function_description);
        hashMap.put(add.trim(), add_function_description);
        hashMap.put(showEdit.trim(), showEdit_function_description);
        hashMap.put(edit.trim(), edit_function_description);
        hashMap.put(delete.trim(), delete_function_description);
        hashMap.put(batchDelete.trim(), batchDelete_function_description);
        hashMap.put(getDetail.trim(), getDetail_function_description);
        if (isCreateUploadMethod.equalsIgnoreCase("true")) {
            hashMap.put(getExcelImport.trim(), getExcelImport_function_description);
        }
        return hashMap;
    }


    /**
     * 添加功能组
     *
     * @param GroupName 组名称
     * @param parentId  上级ID
     * @return int         添加记录的ID
     */
    public static int addFunctionGroup(String GroupName, int parentId) {
        String sql = "{call dbo.InsertNewFuncTreeNode(?,?,?,?,?, ?,?,?,?,?, ?, ?,?)}";

        Object[] param = {GroupName, "", parentId, 1, "",
                "", "", "", 0, "", 0};

        Object[] OutParams = new Object[2];
        int[] types = {java.sql.Types.INTEGER, java.sql.Types.INTEGER};

        boolean bFlag = DataBaseTool.executeProc(sql, param, OutParams, types);
        if (!bFlag) {
            return -1;
        }
        //查询出刚才插入数据的ID
        sql = "SELECT TOP 1 ID FROM MFU_Func func WHERE pid = ?  AND FuncName = ?  ORDER BY ID DESC ";
        HashMap<String, Object> one = DataBaseTool.getOne(sql, new Object[]{parentId, GroupName});
        if (one == null) {
            return -2;
        }

        return (Integer) one.get("ID");
    }

    /**
     * 添加功能
     */
    public static boolean addFunction(String Name, int parentId, String M, String C, String A, String D, int logFlag, String logInfo, String Alias, int funcLevel) {
        String sql = "{call dbo.InsertNewFuncTreeNode(?,?,?,?,?, ?,?,?,?,?, ?, ?,?)}";

        Object[] param = {Name, Alias, parentId, 0, M, C, A, D, logFlag, logInfo, funcLevel};

        Object[] OutParams = new Object[2];
        int[] types = {java.sql.Types.INTEGER, java.sql.Types.INTEGER};
        boolean bFlag = DataBaseTool.executeProc(sql, param, OutParams, types);

        return bFlag;
    }

    /**
     * 将生成的方法添加到后台
     */
    public static boolean execute() throws SQLException {
        HashMap<String, String> hashMap = getFunctionDescription();
        try {

            if (DataBaseTool.conn == null){
                DataBaseTool.conn = DataBaseTool.getConnection();
            }
            DataBaseTool.conn.setAutoCommit(false);
            //判断有没有配置功能id
            if (FuncId == null || FuncId.trim().length() == 0) {
                throw new IllegalArgumentException("未配置FuncId，请配置......");
            }
            int funcId = Integer.parseInt(FuncId.trim());
            //判断一级功能树名称
            if (oneLevelFuncionName == null || oneLevelFuncionName.trim().length() == 0) {
                throw new IllegalArgumentException("未配置oneLevelFuncionName，请配置......");
            }
            String sql = "SELECT TOP 1 ID FROM MFU_Func func WHERE pid = ?  AND FuncName = ?  ORDER BY ID DESC ";
            HashMap<String, Object> one = DataBaseTool.getOne(sql, new Object[]{funcId, oneLevelFuncionName});
            int oneLevelFuncionID = 0;
            if (one == null) { //数据库不存在，创建
                oneLevelFuncionID = addFunctionGroup(oneLevelFuncionName, funcId);
                if (oneLevelFuncionID < 0) {
                    throw new Exception("创建一级功能树错误！");
                }
            } else {//存在，取ID
                oneLevelFuncionID = (Integer) one.get("ID");
            }
            //判断二级功能树名称
            if (twoLevelFuncionName == null || twoLevelFuncionName.trim().length() == 0) { //没有配置二级功能树名称，将功能添加到一级功能树下面
                boolean b = true;
                //循环将功能添加到数据库中
                for (String method : hashMap.keySet()) {
                    String funcName = hashMap.get(method);
                    b = b && addFunction(funcName, oneLevelFuncionID, packageName, controllerName, method, D, logFlag, logInfo, funcName, funcLevel);
                }
                if (!b) {
                    throw new Exception("添加功能错误！");
                }
            } else {//有配置，查询是否存在，若存在则将功能添加到下面，若没有存在则创建、然后将功能添加到下面
                one = DataBaseTool.getOne(sql, new Object[]{oneLevelFuncionID, twoLevelFuncionName});
                int twoLevelFuncionID = 0;
                if (one == null) { //数据库不存在，创建
                    twoLevelFuncionID = addFunctionGroup(twoLevelFuncionName, oneLevelFuncionID);
                    if (twoLevelFuncionID < 0) {
                        throw new Exception("创建二级功能树错误！");
                    }
                } else {//存在，取ID
                    twoLevelFuncionID = (Integer) one.get("ID");
                }

                boolean b = true;
                //循环将功能添加到数据库中
                for (String method : hashMap.keySet()) {
                    String funcName = hashMap.get(method);
                    b = b && addFunction(funcName, twoLevelFuncionID, packageName, controllerName, method, D, logFlag, logInfo, funcName, funcLevel);
                }
                if (!b) {
                    throw new Exception("添加功能错误！");
                }
            }
            DataBaseTool.conn.commit();
        } catch (NumberFormatException numberFormatException) {
            System.out.println("FuncId 配置项必须为数字,请登录后台查看对应的FuncId后，再配置......");
            numberFormatException.printStackTrace();
            DataBaseTool.conn.rollback();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            DataBaseTool.conn.rollback();
            return false;
        }finally {
            DataBaseTool.conn.setAutoCommit(true);
            DataBaseTool.conn.close();
        }
        return true;
    }




    @Override
    public String getFactoryStr() throws IOException, SQLException {

        return null;
    }

    @Override
    public boolean createFile() throws IOException {

        return false;
    }
}
