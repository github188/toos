package cn.cupcat.utils.code;



import cn.cupcat.utils.file.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

/**
 * 功能：本类主要是用来生成logic文件
 *
 * @author zxy
 * @version 1.1
 * @since 2017年10月20日15:53:58
 */
public class LogicFactory extends Factory {




    /**
     * logic常量 开始
     */
    public String error_id_info = "ID错误！";
    public String error_delete_id_info = "删除失败，" + error_id_info;
    public String error_database_info = "数据库错误，请稍后重试";
    public String error_page_info = "分页参数错误！";
    public final String logic_comment = ";============================================================ ";
    public final String logic_begin = " begin ";
    public final String logic_end = " end ";

    public final String logic_error_success = "0";


    public final String logic_default = "default";
    private final String logic_param_file = "param.file";
    private final String logic_ajax_path_dir = "/system/";
    private final String logic_ajax_file = "message_ajax.httl";
    private final String logic_ajax_sys_error_file = "/system_meet_ajax.httl";

    public final String logic_ajax_default_status_300 = "context.statusCode=300";
    public final String logic_ajax_success_status_200 = "context.statusCode=200";
    public final String logic_action_view = "action=view";
    public final String logic_content_message_eq = "context.message" + eq.trim();
    public final String logic_ajax_default_file_path = logic_param_file + eq.trim() + logic_ajax_path_dir + logic_ajax_file;
    public final String logic_ajax_sys_error_file_path = logic_param_file + eq.trim() + "" + logic_ajax_sys_error_file;
    public String json_data_path = "/json/json.httl";
    private final String json_data_file_path_param_file = logic_param_file + eq.trim() + json_data_path;
    private final String logic_skip_page_sys_error_file = "/system_meet.httl";
    public final String logic_skip_page_success_file_path = logic_param_file + eq.trim() + logic_skip_page_relative_file_path;
    public final String logic_sys_error = "sys_error";

    public final String logic_skip_page_sys_error_file_path = logic_param_file + eq.trim() + logic_skip_page_sys_error_file;

    private final String logic_relative_file_path = "/" + packageName + "/" + controllerName + "/";
    /**logic常量 结束*/


    /**
     * 获得init方法的logic
     */
    public String getInitLogic() {
        StringBuilder stringBuilder = new StringBuilder(512);


        stringBuilder.append(logic_comment + init.trim() + logic_begin + newLine);
        stringBuilder.append(leftMiddleBracket + init.trim() + point + logic_default + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine + newLine);
        stringBuilder.append(leftMiddleBracket + init.trim() + point + logic_error_success + rightMiddleBracket + newLine);
        stringBuilder.append(logic_skip_page_success_file_path + init.trim() + ".httl" + newLine + newLine);
        stringBuilder.append(leftMiddleBracket + init.trim() + point + logic_sys_error + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine);
        stringBuilder.append(logic_skip_page_sys_error_file_path + newLine);
        stringBuilder.append(logic_comment + init.trim() + logic_end + newLine);
        return stringBuilder.toString();
    }

    /**
     * 获得列表logic
     */
    public String getGetListByPageLogic() {
        StringBuilder stringBuilder = new StringBuilder(512);


        stringBuilder.append(logic_comment + getListByPage.trim() + logic_begin + newLine);
        stringBuilder.append(leftMiddleBracket + getListByPage.trim() + point + logic_default + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine);
        stringBuilder.append(logic_ajax_default_status_300 + newLine);
        stringBuilder.append(logic_ajax_default_file_path + newLine + newLine);


        stringBuilder.append(leftMiddleBracket + getListByPage.trim() + point + logic_error_success + rightMiddleBracket + newLine);
        stringBuilder.append(logic_ajax_success_status_200 + newLine);
        stringBuilder.append(json_data_file_path_param_file + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getListByPage.trim() + point + error_one.trim() + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + error_page_info + newLine + newLine);


        stringBuilder.append(leftMiddleBracket + getListByPage.trim() + point + logic_sys_error + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine);
        stringBuilder.append(logic_ajax_default_status_300 + newLine);
        stringBuilder.append(logic_ajax_sys_error_file_path + newLine);

        stringBuilder.append(logic_comment + getListByPage.trim() + logic_end);


        return stringBuilder.toString();
    }

