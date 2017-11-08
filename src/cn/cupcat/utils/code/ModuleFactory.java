package cn.cupcat.utils.code;

import cn.cupcat.utils.file.FileUtil;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：本类主要是用来生成module文件，其中包含字段验证，简单的增删改查的基础操作
 *
 * @author zxy
 * @version 1.1
 * @since 2017年10月20日15:48:10
 */
public class ModuleFactory extends Factory {

    //常用集合 数组
    public static final String arrayList = " ArrayList<HashMap<String,Object>> lists ";
    public static final String arrayListFieldName = " lists ";
    public static final String arrayListArray = " ArrayList<HashMap<String,Object>>[] listsArray ";
    public static final String arrayListArrayFieldName = " listsArray ";

    public static final String hashMap = " HashMap<String,Object> map ";
    public static final String hashMapFieldName = " map ";
    public static final String hashMapArray = " HashMap<String,Object>[] mapArray ";
    public static final String hashMapArrayFieldName = " mapArray ";

    public static final String stringArray = " String[] jsonData ";
    public static final String getStringArrayFieldName = " jsonData ";


    //方法声明
    private static final String methodBegin = " public int ";
    private static final String methodReturn = " return constants.ERROR_SUCCESS;  ";

    //常用基本数据类型
    public static final String sInt = " int ";
    public static final String sInteger = " Integer ";
    public static final String sString = " String ";
    public static final String sDouble = " double ";


    //注解
    public static final String MAPAnotation = "@MAP";
    public static final String OutAnotation = "@Out";


    //固定参数
    public static final String session = " Session session ";

    public static final String pageNo = "pager.pageNo";
    public static final String pageSize = "pager.pageSize";
    public static final String sort = "sort";
    public static final String direction = "direction";

    public static final String argsPageNo = " int pageNo ";
    public static final String argsPageSize = " int pageSize ";
    public static final String argsSort = " String orderField ";
    public static final String argsDirection = " String orderDirection ";


    public static final String argsPageNoFieldName = " pageNo ";
    public static final String argsPageSizeFieldName = " pageSize ";
    public static final String argsSortFieldName = " orderField ";
    public static final String argsDirectionFieldName = " orderDirection ";

    public static final String mapArrayKey = "detail";
    public static final String arrayListArrayKey = "list";
    public static final String stringArrayKey = "jsonData";

    public static final String newPageQueryInfo = " PageQueryInfo pageQueryInfo = new PageQueryInfo() ; ";
    public static final String pageQueryInfoFieldName = " pageQueryInfo ";

    public static final String jsonObject = " JSONObject jsonObject ";
    public static final String jsonObjectFieldName = " jsonObject ";
    public static final String commonUtil = " CommonUtil";


    public static final String defaultOrderField = "ID";
    public static final String defaultOrderDirection = "DESC";


    public static boolean isDetail = false;

    /**
     * 生成代码常用变量 结束
     */


    //标志同一方法
    public static String flag = null;
    public static int errorCode = 2;

    /**
     * 动态生成init方法
     */
    public static String getInitMethod() {
        String comments = "/***/";//注释
        StringBuilder stringBuilder = new StringBuilder(50);
        stringBuilder.append(methodBegin + init + smallBrackets + leftBigBracket + newLine);
        stringBuilder.append(tab + methodReturn + newLine);
        stringBuilder.append(rightBigBracket + newLine);
        return stringBuilder.toString();
    }

