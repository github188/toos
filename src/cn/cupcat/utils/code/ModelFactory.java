package cn.cupcat.utils.code;


import cn.cupcat.utils.file.FileUtil;

import java.io.File;
import java.io.IOException;


/**
 * 功能：本类主要是用来生成model文件
 *
 * @author zxy
 * @version 1.1
 * @since 2017年10月20日15:48:10
 */

public class ModelFactory extends Factory {
    /**
     * 获取model（DAO）字符串
     */
    @Override
    public String getFactoryStr() throws IOException {

        StringBuilder stringBuilder = new StringBuilder(512);
        stringBuilder.append("package " + model + point + packageName + semicolon + newLine);
        stringBuilder.append("public class " + modelName + " extends BasicDAO<" + pk_column_class_name + ">" + leftBigBracket + newLine);
        stringBuilder.append(tab + "public static String tableOrViewName = \"" + tableName + "\"" + semicolon + "//表名 或者 视图名" + newLine);

        stringBuilder.append(tab + "public " + modelName + leftSmallBracket + dataBase + rightSmallBracked + leftBigBracket + newLine);
        stringBuilder.append(tab + tab + "super" + leftSmallBracket + "tableOrViewName," + dataBaseFieldName + rightSmallBracked + semicolon + newLine);
        stringBuilder.append(tab + rightBigBracket + newLine);

        stringBuilder.append(tab + "public " + modelName + leftSmallBracket + "String tableName," + dataBase + rightSmallBracked + leftBigBracket + newLine);
        stringBuilder.append(tab + tab + "super" + leftSmallBracket + "tableName," + dataBaseFieldName + rightSmallBracked + semicolon + newLine);
        stringBuilder.append(tab + rightBigBracket + newLine);
        
        String comment = tab + "/**" + newLine;
        comment += tab + " * 分页查看列表" + newLine;
        comment += tab + " * @param orderField     排序字段" + newLine;
        comment += tab + " * @param orderDirection 排序规则" + newLine;
        comment += tab + " * @param pageNum        分页对象" + newLine;
        comment += tab + " * @param pageSize       页码" + newLine;
        comment += tab + " * @param pageQueryInfo  每页显示条数" + newLine;
        comment += tab + "*/" + newLine;
        stringBuilder.append(comment);

        stringBuilder.append(tab + " public ArrayList<HashMap<String, Object>> getListByPage(String orderField, String orderDirection, Integer pageNum, Integer pageSize, PageQueryInfo pageQueryInfo) {" + newLine);
        stringBuilder.append(tab + tab + " HashMap<String,String> paramMap = new HashMap<String,String>();" + newLine);
        stringBuilder.append(tab + tab + " return getListByPage(orderField, orderDirection, pageNum, pageSize, pageQueryInfo, paramMap, tableName, null,null, dataBase);" + newLine);
        stringBuilder.append(tab + " }" + newLine);
        stringBuilder.append(rightBigBracket + newLine);
        return stringBuilder.toString();
    }

    @Override
    public boolean createFile() throws IOException {
        String content = getFactoryStr();
        String filePath = srcDirPath + File.separator + model + File.separator + packageName + File.separator + modelName + ".java";
        sop("----------------------------------...即将开始自动生成model文件...---------------------------------");
        try {
            FileUtil.writeFile(content, filePath);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        sop("----------------------------------...自动生成model文件完成 ...---------------------------------");
        return true;
    }
}