    /**
     * 获得进入添加界面logic
     */
    public String getShowAddLogic() {
        StringBuilder stringBuilder = new StringBuilder(512);


        stringBuilder.append(logic_comment + showAdd.trim() + logic_begin + newLine);
        stringBuilder.append(leftMiddleBracket + showAdd.trim() + point + logic_default + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine + newLine);
        stringBuilder.append(leftMiddleBracket + showAdd.trim() + point + logic_error_success + rightMiddleBracket + newLine);
        stringBuilder.append(logic_skip_page_success_file_path + add.trim() + ".httl" + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + showAdd.trim() + point + logic_sys_error + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine);
        stringBuilder.append(logic_skip_page_sys_error_file_path + newLine);
        stringBuilder.append(logic_comment + showAdd.trim() + logic_end + newLine);


        return stringBuilder.toString();
    }

    /**
     * 获得添加logic
     */
    public String getAddLogic() {
        StringBuilder stringBuilder = new StringBuilder(512);

        stringBuilder.append(logic_comment + add.trim() + logic_begin + newLine);
        stringBuilder.append(leftMiddleBracket + add.trim() + point + logic_default + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine);
        stringBuilder.append(logic_ajax_default_status_300 + newLine);
        stringBuilder.append(logic_ajax_default_file_path + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + add.trim() + point + logic_error_success + rightMiddleBracket + newLine);
        stringBuilder.append(logic_ajax_success_status_200 + newLine);
        stringBuilder.append(logic_content_message_eq + "添加成功！" + newLine + newLine);

        HashMap<Integer, String> map = errorCodeMaps.get(add.trim());
        Set<Integer> integers = map.keySet();

        for (Integer integer : integers) {//循环遍历
            String columnName = map.get(integer);
            if (integer == 11 ){ //当错误码为11的时候，添加10
                stringBuilder.append(leftMiddleBracket + add.trim() + point + dataBase_error.trim() + rightMiddleBracket + newLine);
                stringBuilder.append(logic_content_message_eq + "添加失败," + error_database_info  + newLine + newLine);
            }
            stringBuilder.append(leftMiddleBracket + add.trim() + point + integer + rightMiddleBracket + newLine);
            stringBuilder.append(logic_content_message_eq + "添加失败," + columnName  + newLine + newLine);
        }
        if (integers.size() < 11 ){ //没有把错误码为10的添加上，则补上
            stringBuilder.append(leftMiddleBracket + add.trim() + point + dataBase_error.trim() + rightMiddleBracket + newLine);
            stringBuilder.append(logic_content_message_eq + "添加失败," + error_database_info  + newLine + newLine);
        }

        stringBuilder.append(leftMiddleBracket + add.trim() + point + logic_sys_error + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine);
        stringBuilder.append(logic_ajax_default_status_300 + newLine);
        stringBuilder.append(logic_ajax_sys_error_file_path + newLine);
        stringBuilder.append(logic_comment + add.trim() + logic_end);
        return stringBuilder.toString();
    }


    /**
     * 获得进入修改界面logic
     */
    public String getShowEditLogic() {
        StringBuilder stringBuilder = new StringBuilder(512);


        stringBuilder.append(logic_comment + showEdit.trim() + logic_begin + newLine);
        stringBuilder.append(leftMiddleBracket + showEdit.trim() + point + logic_default + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine + newLine);
        stringBuilder.append(leftMiddleBracket + showEdit.trim() + point + logic_error_success + rightMiddleBracket + newLine);
        stringBuilder.append(logic_skip_page_success_file_path + edit.trim() + ".httl" + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + showEdit.trim() + point + error_id + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + error_id_info + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + showEdit.trim() + point + dataBase_error.trim() + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + error_database_info + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + showEdit.trim() + point + logic_sys_error + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine);
        stringBuilder.append(logic_skip_page_sys_error_file_path + newLine);
        stringBuilder.append(logic_comment + showEdit.trim() + logic_end + newLine);


        return stringBuilder.toString();
    }