    /**
     * 获取含有注解的参数
     */
    public static String getHasAnotationParam(String anotationName, String dataType, String args, String methodName, boolean isEnd, String... validateParam) {
        String[] argsArray = args.trim().split(" ");
        String paramName = argsArray[argsArray.length - 1].trim().equalsIgnoreCase("") ? argsArray[argsArray.length - 2].trim() : argsArray[argsArray.length - 1].trim(); //param = "" 的参数
        if (flag == null) { //第一次调用
            flag = methodName;
        } else {
            if (flag.trim().equals(methodName.trim())) { //同一方法调用
                if (dataType.trim().equals(sInt.trim()) || dataType.trim().equals("byte") || dataType.trim().equals("short") || dataType.trim().equals("long") || dataType.trim().equals("float") || dataType.trim().equals("double")) {
                    errorCode++;
                    String remarks = columnNameToRemarkMap.get(paramName.trim());
                    errorCodeMap.put(errorCode, remarks == null || remarks.equals("") ? paramName + "只能为数字！" : remarks + "只能为数字！");
                }
            } else {//不是同一方法
                errorCodeMaps.put(flag.trim(), (HashMap<Integer, String>) errorCodeMap.clone());//深拷贝
                errorCodeMap = new HashMap<Integer, String>();;//将map重置
                flag = methodName;
                errorCode = 2;
            }
        }


        String str = "";
        if (anotationName == MAPAnotation) {//输入注解
            if (dataType.trim().equals(sInt.trim())) {
                if (args == argsPageNo) {
                    str += MAPAnotation + leftSmallBracket + " param = \"" + pageNo + "\" , error = " + error_one + rightSmallBracked + argsPageNo;
                    errorCode = 1;
                } else if (args == argsPageSize) {
                    str += MAPAnotation + leftSmallBracket + " param = \"" + pageSize + "\" , error = " + error_one + rightSmallBracked + argsPageSize;
                    errorCode = 1;
                } else {
                    str += MAPAnotation + leftSmallBracket + " param = \"" + paramName + "\" , error = " + errorCode + rightSmallBracked + args;
                }
            } else if (dataType.trim().equals(sString.trim())) {
                if (args == argsSort) {
                    str += MAPAnotation + leftSmallBracket + " param = \"" + sort + "\" ,nullValue = \"" + defaultOrderField + "\" , emptyValue = \"" + defaultOrderField + "\" " + rightSmallBracked + argsSort;
                } else if (args == argsDirection) {
                    str += MAPAnotation + leftSmallBracket + " param = \"" + direction + "\" ,nullValue = \"" + defaultOrderDirection + "\" , emptyValue = \"" + defaultOrderDirection + "\" " + rightSmallBracked + argsDirection;
                } else {
                    if (isEnd) { //不加验证的
                        str += MAPAnotation + leftSmallBracket + " param = \"" + paramName + "\" ,nullValue = \"\" , emptyValue = \"\" " + rightSmallBracked + args;
                    } else {
                        str += MAPAnotation + leftSmallBracket + " param = \"" + paramName + "\" " + rightSmallBracked;
                    }
                }
            } else { //byte、short、long、float、double 使用
                str += MAPAnotation + leftSmallBracket + " param = \"" + paramName + "\",error= " + errorCode + rightSmallBracked + args;
            }
        } else if (anotationName == OutAnotation) {//输出注解
            if (dataType == hashMapArray) {
                str += OutAnotation + leftSmallBracket + " name = {\"" + mapArrayKey + "\" } " + rightSmallBracked + hashMapArray;
            } else if (dataType == arrayListArray) {
                str += OutAnotation + leftSmallBracket + " name = {\"" + arrayListArrayKey + "\" } " + rightSmallBracked + arrayListArray;
            } else {
                str += OutAnotation + leftSmallBracket + " name = {\"" + stringArrayKey + "\" } " + rightSmallBracked + stringArray;
            }
        } else {//验证注解
            if (isEnd) {
                str = getValidateAnnotationStr(anotationName, validateParam) + " " + args;
            } else {
                str = getValidateAnnotationStr(anotationName, validateParam);
            }
        }
        return str;
    }

    /**
     * 获取数据库字段对应的java类型；
     * 其中除了对应的 byte、short、int、long、float、double 都默认为String
     */
    public static String getColumnClassType(String realColumnType) {
        String columnClassType;
        if (realColumnType.equals("Byte") || realColumnType.equals("Short") || realColumnType.equals("Integer") || realColumnType.equals("Long") || realColumnType.equals("Float") || realColumnType.equals("Double")) {
            columnClassType = " " + realColumnType.toLowerCase() + " ";
        } else {
            columnClassType = sString;
        }
        return columnClassType;
    }

    /**
     * 获取验证注解
     *
     * @param annotationName 注解名称
     * @param args
     **/
    public static String getValidateAnnotationStr(String annotationName, String... args) {
        String str;//方法返回字符串

        if (annotationName.equalsIgnoreCase("@Match")) {
            str = "@Match( pattern = \"" + args[0] + "\",error = " + args[1] + ")";
        } else if (annotationName.equalsIgnoreCase("@NotNull")) {
            str = "@NotNull( error = " + args[0] + ")";
        } else if (annotationName.equalsIgnoreCase("@NotEmpty")) {
            str = "@NotEmpty( error =  " + args[0] + ")";
        } else if (annotationName.equalsIgnoreCase("@Len")) {
            str = "@Len( minLen = " + args[0] + ",maxLen = " + args[1] + ",error= " + args[2] + ")";
        } else if (annotationName.equalsIgnoreCase("@Between")) {
            str = "@Len( minLen = " + args[0] + ",maxLen = " + args[1] + ",error= " + args[2] + ")";
        } else {
            str = "@SelfCheck(check=\"" + args[0] + "\")";
        }
        return str;
    }


