package cn.cupcat.utils.common;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import system.lib.*;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by xy on 2017/10/11.
 */
public class CommonUtil {

    public static final String dataFormatStr = "yyyy-MM-dd";
    public static final String dataTimeForamtStr = "yyyy-MM-dd HH:mm:ss";
    public static final String dataFormat = "yyyyMMdd";


    /**
     * 获取当天时间格式为 yyyyMMdd,一般用于上传文件的时候指定目录
     */
    public static String getTodayDateStr() {
        DateFormat dateFormat = new SimpleDateFormat(dataFormat);
        return dateFormat.format(new Timestamp(System.currentTimeMillis()));
    }

    /**
     * 获得上传文件失败，获取错误码对应错误
     */
    public static int getFileUploadErrorCode(int lastError) {
        switch (lastError) {
            case -1:
                return 3;//"您所提供表单类型不是多数据类型multipart-formdata";
            case -2:
                return 4;//没有上传任何数据！;
            case -3:
                return 5;//文件格式不合法！;
            case -4:
                return 6;//请求数据的size超出了规定的大小！
            case -5:
                return 7;//请求数据的size超出了规定的大小！
            case -6:
                return 8;//文件传输出现错误,例如磁盘空间不足等！
            case -7:
                return 9;//无效的请求类型！
            case -8:
                return 10;//上传失败！
            case -9:
                return 11;//编码不能识别！
            default:
                break;
        }
        return 16;//无法识别的错误
    }

    /**
     * 格式化时间
     *
     * @param timestamp
     * @param format
     * @author zxy
     */
    public static String formatTimestamp(Timestamp timestamp, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(timestamp);

    }