    /**
     * 获得修改logic
     */
    public String getEditLogic() {
        StringBuilder stringBuilder = new StringBuilder(512);

        stringBuilder.append(logic_comment + edit.trim() + logic_begin + newLine);
        stringBuilder.append(leftMiddleBracket + edit.trim() + point + logic_default + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine);
        stringBuilder.append(logic_ajax_default_status_300 + newLine);
        stringBuilder.append(logic_ajax_default_file_path + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + edit.trim() + point + logic_error_success + rightMiddleBracket + newLine);
        stringBuilder.append(logic_ajax_success_status_200 + newLine);
        stringBuilder.append(logic_content_message_eq + "修改成功！" + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + edit.trim() + point + error_id + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + "修改失败," + error_id_info + newLine + newLine);

        HashMap<Integer, String> map = errorCodeMaps.get(edit.trim());
        Set<Integer> integers = map.keySet();
        for (Integer integer : integers) {//循环遍历
            String columnName = map.get(integer);
            if (integer == 11 ){ //当错误码为11的时候，添加10
                stringBuilder.append(leftMiddleBracket + edit.trim() + point + dataBase_error.trim() + rightMiddleBracket + newLine);
                stringBuilder.append(logic_content_message_eq + "修改失败," + error_database_info  + newLine + newLine);
            }
            stringBuilder.append(leftMiddleBracket + edit.trim() + point + integer + rightMiddleBracket + newLine);
            stringBuilder.append(logic_content_message_eq + "修改失败," + columnName  + newLine + newLine);
        }
        if (integers.size() < 11 ){ //没有把错误码为10的添加上，则补上
            stringBuilder.append(leftMiddleBracket + add.trim() + point + dataBase_error.trim() + rightMiddleBracket + newLine);
            stringBuilder.append(logic_content_message_eq + "修改失败," + error_database_info  + newLine + newLine);
        }

        stringBuilder.append(leftMiddleBracket + edit.trim() + point + logic_sys_error + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine);
        stringBuilder.append(logic_ajax_default_status_300 + newLine);
        stringBuilder.append(logic_ajax_sys_error_file_path + newLine);
        stringBuilder.append(logic_comment + edit.trim() + logic_end);
        return stringBuilder.toString();
    }

    /**
     * 获得删除功能logic
     */
    public String getDelteLogic() {
        StringBuilder stringBuilder = new StringBuilder(512);


        stringBuilder.append(logic_comment + delete.trim() + logic_begin + newLine);
        stringBuilder.append(leftMiddleBracket + delete.trim() + point + logic_default + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine);
        stringBuilder.append(logic_ajax_default_status_300 + newLine);
        stringBuilder.append(logic_ajax_default_file_path + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + delete.trim() + point + logic_error_success + rightMiddleBracket + newLine);
        stringBuilder.append(logic_ajax_success_status_200 + newLine + newLine);


        stringBuilder.append(leftMiddleBracket + delete.trim() + point + error_id + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + error_delete_id_info + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + delete.trim() + point + dataBase_error.trim() + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + error_database_info + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + delete.trim() + point + logic_sys_error + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine);
        stringBuilder.append(logic_ajax_default_status_300 + newLine);
        stringBuilder.append(logic_ajax_sys_error_file_path + newLine);

        stringBuilder.append(logic_comment + delete.trim() + logic_end);
        return stringBuilder.toString();
    }