    /**
     * 动态生成getListByPage方法
     */
    public static String getGetListByPageMethod() {

        StringBuilder stringBuilder = new StringBuilder(512);
        stringBuilder.append(methodBegin + getListByPage + leftSmallBracket + newLine);
        stringBuilder.append(tab + getHasAnotationParam(MAPAnotation, sInt, argsPageNo, getListByPage, true) + comma + newLine);
        stringBuilder.append(tab + getHasAnotationParam(MAPAnotation, sInt, argsPageSize, getListByPage, true) + comma + newLine);
        stringBuilder.append(tab + getHasAnotationParam(MAPAnotation, sString, argsSort, getListByPage, true) + comma + newLine);
        stringBuilder.append(tab + getHasAnotationParam(MAPAnotation, sString, argsDirection, getListByPage, true) + comma + newLine);
        stringBuilder.append(tab + getHasAnotationParam(OutAnotation, stringArray, stringArray, getListByPage, true) + comma + dataBase + comma + session + rightSmallBracked + leftBigBracket + newLine);
        stringBuilder.append(tab + tab + newPageQueryInfo + newLine);
        stringBuilder.append(tab + tab + arrayList + " = " + sNew + modelName + leftSmallBracket + dataBaseFieldName + rightSmallBracked + point + getListByPage + leftSmallBracket + argsSortFieldName + comma);
        stringBuilder.append(argsDirectionFieldName + comma + argsPageNoFieldName + comma + argsPageSizeFieldName + comma + pageQueryInfoFieldName + rightSmallBracked + semicolon + newLine);
        stringBuilder.append(tab + tab + jsonObject + " = " + commonUtil + point + "getQUIPageInfo" + leftSmallBracket + arrayListFieldName + comma + argsPageNoFieldName + comma + argsPageSizeFieldName + comma + pageQueryInfoFieldName + comma + argsSortFieldName + comma + argsDirectionFieldName + rightSmallBracked + semicolon + newLine);
        stringBuilder.append(tab + tab + stringArrayKey + "[" + 0 + "]" + " = " + jsonObjectFieldName + point + "toString" + smallBrackets + semicolon + newLine);
        stringBuilder.append(tab + tab + methodReturn + newLine + rightBigBracket + newLine);
        return stringBuilder.toString();

    }

    /**
     * 动态生成showAdd方法
     */
    public static String getShowAddMethod() {
        StringBuilder stringBuilder = new StringBuilder(100);
        stringBuilder.append(methodBegin + showAdd + leftSmallBracket + dataBase + comma + session + rightSmallBracked + leftBigBracket + newLine);
        stringBuilder.append(tab + methodReturn + newLine);
        stringBuilder.append(rightBigBracket + newLine);
        return stringBuilder.toString();
    }

    /**
     * 动态生成add方法
     */
    public static String getAddMethod() throws SQLException {
        Map<String, String> map = getAddOrEditParam(add);

        StringBuilder stringBuilder = new StringBuilder(1024);
        stringBuilder.append(methodBegin + add + leftSmallBracket + newLine);
        stringBuilder.append(tab + map.get("param") + dataBase + comma + session + rightSmallBracked + leftBigBracket + newLine);
        stringBuilder.append(map.get("code"));
        stringBuilder.append(tab + tab + "boolean b = " + sNew + modelName + leftSmallBracket + dataBaseFieldName + rightSmallBracked + point + add.trim() + leftSmallBracket + hashMapFieldName.trim() + rightSmallBracked + semicolon + newLine);
        stringBuilder.append(tab + tab + "if " + leftSmallBracket + "! b " + rightSmallBracked + leftBigBracket + newLine);
        stringBuilder.append(tab + tab + tab + "return " + dataBase_error + semicolon + newLine);
        stringBuilder.append(tab + tab + rightBigBracket + newLine);
        stringBuilder.append(tab + tab + methodReturn + newLine);
        stringBuilder.append(tab + rightBigBracket + newLine);
        return stringBuilder.toString();
    }

    /**
     * 动态生成showEdit方法
     */
    public static String getShowEditMethod() {
        String mehtodName = isDetail ? getDetail : showEdit;
        StringBuilder stringBuilder = new StringBuilder(512);
        stringBuilder.append(methodBegin + mehtodName + leftSmallBracket + newLine);
        String columnClassName = pk_column_class_name.equals(sInteger.trim()) ? sInt : getColumnClassType(pk_column_class_name);// 对应数据类型的类
        String args = columnClassName + pk_colunm_name.trim();
        stringBuilder.append(tab + getHasAnotationParam(MAPAnotation, columnClassName, args, showEdit, true) + comma + newLine);
        stringBuilder.append(tab + getHasAnotationParam(OutAnotation, hashMapArray, hashMapArray, showEdit, true) + comma + newLine);
        stringBuilder.append(tab + getHasAnotationParam(OutAnotation, arrayListArray, arrayListArray, showEdit, true) + comma + dataBase + comma + session + rightSmallBracked + leftBigBracket + newLine);
        stringBuilder.append(tab + tab + hashMap + eq + sNew + modelName + leftSmallBracket + dataBaseFieldName + rightSmallBracked + point + "getOne" + leftSmallBracket + pk_colunm_name + rightSmallBracked + semicolon + newLine);
        stringBuilder.append(tab + tab + "if " + leftSmallBracket + hashMapFieldName + dbEq + sNull + rightSmallBracked + leftBigBracket + newLine);
        stringBuilder.append(tab + tab + tab + "return " + dataBase_error + semicolon + newLine + tab + tab + rightBigBracket + newLine);
        stringBuilder.append(tab + tab + hashMapArrayFieldName + "[0]" + eq + hashMapFieldName + semicolon + newLine);
        stringBuilder.append(tab + tab + methodReturn + newLine + rightBigBracket + newLine);
        return stringBuilder.toString();
    }