    /**
     * 格式化数字，四舍五入保留两位小数
     */
    public static String formatDouble(double num) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        return df.format(num);
    }

    /**
     * 获得DataBase对象
     */
    public static DataBase getDataBase() {
        MvcDBManager mvcDBManager = new MvcDBManager();
        mvcDBManager.setDBStr("java:comp/env/www_ZMTPlatform_2017/jdbcmssql");
        return mvcDBManager.getDB();
    }



    /**
     * 获取当天日期 yyyy-MM-dd 格式
     */
    public static String getTodayDate() {
        String todayDate = CommonUtil.formatTimestamp(new Timestamp(System.currentTimeMillis()), CommonUtil.dataFormatStr);
        return todayDate;
    }

    /**
     * 获得当前时间
     *
     * @return Timestamp
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }


    /**
     * 验证传入的字符串数组的每一项是否全是 整数类型
     *
     * @param array
     * @return boolean
     */
    public static boolean isNumber(String[] array) {
        try {
            for (String item : array) {
                Integer.parseInt(item.trim());//验证是整数
            }
        } catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 获取32位小写UUID
     *
     * @return String uuid
     * @author zxy
     * @since 2017年6月16日18:31:05
     */
    public static String getUUID32() {

        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    /**
     * 获取指定长度小写的UUID（如传入的length不合法，将返回默认32位小写UUID）
     *
     * @param length int类型	生成UUID长度（0-32之间）
     * @return String    length位小写UUID
     * @author zxy
     * @since 2017年6月16日18:31:57
     */
    public static String getUUID(int length) {

        if (length <= 0 || length > 32) {
            return getUUID32();
        }
        return getUUID32().substring(0, length);
    }

    /**
     * 得到指定数量的32位小写uuid（如果传入num不合法，将返回null）
     *
     * @param num int类型	生成UUID数量
     * @return String[]    指定数量的UUID数字
     * @author zxy
     * @since 2017年6月16日19:08:59
     */
    public static String[] getUUIDArray(int num) {

        if (num <= 0) {
            return null;
        }
        String[] uuidArray = new String[num];

        for (int i = 0; i < uuidArray.length; i++) {
            uuidArray[i] = getUUID32();
        }

        return uuidArray;
    }


    //将ArrayList转换为json字符串
    public static String switchListToJSONStr(ArrayList<HashMap<String, Object>> list) {
        if (list != null && list.size() > 0) {
            return JSONArray.fromObject(list).toString();
        }
        return "[]";
    }

    /**
     * 设置QUI的分页信息
     *
     * @param list           ArrayList<HashMap<String,Object>>	数据
     * @param pageNum        int类型		页码
     * @param pageSize       int类型		每页显示条数
     * @param pageQueryInfo  String类型	分页类
     * @param orderField     String类型	排序字段
     * @param orderDirection String类型	排序方式（desc 或者  asc）
     * @return JSONObject
     * @author zxy
     * @since 2017年6月15日17:17:50
     */
    public static JSONObject getQUIPageInfo(ArrayList<HashMap<String, Object>> list, int pageNum, int pageSize, PageQueryInfo pageQueryInfo, String orderField, String orderDirection) {

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("rows", switchListToJSONStr(list));
        jsonObj.put("pager.pageNo", pageNum);
        jsonObj.put("pager.pageSize", pageSize);
        jsonObj.put("pager.totalRows", pageQueryInfo.recordCount);
        jsonObj.put("sort", orderField);
        jsonObj.put("direction", orderDirection);
        jsonObj.put("errorResult", 0);

        return jsonObj;
    }


    /**
     * 验证字符串形如("1,2,3")格式的字符串使用逗号分隔以后，各项是否全是int类型
     *
     * @param str       String类型
     * @param errorCode int类型	验证失败返回的错误吗
     * @return int        验证失败返回errorCode（str为null时，属于验证失败），成功返回0
     * @author zxy
     * @since 2017年6月27日09:07:27
     */
    public static int validateStrIsNum(String str, int errorCode) {

        if (str == null) {
            return errorCode;
        }
        String[] split = str.split(",");

        try {
            for (String string : split) {
                Integer.parseInt(string);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return errorCode;
        }

        return constants.ERROR_SUCCESS;
    }

    /**
     * 验证字符串形如("1,2,3")格式的字符串使用逗号分隔以后，各项是否全是int类型；验证成功会添加到list中,验证失败会清空list集合
     *
     * @param str       String类型
     * @param errorCode int类型	验证失败返回的错误吗
     * @return int        验证失败返回errorCode（str为null时，属于验证失败），成功返回0
     * @author zxy
     * @since 2017年6月27日09:17:27
     */
    public static int validateStrIsNum(String str, int errorCode, List<Integer> list) {

        if (str == null) {
            return errorCode;
        }
        String[] split = str.split(",");
        try {
            Integer num = null;
            for (String string : split) {
                num = Integer.parseInt(string);
                list.add(num);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            list = new ArrayList<Integer>();
            return errorCode;
        }

        return constants.ERROR_SUCCESS;
    }


    /**
     * 获得list1 与 list2的差集（相当于list1.removeAll(list2)）
     *
     * @param list1 List<Integer>
     * @param list2 List<Integer>
     * @return List<Integer> list1与list2的差集
     * @author zxy
     * @since 2017年6月27日08:37:48
     */
    public static List<Integer> minusList(List<Integer> list1, List<Integer> list2) {
        //保存list1 与list2的差集
        List<Integer> minusList = new ArrayList<Integer>();

        if (list2.size() == 0) {
            return list1;
        }
        for (Integer integer : list1) {
            if (!list2.contains(integer)) {
                minusList.add(integer);
            }
        }
        return minusList;
    }


    /**
     * 删除指定文件
     *
     * @param filePath 文件的绝对路径
     * @return boolean    删除成功返回true	删除失败返回false
     * @author zxy
     * @since 2017年5月26日08:55:26
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        //判断文件是否存在
        if (file.exists()) {
            return file.delete();//删除成功返回true 失败返回false
        }
        return false;
    }

    /**
     * 将List集合中的元素生成sql语句in后的条件,若集合为空，则返回''
     *
     * @param list List<?>类型 list集合
     * @return true 操作成功 false 操作失败
     * @author zxy
     * @since 2017年7月6日上午10:10:33
     */
    public static String getFilterSQl(List<?> list) {

        if (list == null || list.size() == 0) {
            return "";
        }
        String sql = " WITH TEMP_TABLE AS ( ";
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sql += " SELECT " + list.get(i) + " FROM DUAL ";
            } else {
                sql += " SELECT " + list.get(i) + " FROM DUAL UNION ";
            }
        }
        sql += " ) SELECT * FROM TEMP_TABLE";

        return sql;
    }
}