    /**
     * 获得批量删除功能logic
     */
    public String getBatchDelteLogic() {
        StringBuilder stringBuilder = new StringBuilder(512);


        stringBuilder.append(logic_comment + batchDelete.trim() + logic_begin + newLine);
        stringBuilder.append(leftMiddleBracket + batchDelete.trim() + point + logic_default + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine);
        stringBuilder.append(logic_ajax_default_status_300 + newLine);
        stringBuilder.append(logic_ajax_default_file_path + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + batchDelete.trim() + point + logic_error_success + rightMiddleBracket + newLine);
        stringBuilder.append(logic_ajax_success_status_200 + newLine + newLine);


        stringBuilder.append(leftMiddleBracket + batchDelete.trim() + point + error_id + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + error_delete_id_info + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + batchDelete.trim() + point + dataBase_error.trim() + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + error_database_info + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + batchDelete.trim() + point + logic_sys_error + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine);
        stringBuilder.append(logic_ajax_default_status_300 + newLine);
        stringBuilder.append(logic_ajax_sys_error_file_path + newLine);

        stringBuilder.append(logic_comment + batchDelete.trim() + logic_end);
        return stringBuilder.toString();
    }

    /**
     * 获得详情功能logic
     */
    public String getGetDetailLogic() {
        StringBuilder stringBuilder = new StringBuilder(512);

        stringBuilder.append(logic_comment + getDetail.trim() + logic_begin + newLine);
        stringBuilder.append(leftMiddleBracket + getDetail.trim() + point + logic_default + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine + newLine);
        stringBuilder.append(leftMiddleBracket + getDetail.trim() + point + logic_error_success + rightMiddleBracket + newLine);
        stringBuilder.append(logic_skip_page_success_file_path + "detail".trim() + ".httl" + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getDetail.trim() + point + error_id + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + error_id_info + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getDetail.trim() + point + dataBase_error.trim() + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + error_database_info + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getDetail.trim() + point + logic_sys_error + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine);
        stringBuilder.append(logic_skip_page_sys_error_file_path + newLine);
        stringBuilder.append(logic_comment + getDetail.trim() + logic_end + newLine);


        return stringBuilder.toString();

    }

    /**
     * excel导入logic
     */
    public String getExcelImpotLogic() {
        StringBuilder stringBuilder = new StringBuilder(512);

        stringBuilder.append(logic_comment + getExcelImport.trim() + logic_begin + newLine);
        stringBuilder.append(leftMiddleBracket + getExcelImport.trim() + point + logic_default + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine);
        stringBuilder.append(logic_ajax_default_status_300 + newLine);
        stringBuilder.append(logic_ajax_default_file_path + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getExcelImport.trim() + point + logic_error_success + rightMiddleBracket + newLine);
        stringBuilder.append(logic_ajax_success_status_200 + newLine);
        stringBuilder.append(logic_content_message_eq + "导入成功！" + newLine + newLine);
        stringBuilder.append(logic_param_file + "=" + logic_relative_file_path + "finish.httl" + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getExcelImport.trim() + point + "1" + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + "文件参数错误！" + newLine);
        stringBuilder.append(logic_param_file + "=" + logic_relative_file_path + "error.httl" + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getExcelImport.trim() + point + "2" + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + "文件不存在！" + newLine);
        stringBuilder.append(logic_param_file + "=" + logic_relative_file_path + "error.httl" + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getExcelImport.trim() + point + "3" + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + "表单类型错误！" + newLine);
        stringBuilder.append(logic_param_file + "=" + logic_relative_file_path + "error.httl" + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getExcelImport.trim() + point + "4" + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + "没有上传任何数据！" + newLine);
        stringBuilder.append(logic_param_file + "=" + logic_relative_file_path + "error.httl" + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getExcelImport.trim() + point + "5" + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + "文件格式不合法！" + newLine);
        stringBuilder.append(logic_param_file + "=" + logic_relative_file_path + "error.httl" + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getExcelImport.trim() + point + "6" + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + "数据大小超出了允许值！" + newLine);
        stringBuilder.append(logic_param_file + "=" + logic_relative_file_path + "error.httl" + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getExcelImport.trim() + point + "7" + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + "数据大小超出了允许值！" + newLine);
        stringBuilder.append(logic_param_file + "=" + logic_relative_file_path + "error.httl" + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getExcelImport.trim() + point + "8" + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + "文件系统错误！" + newLine);
        stringBuilder.append(logic_param_file + "=" + logic_relative_file_path + "error.httl" + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getExcelImport.trim() + point + "9" + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + "无效的请求类型！" + newLine);
        stringBuilder.append(logic_param_file + "=" + logic_relative_file_path + "error.httl" + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getExcelImport.trim() + point + "10" + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + "上传失败！" + newLine);
        stringBuilder.append(logic_param_file + "=" + logic_relative_file_path + "error.httl" + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getExcelImport.trim() + point + "11" + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + "编码不能识别！" + newLine);
        stringBuilder.append(logic_param_file + "=" + logic_relative_file_path + "error.httl" + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getExcelImport.trim() + point + "12" + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + "文件名转码错误！" + newLine);
        stringBuilder.append(logic_param_file + "=" + logic_relative_file_path + "error.httl" + newLine + newLine);


        stringBuilder.append(leftMiddleBracket + getExcelImport.trim() + point + "13" + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + "文件保存错误！" + newLine);
        stringBuilder.append(logic_param_file + "=" + logic_relative_file_path + "error.httl" + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getExcelImport.trim() + point + "14" + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + "数据库保存错误！" + newLine);
        stringBuilder.append(logic_param_file + "=" + logic_relative_file_path + "error.httl" + newLine + newLine);


        stringBuilder.append(leftMiddleBracket + getExcelImport.trim() + point + "15" + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + "上传Session ID错误！" + newLine);
        stringBuilder.append(logic_param_file + "=" + logic_relative_file_path + "error.httl" + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getExcelImport.trim() + point + "16" + rightMiddleBracket + newLine);
        stringBuilder.append(logic_content_message_eq + "无法识别的错误！" + newLine);
        stringBuilder.append(logic_param_file + "=" + logic_relative_file_path + "error.httl" + newLine + newLine);

        stringBuilder.append(leftMiddleBracket + getExcelImport.trim() + point + logic_sys_error + rightMiddleBracket + newLine);
        stringBuilder.append(logic_action_view + newLine);
        stringBuilder.append(logic_ajax_default_status_300 + newLine);
        stringBuilder.append(logic_ajax_sys_error_file_path + newLine);
        stringBuilder.append(logic_comment + getExcelImport.trim() + logic_end);
        return stringBuilder.toString();

    }