    /**
     * 动态生成edit方法
     */
    public static String getEditMethod() throws SQLException {
        Map<String, String> map = getAddOrEditParam(edit);

        StringBuilder stringBuilder = new StringBuilder(1024);
        stringBuilder.append(methodBegin + edit + leftSmallBracket + newLine);
        stringBuilder.append(tab + map.get("param") + dataBase + comma + session + rightSmallBracked + leftBigBracket + newLine);
        stringBuilder.append(map.get("code"));
        stringBuilder.append(tab + tab + "boolean b = " + sNew + modelName + leftSmallBracket + dataBaseFieldName + rightSmallBracked + point + edit.trim() + leftSmallBracket + hashMapFieldName.trim() + comma + pk_colunm_name + rightSmallBracked + semicolon + newLine);
        stringBuilder.append(tab + tab + "if " + leftSmallBracket + "! b " + rightSmallBracked + leftBigBracket + newLine);
        stringBuilder.append(tab + tab + tab + "return " + dataBase_error + semicolon + newLine);
        stringBuilder.append(tab + tab + rightBigBracket + newLine);
        stringBuilder.append(tab + tab + methodReturn + newLine);
        stringBuilder.append(tab + rightBigBracket + newLine);
        return stringBuilder.toString();
    }

    /**
     * 动态生成delete方法
     */
    public static String getDeleteMethod() {
        StringBuilder stringBuilder = new StringBuilder(512);
        stringBuilder.append(methodBegin + delete + leftSmallBracket + newLine);
        String columnClassName = pk_column_class_name.equals(sInteger.trim()) ? sInt : getColumnClassType(pk_column_class_name);// 对应数据类型的类
        String args = columnClassName + pk_colunm_name.trim();
        stringBuilder.append(tab + getHasAnotationParam(MAPAnotation, columnClassName, args, delete, true) + comma + newLine);
        stringBuilder.append(tab + dataBase + rightSmallBracked + leftBigBracket + newLine);
        stringBuilder.append(tab + tab + "boolean b " + eq + sNew + modelName + leftSmallBracket + dataBaseFieldName + rightSmallBracked + point + delete + leftSmallBracket + pk_colunm_name + rightSmallBracked + semicolon + newLine);
        stringBuilder.append(tab + tab + "if " + leftSmallBracket + "! b " + rightSmallBracked + leftBigBracket + newLine);
        stringBuilder.append(tab + tab + tab + "return " + dataBase_error + semicolon + newLine);
        stringBuilder.append(tab + tab + rightBigBracket + newLine);
        stringBuilder.append(tab + tab + methodReturn + newLine);
        stringBuilder.append(tab + rightBigBracket + newLine);
        return stringBuilder.toString();
    }

    /**
     * 动态生成批量删除（batchDelete）方法
     */
    public static String getBatchDeleteMethod() {
        StringBuilder stringBuilder = new StringBuilder(512);
        stringBuilder.append(methodBegin + batchDelete + leftSmallBracket + newLine);
        String args = sString.trim() + " IDs";
        stringBuilder.append(tab + getHasAnotationParam(MAPAnotation, sString, args, batchDelete, true) + comma + newLine);
        stringBuilder.append(tab + dataBase + comma + session + rightSmallBracked + leftBigBracket + newLine);
        stringBuilder.append(tab + tab + "String[] idArray " + eq + "IDs" + point + "split" + leftSmallBracket + "\",\"" + rightSmallBracked + semicolon + newLine);
        stringBuilder.append(tab + tab + "boolean b " + eq + commonUtil + point + "isNumber" + leftSmallBracket + "idArray" + rightSmallBracked + semicolon + newLine);
        stringBuilder.append(tab + tab + "if " + leftSmallBracket + "! b " + rightSmallBracked + leftBigBracket + newLine);
        stringBuilder.append(tab + tab + tab + "return " + errorCode + semicolon + newLine + tab + tab + rightBigBracket + newLine);
        stringBuilder.append(tab + tab + "b" + eq + sNew + modelName + leftSmallBracket + dataBaseFieldName + rightSmallBracked + point + batchDelete + leftSmallBracket + "idArray" + rightSmallBracked + semicolon + newLine);
        stringBuilder.append(tab + tab + "if " + leftSmallBracket + "! b " + rightSmallBracked + leftBigBracket + newLine);
        stringBuilder.append(tab + tab + tab + "return " + dataBase_error + semicolon + newLine + tab + tab + rightBigBracket + newLine);
        stringBuilder.append(tab + methodReturn + newLine + rightBigBracket + newLine);
        return stringBuilder.toString();
    }




    /**
     * 动态生成获取详情（getDetail）方法
     */
    public static String getGetDetailMethod() {
        isDetail = true;
        return getShowEditMethod();
    }


