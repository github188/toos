package cn.cupcat.utils.code;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

/**
 * 该类是其他类的父类，保存着一些公用的配置
 * @author zxy
 * @version 1.1
 * */
public abstract class Factory {

    /**
     * 表名 ，控制器类名，model名配置  开始
     */

    public static String controllerName = "";//modules控制器类名称
    public static String modelName = "";//model类名
    public static String tableName = "";//数据库表名
    public static String projectPath = "";
    public static String packageName = "";//包名
    public static String itemName = "";//前台页面显示名称
    public static String appClient = "";//端名称
    public static String isCreateUploadMethod = "";//是否创建上传文件


    public static String templateDirPath = "";//生成logic文件位置配置
    //生成modules文件位置配置
    public static String srcDirPath = "";//项目硬盘的src所在目录
    public static String logic_skip_page_relative_file_path = "";



    /*生成方法名*/
    /**
     * 数据库链接配置 开始
     */

    public static String database = "";
    public static String driver = "";
    public static String url = "";
    public static String username = "";
    public static String password = "";
    /**
     * 数据库连接配置 结束
     */

    public static String pk_colunm_name = "ID"; //表主键列名
    public static String pk_column_class_name = " Integer "; //表主键对应的java类型
    public static String sql = "";
    public static String modulesName = "modules";//模块名
    public static String model = "model";
    /**
     * 表名 ，控制器类名，model名配置  结束
     */

    /**
     * 生成数据库脚本配置开始
     */

    public static String FuncId = ""; //功能id
    public static String oneLevelFuncionName = ""; //生成一级功能树名称
    public static String twoLevelFuncionName = "";//生成二级功能书名称
    //对应module方法后台添加的功能名称
    public static String init_function_description = "进入列表界面";
    public static String getListByPage_function_description = "获取列表数据";
    public static String showAdd_function_description = "进入添加界面";
    public static String add_function_description = "添加";
    public static String showEdit_function_description = "进入修改界面";
    public static String edit_function_description = "修改";
    public static String delete_function_description = "删除";
    public static String batchDelete_function_description = "批量删除";
    public static String getDetail_function_description = "获取详情";
    public static String getExcelImport_function_description = "导入";

    /**
     * 生成数据库脚本配置结束
     */
    //方法名称
    public static final String init = " init ";
    public static final String getListByPage = " getListByPage ";
    public static final String showAdd = " showAdd ";
    public static final String add = " add ";
    public static final String showEdit = " showEdit ";
    public static final String edit = " edit ";
    public static final String delete = " delete ";
    public static final String batchDelete = " batchDelete ";
    public static final String getDetail = " getDetail ";
    public static final String getExcelImport = " getExcelImport ";


    //错误码
    public static final String error_one = " 1 "; //分页报错
    public static final String error_id = "2";
    public static final String dataBase_error = " 10 ";

    //方法存放错误码
    public static HashMap<Integer, String> errorCodeMap = new HashMap<Integer, String>();
    public static HashMap<String, HashMap<Integer, String>> errorCodeMaps = new HashMap<String, HashMap<Integer, String>>();
    public static Map<String,String>  columnNameToRemarkMap = new HashMap<String,String>();

    //存放表列中的元数据
    public static List<HashMap<String, Object>> columnDataList =  null;



    //基本操作符号
    public static final String eq = " = ";
    public static final String dbEq = " == ";
    public static final String notEq = " != ";
    public static final String sNull = " null ";
    public static final String sNew = " new ";
    //括号
    public static final String smallBrackets = " () ";
    public static final String leftSmallBracket = " ( ";
    public static final String rightSmallBracked = " ) ";
    public static final String bigBrackets = " {} ";
    public static final String leftBigBracket = " { ";
    public static final String rightBigBracket = "} ";
    public static final String middleBrackets = "[]";
    public static final String leftMiddleBracket = "[";
    public static final String rightMiddleBracket = "]";

    //转义符
    public static final String newLine = " \r\n ";
    public static final String tab = " \t ";

    //标点
    public static final String comma = " , ";
    public static final String semicolon = " ; ";
    public static final String point = ".";


    //数据库对象
    public static final String dataBase = " DataBase dataBase ";
    public static final String dataBaseFieldName = " dataBase ";