    @Override
    public String getFactoryStr() {
        StringBuilder stringBuilder = new StringBuilder(1024);
        String initLogic = getInitLogic();
        String getListByPageLogic = getGetListByPageLogic();
        String showAddLogic = getShowAddLogic();
        String addLogic = getAddLogic();
        String showEditLogic = getShowEditLogic();
        String editLogic = getEditLogic();
        String deleteLogic = getDelteLogic();
        String batchDeleteLogic = getBatchDelteLogic();
        String getDetailLogic = getGetDetailLogic();
        String getExcelImportLogic = getExcelImpotLogic();//导入

        //添加到 stringBuilder
        stringBuilder.append(initLogic + newLine);
        stringBuilder.append(getListByPageLogic + newLine);
        stringBuilder.append(showAddLogic + newLine);
        stringBuilder.append(addLogic + newLine);
        stringBuilder.append(showEditLogic + newLine);
        stringBuilder.append(editLogic + newLine);
        stringBuilder.append(deleteLogic + newLine);
        stringBuilder.append(batchDeleteLogic + newLine);
        stringBuilder.append(getDetailLogic + newLine);
        if (isCreateUploadMethod.equalsIgnoreCase("true")){
            stringBuilder.append(getExcelImportLogic + newLine); //导入
        }


        return stringBuilder.toString();
    }

    /**
     * 生成logic文件
     */
    @Override
    public boolean createFile() throws IOException {
        String content = getFactoryStr();
        String filePath = templateDirPath + File.separator + packageName + File.separator + controllerName + ".logic";
        sop("----------------------------------...即将开始自动生成logic文件...---------------------------------");
        try {
            FileUtil.writeFile(content, filePath);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        sop("----------------------------------...自动生成logic文件完成...---------------------------------");
        return true;
    }
}