    /**
     * 得到excel导入方法
     *
     * @throws SQLException
     */
    public static String getExcelImportMethod() throws SQLException {
        StringBuilder stringBuilder = new StringBuilder(2048);
        stringBuilder.append(methodBegin + getExcelImport + leftSmallBracket + newLine);
        stringBuilder.append(tab + " DataBase Db " + comma + newLine);
        stringBuilder.append(tab + MAPAnotation + leftSmallBracket + "param = \"attach\"" + rightSmallBracked + " @NotNull" + leftSmallBracket + "error = 1" + rightSmallBracked + " @NotEmpty" + leftSmallBracket + "error = 2" + rightSmallBracked + sString + "attach " + comma + newLine);
        stringBuilder.append(tab + OutAnotation + leftSmallBracket + "name =" + leftBigBracket + "\"errorlist\"" + rightBigBracket + rightSmallBracked + "List<String>" + middleBrackets + "errorStr" + comma + newLine);
        stringBuilder.append(tab + OutAnotation + leftSmallBracket + "name =" + leftBigBracket + "\"sucNum\"" + rightBigBracket + rightSmallBracked + "int" + middleBrackets + "sucNum" + comma + newLine);
        stringBuilder.append(tab + OutAnotation + leftSmallBracket + "name =" + leftBigBracket + "\"errorNum\"" + rightBigBracket + rightSmallBracked + "int" + middleBrackets + "errorNum" + comma + newLine);
        stringBuilder.append(tab + OutAnotation + leftSmallBracket + "name =" + leftBigBracket + "\"totalNum\"" + rightBigBracket + rightSmallBracked + "int" + middleBrackets + "totalNum" + comma + newLine);
        stringBuilder.append(tab + OutAnotation + leftSmallBracket + "name =" + leftBigBracket + "\"sign\"" + rightBigBracket + rightSmallBracked + "int" + middleBrackets + "sign" + comma + newLine);
        stringBuilder.append(tab + OutAnotation + leftSmallBracket + "name =" + leftBigBracket + "\"message\"" + rightBigBracket + rightSmallBracked + "String" + middleBrackets + "message" + rightSmallBracked + leftBigBracket + newLine);
        stringBuilder.append(tab + tab + modelName + " model = new " + modelName + " " + leftSmallBracket + "Db" + rightSmallBracked + semicolon + newLine);
        stringBuilder.append(tab + tab + "boolean flag = false" + semicolon + newLine);
        stringBuilder.append(tab + tab + "attach = attach.replace" + leftSmallBracket + "\",\"" + comma + "\"\"" + rightSmallBracked + semicolon + newLine);
        stringBuilder.append(tab + tab + "int attachId = 0" + semicolon + newLine);
        stringBuilder.append(tab + tab + "try" + leftBigBracket + newLine);
        stringBuilder.append(tab + tab + tab + "attachId = Integer.parseInt(attach)" + semicolon + newLine);
        stringBuilder.append(tab + tab + rightBigBracket + "catch" + leftSmallBracket + "Exception e" + rightSmallBracked + leftBigBracket + newLine);
        stringBuilder.append(tab + tab + rightBigBracket + newLine);
        stringBuilder.append(tab + tab + "AttachmentModel Attach = new AttachmentModel(Db)" + semicolon + newLine);
        stringBuilder.append(tab + tab + hashMap + "= Attach.getAttachmentInfo(attachId)" + semicolon + newLine);
        stringBuilder.append(tab + tab + "if(map == null){" + newLine);
        stringBuilder.append(tab + tab + tab + "sign[0] = 3" + semicolon + newLine);
        stringBuilder.append(tab + tab + tab + "return constants.ERROR_SUCCESS" + semicolon + newLine);
        stringBuilder.append(tab + tab + tab + "}" + newLine);
        stringBuilder.append(tab + tab + "String srcFile = (String)map.get(\"AttachFileName\")" + semicolon + newLine);
        stringBuilder.append(tab + tab + "String realFile = Application.getRealPath(srcFile)" + semicolon + newLine);
        stringBuilder.append(tab + tab + "String fileName = srcFile.substring(srcFile.lastIndexOf('/'));" + newLine);
        stringBuilder.append(tab + tab + "String exFileName = \"\"" + semicolon + newLine);
        stringBuilder.append(tab + tab + "int i = fileName.lastIndexOf('.')" + semicolon + newLine);
        stringBuilder.append(tab + tab + "if ((i >-1) && (i < (fileName.length() - 1))) {" + semicolon + newLine);
        stringBuilder.append(tab + tab + tab + "exFileName = fileName.substring(i + 1)" + semicolon + newLine + tab + tab + "}" + newLine);
        stringBuilder.append(tab + tab + "else{" + newLine);
        stringBuilder.append(tab + tab + tab + "sign[0] = 5" + semicolon + newLine);
        stringBuilder.append(tab + tab + tab + "return constants.ERROR_SUCCESS; // 非excel类型文件" + newLine + tab + tab + "}" + newLine);
        stringBuilder.append(tab + tab + "if(!exFileName.equalsIgnoreCase(\"xls\") && !exFileName.equalsIgnoreCase(\"xlsx\")){" + newLine);
        stringBuilder.append(tab + tab + tab + "sign[0] = 5" + semicolon + newLine);
        stringBuilder.append(tab + tab + tab + "return constants.ERROR_SUCCESS; // 非excel类型文件" + newLine + tab + tab + "}" + newLine);
        stringBuilder.append(tab + tab + "File src = new File(realFile)" + semicolon + newLine);
        stringBuilder.append(tab + tab + "if (!src.exists()) {" + newLine);
        stringBuilder.append(tab + tab + tab + "sign[0] = 2" + semicolon + newLine);
        stringBuilder.append(tab + tab + tab + "return constants.ERROR_SUCCESS;" + newLine + tab + tab + "}" + newLine + newLine);
        stringBuilder.append(tab + tab + "try {" + newLine + tab + tab + tab + "// 文件流指向excel文件" + newLine);
        stringBuilder.append(tab + tab + tab + "HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(realFile));// 创建工作薄" + newLine);
        stringBuilder.append(tab + tab + tab + "HSSFSheet sheet = workbook.getSheetAt(0);// 得到工作表" + newLine);
        stringBuilder.append(tab + tab + tab + "HSSFRow row = null;// 对应excel的行" + newLine);
        stringBuilder.append(tab + tab + tab + "HSSFCell cell = null;// 对应excel的列" + newLine + newLine);
        stringBuilder.append(tab + tab + tab + "int totalRow = sheet.getLastRowNum();// 得到excel的总记录条数" + newLine);
        stringBuilder.append(tab + tab + tab + "// 以下的字段一一对应数据库表的字段" + newLine);
        List<HashMap<String, Object>> data = columnDataList != null ? columnDataList : getColumnData();
        HashMap<String, Object> map = null;
        for (int i = 0; i < data.size(); i++) {
            map = data.get(i);
            String columnName = (String) map.get("columnName"); // 获得指定列的列名
            String[] splitClassNameArray = ((String) map.get("columnClassName")).split("\\."); //这里必须加上 \\ 转移符
            //String columnClassName = (splitClassNameArray[splitClassNameArray.length - 1]).trim().equals(sInteger.trim()) ? sInt : sString;// 对应数据类型的类型


            if (pk_colunm_name.trim().equals(columnName.trim())) {//是主键
                continue;
            } else {
                stringBuilder.append(tab + tab + tab + "String " + columnName + " = \"\";" + newLine);
            }
        }
        //stringBuilder.append(tab+tab+tab+"String test = \"\";"+newLine);
        stringBuilder.append(tab + tab + tab + "ArrayList<HashMap<String, Object>> listGet = new ArrayList<HashMap<String, Object>>();" + newLine);
        stringBuilder.append(tab + tab + tab + "totalNum[0] = totalRow;" + newLine);
        stringBuilder.append(tab + tab + tab + "sucNum[0] = 0;" + newLine);
        stringBuilder.append(tab + tab + tab + "errorNum[0] = 0;" + newLine);
        stringBuilder.append(tab + tab + tab + "ArrayList<String> temp = new ArrayList<String>();" + newLine);
        stringBuilder.append(tab + tab + tab + "String errorMsg = \"\";" + newLine + newLine);
        stringBuilder.append(tab + tab + tab + "for (int j = 1; j <=totalRow; j++) {" + newLine);
        stringBuilder.append(tab + tab + tab + tab + "HashMap<String, Object> retMap = new HashMap<String, Object>();" + newLine);
        stringBuilder.append(tab + tab + tab + tab + "row = sheet.getRow(j);" + newLine);
        for (int i = 1; i < data.size(); i++) {
            map = data.get(i);
            String columnName = (String) map.get("columnName"); // 获得指定列的列名
            if (pk_colunm_name.trim().equals(columnName.trim())) {//是主键
                continue;
            } else {
                stringBuilder.append(tab + tab + tab + tab + "cell = row.getCell(" + (i - 1) + ");" + newLine);
                stringBuilder.append(tab + tab + tab + tab + "if(cell!=null){" + newLine);
                stringBuilder.append(tab + tab + tab + tab + tab + "cell.setCellType(Cell.CELL_TYPE_STRING);" + newLine);
                stringBuilder.append(tab + tab + tab + tab + tab + columnName + " = " + "cell.getRichStringCellValue().toString().trim();" + newLine);
                stringBuilder.append(tab + tab + tab + tab + "}else{");
                stringBuilder.append(tab + tab + tab + tab + tab + columnName + " = \"\"");
                stringBuilder.append(tab + tab + tab + tab + "}");
            }
        }
        for (int i = 1; i < data.size(); i++) {
            map = data.get(i);
            String columnName = (String) map.get("columnName"); // 获得指定列的列名
            if (pk_colunm_name.trim().equals(columnName.trim())) {//是主键
                continue;
            } else {
                stringBuilder.append(tab + tab + tab + tab + "if (GenericValidator.isBlankOrNull(" + columnName + ")) {" + newLine);
                stringBuilder.append(tab + tab + tab + tab + tab + "errorMsg = \"第\" + (j) + \"行有错误," + columnName + "为空;\";" + newLine);
                stringBuilder.append(tab + tab + tab + tab + tab + "temp.add(errorMsg);" + newLine);
                stringBuilder.append(tab + tab + tab + tab + tab + "errorNum[0]++;" + newLine + tab + tab + tab + tab + tab + "continue;" + newLine + tab + tab + tab + tab + "}" + newLine);
            }

        }
        for (int i = 1; i < data.size(); i++) {
            map = data.get(i);
            String columnName = (String) map.get("columnName"); // 获得指定列的列名
            if (pk_colunm_name.trim().equals(columnName.trim())) {//是主键
                continue;
            } else {
                stringBuilder.append(tab + tab + tab + tab + tab + "retMap.put(\"" + columnName + "\", " + columnName + ");" + newLine);

            }
        }
        stringBuilder.append(tab + tab + tab + tab + tab + "listGet.add(retMap);" + newLine);
        stringBuilder.append(tab + tab + tab + tab + tab + "sucNum[0]++;" + newLine + tab + tab + tab + tab + "}" + newLine);
        stringBuilder.append(tab + tab + tab + tab + "// 这里是重点，若要求 有一条错误就不可以导入 ，请将注释打开，若要求有错误其它正确的继续导入，则注释" + newLine);
        stringBuilder.append(tab + tab + tab + tab + "if (errorNum[0] > 0) {" + newLine);
        stringBuilder.append(tab + tab + tab + tab + tab + "sign[0] = 0;" + newLine);
        stringBuilder.append(tab + tab + tab + tab + tab + "message[0] = \"信息导入失败！\";" + newLine);
        stringBuilder.append(tab + tab + tab + tab + tab + "errorStr[0] = temp;");
        stringBuilder.append(tab + tab + tab + tab + tab + "flag = false;" + newLine);
        stringBuilder.append(tab + tab + tab + tab + tab + "return 0;" + newLine + tab + tab + tab + tab + "}" + newLine);
        stringBuilder.append(tab + tab + tab + tab + " flag = model.addBatch(listGet);" + newLine);
        stringBuilder.append(tab + tab + tab + tab + " if(!flag){" + newLine);
        stringBuilder.append(tab + tab + tab + tab + tab + "sign[0] = 0;" + newLine);
        stringBuilder.append(tab + tab + tab + tab + tab + "message[0] = \"数据库错误！\";" + newLine);
        stringBuilder.append(tab + tab + tab + tab + tab + "return 0;" + newLine + tab + tab + tab + tab + "}" + newLine);
        stringBuilder.append(tab + tab + tab + "} catch (FileNotFoundException e) {" + newLine);
        stringBuilder.append(tab + tab + tab + "} catch (IOException e) {" + newLine);
        stringBuilder.append(tab + tab + tab + "} finally {" + newLine);
        stringBuilder.append(tab + tab + tab + tab + "if (flag) {" + newLine);
        stringBuilder.append(tab + tab + tab + tab + tab + "sign[0] = 0;" + newLine);
        stringBuilder.append(tab + tab + tab + tab + tab + "message[0] = \"信息导入成功！\";" + newLine + tab + tab + tab + tab + "}" + newLine);
        stringBuilder.append(tab + tab + tab + tab + "return constants.ERROR_SUCCESS;" + newLine);
        stringBuilder.append(tab + tab + tab + "}" + newLine + "}" + newLine);


        return stringBuilder.toString();
    }