    static { //静态初始化
        try {
            initConfigration();
            columnDataList = getColumnData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    /**
     * 获取表字段到备注的map； 说明：Map的key为字段名，value为该字段的备注
     */
    public static List<HashMap<String, Object>> getColumnData() throws SQLException {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>(20);
        Map<String, String> columnNameToRemarkMap = new HashMap<String, String>();

        String sql = "";
        String sql_server = "select  b.name as column_name, cast([value] as varchar(500)) [remarks] \n" +
                "from sys.tables a left join sys.columns b on a.object_id=b.object_id  \n" +
                "    left join sys.extended_properties c on a.object_id=c.major_id  \n" +
                "    where a.name='" + tableName + "' and c.minor_id<>0 and b.column_id=c.minor_id  \n" +
                "    and a.schema_id=(  \n" +
                "        select schema_id from sys.schemas where name='dbo'  \n" +
                "    )\n";
        String mysql = "select column_name,column_comment as remarks from INFORMATION_SCHEMA.Columns where table_name='"+tableName+"' and table_schema='test'";

        if (database.trim().equalsIgnoreCase("mysql")){
            sql = mysql;
        }else if (database.trim().equalsIgnoreCase("sqlserver")){
            sql = sql_server;
        }

        Connection connection = DataBaseTool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String columnName = resultSet.getString("column_name");
                String remarks = resultSet.getString("remarks") == null ? "": resultSet.getString("remarks") ;
                //获取列名、备注存放进map
                columnNameToRemarkMap.put(columnName, remarks);
            }
            Factory.columnNameToRemarkMap = columnNameToRemarkMap;
            ResultSetMetaData resultSetMetaData ;
            preparedStatement = connection.prepareStatement(Factory.sql);
            resultSet = preparedStatement.executeQuery();
            resultSetMetaData = resultSet.getMetaData();
            //重新整理数据将

            String columnName; // 获得指定列的列名
            String columnClassName;
            int columnDisplaySize = 0;// 在数据库中类型的最大字符个数
            int isNullable = 0;// 是否为空 --未使用



            HashMap<String, Object> columnMap ;
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                columnMap = new HashMap<String, Object>();

                //有用字段分析： 列名、列的数据类型，列字段的长度，是否非空
                columnName = resultSetMetaData.getColumnName(i);
                columnClassName = resultSetMetaData.getColumnClassName(i);
                columnDisplaySize = resultSetMetaData.getColumnDisplaySize(i);
                isNullable = resultSetMetaData.isNullable(i);
                columnMap.put("columnName", columnName);
                columnMap.put("columnClassName", columnClassName);
                columnMap.put("columnDisplaySize", columnDisplaySize);
                columnMap.put("isNullable", isNullable);
                for (String string : columnNameToRemarkMap.keySet()){
                    if (string.equals(columnName)){
                        columnMap.put("remarks",columnNameToRemarkMap.get(string));
                        break;
                    }
                }
                list.add(columnMap);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return list;
    }



    /**
     * 获取配置文件信息
     */
    public static void initConfigration() throws IOException {
        //读取配置文件
        InputStreamReader inputStreamReader = new InputStreamReader(Factory.class.getResourceAsStream("/utils/code/config.properties"), "UTF-8");
        Properties properties = new Properties();
        properties.load(inputStreamReader);


        String driver = properties.getProperty("jdbc.driver");
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        //表名
        String tableName = properties.getProperty("tableName");
        //包名
        String packageName = properties.getProperty("packageName");
        //module类名（控制器类名）
        String controllerName = properties.getProperty("controllerName");
        //model类名（DAO类名）
        String modelName = properties.getProperty("modelName");
        //对应的端
        String appClient = properties.getProperty("appClient");
        //项目路径（硬盘项目路径,带项目名）
        String projectPath = properties.getProperty("projectPath");
        //是否创建上传文件方法
        String isCreateUploadMethod = properties.getProperty("isCreateUploadMethod");
        //表主键的列名
        String pk_colunm_name = properties.getProperty("pk_colunm_name");
        //页面提示删除提示
        String itemName = properties.getProperty("itemName");
        //大后台功能ID 与 web-inf 下面的appIni.ini文件功能一直
        String FuncId = properties.getProperty("FuncId");
        //大后台一级功能树名称
        String oneLevelFuncionName = properties.getProperty("oneLevelFuncionName");
        //大后台二级功能树名称
        String twoLevelFuncionName = properties.getProperty("twoLevelFuncionName");


        String database = "" ;
        if (driver != null && ! driver.trim().equalsIgnoreCase("")){
            if (driver.toLowerCase().indexOf("mysql") != -1 ){
                database = "mysql";
            }else if (driver.toLowerCase().indexOf("sqlserver") != -1) {
                database = "sqlserver";
            }else {
                database = "oracle";
            }
        }
        Factory.database =database;
        Factory.driver = driver;
        Factory.url = url;
        Factory.username = username;
        Factory.password = password;
        Factory.tableName = tableName;
        Factory.packageName = packageName;
        Factory.controllerName = controllerName;
        Factory.modelName = modelName;
        Factory.appClient = appClient;
        Factory.isCreateUploadMethod = isCreateUploadMethod;
        Factory.pk_colunm_name = pk_colunm_name;
        Factory.itemName = itemName;
        Factory.FuncId = FuncId;
        Factory.oneLevelFuncionName = oneLevelFuncionName;
        Factory.twoLevelFuncionName = twoLevelFuncionName;
        sql = " SELECT * FROM " + tableName;
        Factory.templateDirPath = projectPath + "\\WebRoot\\"+appClient+"\\template";//生成logic文件位置配置
        Factory.srcDirPath = projectPath + "\\src\\";//项目硬盘的src所在目录;
        Factory.logic_skip_page_relative_file_path = "/" + packageName + "/" + controllerName + "/" + controllerName + "_";

    }



    public abstract String getFactoryStr() throws IOException, SQLException;
    public abstract boolean createFile() throws IOException;


    /**
     * 自动生成 : 已废弃
     */
    public static void autoGeneration() throws IOException, SQLException {
        long start = System.currentTimeMillis();
        sop("----------------------------------...开始生成...---------------------------------");




        sop("----------------------------------...生成结束...---------------------------------");
        long end = System.currentTimeMillis();
        sop("----------------------------------...共用时：" + (end - start) + "毫秒...---------------------------------");
    }
    public static void sop(Object obj) {
        System.out.println(obj);
    }

    /**
     *  未使用
     * */
    protected Factory getFactory(){


        return this;
    }

}
