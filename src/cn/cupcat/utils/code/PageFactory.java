package cn.cupcat.utils.code;

import cn.cupcat.utils.file.FileUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * 功能：本类主要是用来生成httl文件;包括前台验证
 *
 * @author zxy
 * @version 1.1
 * @since 2017年10月20日15:53:58
 */
public class PageFactory extends Factory {

    public static String newLine = Factory.newLine;

    public String init_page_name = Factory.templateDirPath + "/" + logic_skip_page_relative_file_path + init.trim() + point + "httl";
    public String add_page_name = Factory.templateDirPath + "/" + logic_skip_page_relative_file_path + add.trim() + point + "httl";
    public String edit_page_name = Factory.templateDirPath + "/" + logic_skip_page_relative_file_path + edit.trim() + point + "httl";
    public String detail_page_name = Factory.templateDirPath + "/" + logic_skip_page_relative_file_path + "detail" + point + "httl";
    public String json_data_path = Factory.templateDirPath + "/json/json.httl";

    private HtmlComponent htmlComponent = new HtmlComponent();


    /**
     * 获取init页面字符串
     */
    public String getInitPageStr() {
        StringBuilder stringBuilder = new StringBuilder(2048);
        stringBuilder.append(getPageHeadStr());
        stringBuilder.append("<div style=\"height:25px;margin-left:-4px;text-indent:4px;padding-top:1px;border:1px #95B8E7 solid;font-weight:700;\">" + newLine);
        stringBuilder.append(itemName + "维护" + newLine);
        stringBuilder.append(" </div>" + newLine);
        stringBuilder.append(" <div position=\"top\" style=\"border-width:0 0 0 0;\">" + newLine);

        stringBuilder.append("<form id=\"queryForm\" style=\"padding-top:5px;padding-bottom:5px;\">" + newLine);
        stringBuilder.append("<table>" + newLine);
        stringBuilder.append(" <tr>" + newLine);
        stringBuilder.append(" <td>搜索条件：</td>" + newLine);
        stringBuilder.append(" <td>" + newLine);
        stringBuilder.append("<input name=\"\" placeholder=\"请输入搜索条件\" style=\"width:160px;\" type=\"text\" onkeydown=\"javascript:if(event.keyCode==13){template.query();};\"/>" + newLine);
        stringBuilder.append("</td>" + newLine);
        stringBuilder.append("<td style=\"text-indent:1em;\">" + newLine);
        stringBuilder.append(" <button type=\"button\" id=\"query\" class=\"primary button\" style=\"padding-left: 5px;\"><span" + newLine);
        stringBuilder.append(" class=\"icon_find\" style=\"cursor: default; font-size: 14px;\">查询</span></button>" + newLine);
        stringBuilder.append("</td>" + newLine);
        stringBuilder.append("<td style=\"text-indent:1em;\">" + newLine);
        stringBuilder.append(" <button type=\"button\" id=\"resetQuery\" class=\"button\" style=\"padding-left: 5px;\"><span" + newLine);
        stringBuilder.append(" class=\"icon_clear\" style=\"cursor: default; font-size: 14px;\">重置</span></button>" + newLine);
        stringBuilder.append("</td>" + newLine);
        stringBuilder.append(" </tr>" + newLine);
        stringBuilder.append(" </table>" + newLine);
        stringBuilder.append(" </form>" + newLine);
        stringBuilder.append("</div>" + newLine);
        stringBuilder.append("<div position=\"center\" id=\"\">" + newLine);
        stringBuilder.append(" <div id=\"grid\" style=\"border-width:1px 1px 1px 1px\"></div>" + newLine);
        stringBuilder.append("<iframe id=\"download_a\" src=\"about:blank\" style=\"display:none;\"></iframe>" + newLine);
        stringBuilder.append("</div>" + newLine);
        stringBuilder.append("${include(\"/public/foot.httl\")}" + newLine);
        stringBuilder.append("<script type=\"text/javascript\">" + newLine);

        stringBuilder.append("template.setItemName(\"" + itemName + "\");" + newLine);
        stringBuilder.append("template.setDeleteUrl(" + "\"?m=" + packageName + "&c=" + controllerName + "&a=" + delete.trim() + "\");" + newLine);

        stringBuilder.append("template.setBatchDeleteUrl(" + "\"?m=" + packageName + "&c=" + controllerName + "&a=" + batchDelete.trim() + "\");" + newLine);
        stringBuilder.append("template.setShowAddUrl(" + "\"?m=" + packageName + "&c=" + controllerName + "&a=" + showAdd.trim() + "\");" + newLine);
        stringBuilder.append("template.setAddUrl(" + "\"?m=" + packageName + "&c=" + controllerName + "&a=" + add.trim() + "\");" + newLine);
        stringBuilder.append("template.setShowEditUrl(" + "\"?m=" + packageName + "&c=" + controllerName + "&a=" + showEdit.trim() + "\");" + newLine);
        stringBuilder.append("template.setEditUrl(" + "\"?m=" + packageName + "&c=" + controllerName + "&a=" + edit.trim() + "\");" + newLine);


        stringBuilder.append("function initComplete() { " + newLine);
        stringBuilder.append(" initQUIGird(\"#grid\",{ " + newLine);
        stringBuilder.append(" columns: [ " + newLine);
        List<HashMap<String, Object>> data = null;
        try {
            data = columnDataList != null ? columnDataList : getColumnData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HashMap<String, Object> columnMap;
        int dataLength = data.size();
        double width = 85 / dataLength;
        String columnName;
        String remark;
        boolean hasDate = false;
        for (int i = 0; i < dataLength; i++) {
            columnMap = data.get(i);
            columnName = (String) columnMap.get("columnName");
            remark = (remark = (String) columnMap.get("remarks")) == null || remark.equalsIgnoreCase("") ? columnName : remark;
            String[] splitClassNameArray = ((String) columnMap.get("columnClassName")).split("\\."); //这里必须加上 \\ 转移符
            //除了byte、 short、int、long float、double 都默认为String类型
            String realColumnClassName = splitClassNameArray[splitClassNameArray.length - 1].trim();
            String columnClassName = realColumnClassName.equals(ModuleFactory.sInteger.trim()) ? ModuleFactory.sInt : ModuleFactory.getColumnClassType(realColumnClassName);// 对应数据类型的类

            if (realColumnClassName.trim().equalsIgnoreCase(columnClassName.trim()) && columnClassName.trim().equalsIgnoreCase("String")) { //真String类型
                stringBuilder.append(" {display: '" + remark + "', name: '" + columnName + "', align: 'left', width: '" + width + "%',showTitle : true}," + newLine);
            } else if (realColumnClassName.toLowerCase().equalsIgnoreCase(columnClassName.trim().toLowerCase()) || columnClassName.trim().equalsIgnoreCase("int")) {
                stringBuilder.append(" {display: '" + remark + "', name: '" + columnName + "', align: 'center', width: '" + width + "%',showTitle : true}," + newLine);
            } else { //日期类型
                stringBuilder.append(" {display: '" + remark + "', name: '" + columnName + "', align: 'center', width: '" + width + "%',showTitle : true,type:'date'}," + newLine);
                hasDate = true;
            }
        }

        stringBuilder.append(" {" + newLine);
        stringBuilder.append(" display: '操作', align: 'center', width: '10%', isSort: false, isAllowHide: false," + newLine);
        stringBuilder.append(" render: function (rowdata, rowindex, value, column) {" + newLine);
        stringBuilder.append("return '<div class=\"grid_opp_container\">'" + newLine);
        stringBuilder.append("  + '<span class=\"grid_opp_edit\" data-id=\"'+rowdata.ID+'\" title=\"修改\"  >修改</span>'" + newLine);
        stringBuilder.append("+ '<span class=\"grid_opp_delete\" title=\"删除\" data-id=\"'+rowdata.ID+'\">删除</span>'" + newLine);
        stringBuilder.append("+ '</div>';" + newLine);
        stringBuilder.append("  }" + newLine);
        stringBuilder.append(" }" + newLine);
        stringBuilder.append(" ]," + newLine);
        stringBuilder.append(" url: '?m=" + packageName + "&c=" + controllerName + "&a=getListByPage'," + newLine);
        stringBuilder.append(" toolbar: {" + newLine);
        stringBuilder.append("items: [" + newLine);
        stringBuilder.append(" {text: '新增', click: template.showAdd, iconClass: 'icon_add'}," + newLine);
        stringBuilder.append(" {line: true}," + newLine);
        stringBuilder.append(" {text: '批量删除', click: template.batchDelete, iconClass: 'icon_delete'}," + newLine);
        stringBuilder.append("{line: true}" + newLine);
        stringBuilder.append("]" + newLine);
        stringBuilder.append(" }" + newLine);
        stringBuilder.append("  },template);" + newLine);
        if (hasDate) { //如果有日期，格式化日期
            stringBuilder.append("  $.quiDefaults.Grid.formatters['date'] = function (value, column) { " + newLine);
            stringBuilder.append("  return new Date(value.time).Format(\"yyyy-MM-dd\"); " + newLine);
            stringBuilder.append("  }" + newLine);
        }
        stringBuilder.append(" }" + newLine);
        stringBuilder.append("</script>" + newLine);
        stringBuilder.append(getPageFootStr());
        return stringBuilder.toString();
    }

    /**
     * 添加界面
     */
    public String getAddPageStr() {
        return getCommonPageStr("add");
    }

    /**
     * 修改界面
     */
    public String getEditPageStr() {
        return getCommonPageStr("edit");
    }

    /**
     * 详情界面
     */
    public String getDetailPageStr() {
        return getCommonPageStr("detail");
    }


    public String getCommonPageStr(String type) {
        String formId; //表单id
        String httl = "";
        if (type.equals("add")) {
            formId = "addForm";
        } else if (type.equals("edit")) {
            formId = "editForm";
            httl = "#set(HashMap detail)";
        } else {
            formId = "detailForm";
        }


        StringBuilder stringBuilder = new StringBuilder(2000);
        stringBuilder.append(getPageHeadStr());
        stringBuilder.append(httl + newLine);
        stringBuilder.append("<div style=\"overflow-y:auto\">" + newLine);
        stringBuilder.append(" <form id=\"" + formId + "\" >" + newLine);
        stringBuilder.append(" <div class=\"box\">" + newLine);
        stringBuilder.append(" <table style=\"margin-top:30px;margin-left:30px;\">" + newLine);


        List<HashMap<String, Object>> data = null;
        try {
            data = columnDataList != null ? columnDataList : getColumnData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HashMap<String, Object> columnMap;
        int dataLength = data.size();
        double width = 90 / dataLength;
        String columnName;
        String remark;
        for (int i = 0; i < dataLength; i++) {
            columnMap = data.get(i);
            columnName = (String) columnMap.get("columnName");
            String column_value = type.equals("add") ? "" : "${detail." + columnName + "}";


            remark = (remark = (String) columnMap.get("remarks")) == null || remark.equalsIgnoreCase("") ? columnName : remark;
            String[] splitClassNameArray = ((String) columnMap.get("columnClassName")).split("\\."); //这里必须加上 \\ 转移符

            int columnDisplaySize = (Integer) columnMap.get("columnDisplaySize");// 在数据库中类型的最大字符个数
            int isNullable = (Integer) columnMap.get("isNullable"); // 是否为空
            boolean isRequired = isNullable == 0 ? true : false;
            //除了byte、 short、int、long float、double 都默认为String类型
            String realColumnClassName = splitClassNameArray[splitClassNameArray.length - 1].trim();
            String columnClassName = realColumnClassName.equals(ModuleFactory.sInteger.trim()) ? ModuleFactory.sInt : ModuleFactory.getColumnClassType(realColumnClassName);// 对应数据类型的类

            if (columnName.equalsIgnoreCase(pk_colunm_name)) { //如果是主键
                if (type.equals("edit") || type.equals("detail")) {
                    stringBuilder.append(htmlComponent.getInputComponent("hidden", columnName, column_value, null, true, null, null));
                }
            } else {
                stringBuilder.append("<tr>" + newLine);
                stringBuilder.append(" <td style=\"text-align:right;width:100px;\">" + remark + "：" + " </td>" + newLine);
                stringBuilder.append("<td style=\"text-align:left\" >" + newLine);


                if (realColumnClassName.trim().equalsIgnoreCase(columnClassName.trim()) && columnClassName.trim().equalsIgnoreCase("String")) { //真String类型
                    stringBuilder.append(htmlComponent.getInputComponent("text", columnName, column_value, remark, isRequired, isRequired ? "1" : "0", String.valueOf(columnDisplaySize)));
                    if (isRequired) {
                        stringBuilder.append(htmlComponent.getNeedStart());
                    }
                    stringBuilder.append(newLine);

                } else if (realColumnClassName.toLowerCase().equalsIgnoreCase(columnClassName.trim().toLowerCase()) || columnClassName.trim().equalsIgnoreCase("int")) {
                    stringBuilder.append(htmlComponent.getSelectComponent(columnName, isRequired) + newLine);
                } else { //日期类型
                    stringBuilder.append(htmlComponent.getDateComponent(columnName, column_value) + htmlComponent.getNeedStart() + newLine);
                }

            }
            stringBuilder.append("</td>" + newLine);
            stringBuilder.append("</tr>" + newLine);
        }

        stringBuilder.append("</table>" + newLine);
        stringBuilder.append("</div>" + newLine);
        stringBuilder.append("</form>" + newLine);
        stringBuilder.append("</div>" + newLine);
        stringBuilder.append(" ${include(\"/public/foot.httl\")}" + newLine);
        stringBuilder.append("<script src=\"/public/initForm.js\"></script>" + newLine);
        if (type.equals("detail")) {
            stringBuilder.append("<script>$('form :input,select,textarea').prop('disabled',true).prop('readonly',true);</script>" + newLine);
        }
        String disabledStr = "";

        stringBuilder.append(getPageFootStr());
        return stringBuilder.toString();
    }


    public void createInitFile() throws IOException {
        String content = getInitPageStr();
        FileUtil.writeFile(content, init_page_name);//写文件
    }

    public void createDetailFile() throws IOException {
        String content = getDetailPageStr();
        FileUtil.writeFile(content, detail_page_name);//写文件
    }

    public void createAddFile() throws IOException {
        String content = getAddPageStr();
        FileUtil.writeFile(content, add_page_name);//写文件
    }

    public void createEditFile() throws IOException {
        String content = getEditPageStr();
        FileUtil.writeFile(content, edit_page_name);//写文件
    }

    /**
     * 获得页面头部
     */
    public static String getPageHeadStr() {

        StringBuilder stringBuilder = new StringBuilder(200);
        stringBuilder.append("<!DOCTYPE html >" + newLine);
        stringBuilder.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">" + newLine);
        stringBuilder.append("<head>" + newLine);
        stringBuilder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>" + newLine);
        stringBuilder.append("<title></title>" + newLine);
        stringBuilder.append(" ${include(\"/public/head.httl\")}" + newLine);
        stringBuilder.append("</head>" + newLine);
        stringBuilder.append("<body>" + newLine);
        return stringBuilder.toString();
    }

    /**
     * 获得页面头部
     */
    public static String getPageFootStr() {
        StringBuilder stringBuilder = new StringBuilder(50);
        stringBuilder.append("</body>" + newLine);
        stringBuilder.append("" + newLine);
        stringBuilder.append("</html>" + newLine);
        return stringBuilder.toString();
    }

    /**
     * 创建公用json文件
     */
    public boolean createJSONFile() {
        boolean b = true;
        String content = "$!{jsonData}" + newLine;
        try {
            FileUtil.writeFile(content, json_data_path);
        } catch (IOException e) {
            e.printStackTrace();
            b = false;
        }
        return b;
    }


    class HtmlComponent {

        public String componentWidth = "200px";

        public HtmlComponent(String componentWidth) {
            this.componentWidth = componentWidth;
        }

        public HtmlComponent() {
        }


        /**
         * input表单元素
         */
        public String getInputComponent(String type, String name, String value, String placeholder, boolean isRequired, String min, String max) {
            String required = isRequired ? "required," : "";
            placeholder = placeholder == null || placeholder.trim().equalsIgnoreCase("") ? "" : "请输入" + placeholder;
            value = value == null || value.trim().equalsIgnoreCase("") ? "" : value;
            String str = "";
            if (type != null) {
                if (type.equalsIgnoreCase("text")) {
                    str = "<input type=\"" + type + "\" value=\"" + value + "\"  placeholder=\"" + placeholder + "\" name=\"" + name + "\" id=\"" + name + "\" style=\"width:" + componentWidth + ";\"" + " class=\"validate[" + required + "length[" + min + "," + max + "]\"/>";
                } else if (type.equalsIgnoreCase("hidden")) {
                    str = "<input type=\"" + type + "\" value=\"" + value + "\"   name=\"" + name + "\" id=\"" + name + "\"  " + "/>";
                } else if (type.equalsIgnoreCase("checkbox")) {
                    str = "<input type=\"" + type + "\" value=\"" + value + "\"   name=\"" + name + "\"  style=\"width:" + componentWidth + ";\"" + " class=\"validate[minCheckbox[" + min + "],maxCheckbox[" + max + "]\"/>";
                } else if (type.equalsIgnoreCase("radio")) {
                    str = "<input type=\"" + type + "\" value=\"" + value + "\"   name=\"" + name + "\"  style=\"width:" + componentWidth + ";\"" + " class=\"validate[required] radio\"/>";
                } else if (type.equalsIgnoreCase("button")) {
                    str = "<input type=\"" + type + "\" value=\"" + value + "\"   name=\"" + name + "\" id=\"" + name + "\"  style=\"width:" + componentWidth + ";\"" + "/>";
                } else {
                    str = "<input type=\"" + type + "\" value=\"" + value + "\"  placeholder=\"" + placeholder + "\" name=\"" + name + "\" id=\"" + name + "\" style=\"width:" + componentWidth + ";\"" + " class=\"validate[" + required + "length[" + min + "," + max + "]\"/>";
                }
            } else { //默认为text
                str = "<input type=\"" + type + "\" value=\"" + value + "\"  placeholder=\"" + placeholder + "\" name=\"" + name + "\" id=\"" + name + "\" style=\"width:" + componentWidth + ";\"" + " class=\"validate[" + required + "length[" + min + "," + max + "]\"/>";
            }
            return str;
        }

        /**
         * select表单元素
         */
        public String getSelectComponent(String name, boolean isRequired) {
            String required = isRequired ? "required" : "";

            String str = "<select class=\"validate[" + required + "]\" selWidth=\"" + componentWidth.substring(0, componentWidth.length() - 2) + "\" name=\"" + name + "\" id=\"" + name + "\">";
            str += "<option value=\"\" selected>请选择</option>" + newLine;
            str += "</select>" + newLine;
            return str;
        }

        /**
         * 得到日期组件
         */
        public String getDateComponent(String name, String value) {
            String placeholder = "请选择日期";
            value = !value.equals("") ? value : "";
            String str = "<input value='" + value + "' class=\"date validate[required,custom[date]]\"  placeholder=\"" + placeholder + "\" type=\"text\"  name=\"" + name + "\" id=\"" + name + "\" style=\"width:" + componentWidth + ";\" />  ";
            return str;
        }

        /**
         * 获取div模仿的textarea  //TODO 未实现
         */
        public String getDivImitateTextarea() {

            return "";
        }

        /**
         * 获取textarea  //TODO 未实现
         */
        public String getTextarea() {
            return "";
        }

        /**
         * 获得必填的* 表示
         */
        public String getNeedStart() {
            return "<span class=\"star\">*</span>";
        }

    }

    @Override
    public String getFactoryStr() throws IOException, SQLException {
        return null;
    }

    @Override
    public boolean createFile() {
        boolean b = true;
        sop("----------------------------------...即将开始自动生成页面文件...---------------------------------");
        try {
            createJSONFile();
            createInitFile();
            createAddFile();
            createEditFile();
            createDetailFile();
        } catch (IOException e) {
            e.printStackTrace();
            sop("----------------------------------...自动生成页面出错，请查看...---------------------------------");
            b = false;
        }
        sop("----------------------------------...自动生成页面文件完成...---------------------------------");
        return b;
    }


}