    /**
     * 得到添加或者修改方法的参数
     */
    public static Map<String, String> getAddOrEditParam(String methodName) throws SQLException {
        List<HashMap<String, Object>> data = columnDataList != null ? columnDataList : getColumnData();
        Map<String, String> map = new HashMap<String, String>();
        StringBuilder param = new StringBuilder(512);//保存新增、修改参数
        StringBuilder code = new StringBuilder(512);//保存传入Model的map
        boolean fromAdd = methodName.trim().equals(add.trim()) ? true : false;


        code.append(newLine + tab + tab + "Map<String,Object> map = new HashMap<String,Object>();" + newLine);
        HashMap<String, Object> columnMap = null;
        for (int i = 0; i < data.size(); i++) {
            columnMap = data.get(i);
            String columnName = (String) columnMap.get("columnName"); // 获得指定列的列名
            String[] splitClassNameArray = ((String) columnMap.get("columnClassName")).split("\\."); //这里必须加上 \\ 转移符
            //除了byte、 short、int、long float、double 都默认为String类型
            String realColumnClassName = splitClassNameArray[splitClassNameArray.length - 1].trim();
            String columnClassName = realColumnClassName.equals(sInteger.trim()) ? sInt : getColumnClassType(realColumnClassName);// 对应数据类型的类
            String remarks = columnMap.get("remarks") == null || ((String) columnMap.get("remarks")).trim().equals("") ? columnName : (String) columnMap.get("remarks");//备注
            int columnDisplaySize = (Integer) columnMap.get("columnDisplaySize");// 在数据库中类型的最大字符个数
            int isNullable = (Integer) columnMap.get("isNullable"); // 是否为空

            //判断时候是主键，是-判断是add还是edit方法，  add-不用添加主键， edit 添加主键
            if (pk_colunm_name.trim().equals(columnName.trim())) {//是主键
                pk_column_class_name = realColumnClassName;
                if (!fromAdd) { //edit方法需要添加主键
                    String args = columnClassName + columnName;
                    String methodParam = getHasAnotationParam(MAPAnotation, columnClassName, args, methodName, true) + comma + newLine;
                    param.append(methodParam);
                }
            } else {//不是主键
                String methodParam;
                String args = columnClassName + columnName;
                if (isNullable == 0) {//不为空,验证

                    if (columnClassName.equals(sString) && realColumnClassName.equalsIgnoreCase(columnClassName.trim())) { //真对应String
                        methodParam = getHasAnotationParam(MAPAnotation, columnClassName, args, methodName, false);
                        methodParam += getHasAnotationParam("@NotNull", columnClassName, args, methodName, false, String.valueOf(errorCode == 9 ? errorCode = 11 : ++errorCode));
                        methodParam += getHasAnotationParam("@NotEmpty", columnClassName, args, methodName, false, String.valueOf(errorCode));
                        errorCodeMap.put(errorCode, remarks + "不能为空！");
                        methodParam += getHasAnotationParam("@Len", columnClassName, args, methodName, true, new String[]{"1", String.valueOf(columnDisplaySize), String.valueOf(errorCode == 9 ? errorCode = 11 : ++errorCode)}) + comma + newLine;
                        errorCodeMap.put(errorCode, remarks + "长度不能超过" + columnDisplaySize + "!");
                    } else {//其他类型,暂不处理
                        methodParam = getHasAnotationParam(MAPAnotation, columnClassName, args, methodName, true) + comma + newLine;
                        ;
                    }
                } else {
                    if (columnClassName.equals(sString) && realColumnClassName.equalsIgnoreCase(columnClassName.trim())) { //真对应String
                        methodParam = getHasAnotationParam(MAPAnotation, columnClassName, args, methodName, false);
                        methodParam += getHasAnotationParam("@Len", columnClassName, args, methodName, true, new String[]{"0", String.valueOf(columnDisplaySize), String.valueOf(errorCode == 9 ? errorCode = 11 : ++errorCode)}) + comma + newLine;
                        errorCodeMap.put(errorCode, remarks + "长度不能超过" + columnDisplaySize + "!");
                    } else {
                        methodParam = getHasAnotationParam(MAPAnotation, columnClassName, args, methodName, true) + comma + newLine;
                    }

                }
                param.append(methodParam);
                code.append(tab + tab + "map.put" + leftSmallBracket + "\"" + columnName.trim() + "\"" + comma + columnName + rightSmallBracked + semicolon + newLine);
            }
        }
        map.put("param", param.toString());
        map.put("code", code.toString());
        return map;
    }


    @Override
    public String getFactoryStr() throws IOException, SQLException {
        StringBuilder stringBuilder = new StringBuilder(4096);
        stringBuilder.append("package " + modulesName + point + packageName + semicolon + newLine);
        stringBuilder.append("public class " + controllerName + " extends MvcObj" + leftBigBracket + newLine);
        stringBuilder.append(tab + "public " + controllerName + leftSmallBracket + "MvcApplication app" + rightSmallBracked + leftBigBracket + newLine);
        stringBuilder.append(tab + tab + "super" + leftSmallBracket + "app" + rightSmallBracked + semicolon + newLine);
        stringBuilder.append(tab + rightBigBracket + newLine);

        String initMethod = getInitMethod();
        String getListByPageMethod = getGetListByPageMethod();
        String showAddMethod = getShowAddMethod();
        String addMethod = getAddMethod();
        String showEditMethod = getShowEditMethod();
        String editMethod = getEditMethod();
        String deleteMethod = getDeleteMethod();
        String batchDeleteMethod = getBatchDeleteMethod();
        String getDetail = getGetDetailMethod();
        String ExcelImport = "";
        if (isCreateUploadMethod.equalsIgnoreCase("true")) {
            ExcelImport = getExcelImportMethod();//导入
        }


        stringBuilder.append(tab + initMethod);
        stringBuilder.append(tab + getListByPageMethod);
        stringBuilder.append(tab + showAddMethod);
        stringBuilder.append(tab + addMethod);
        stringBuilder.append(tab + showEditMethod);
        stringBuilder.append(tab + editMethod);
        stringBuilder.append(tab + deleteMethod);
        stringBuilder.append(tab + batchDeleteMethod);
        stringBuilder.append(tab + getDetail);
        stringBuilder.append(tab + ExcelImport);//导入
        stringBuilder.append(newLine + rightBigBracket + newLine);
        return stringBuilder.toString();
    }

    /**
     * 生成控制器(Module)文件
     */
    @Override
    public boolean createFile() throws IOException {
        String filePath = srcDirPath + File.separator + modulesName + File.separator + packageName + File.separator + controllerName + ".java";
        sop("----------------------------------...即将开始自动生成module文件...---------------------------------");
        try {
            String content = getFactoryStr();
            FileUtil.writeFile(content, filePath);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        sop("----------------------------------...自动生成module文件完成...---------------------------------");
        return true;
    }




}
