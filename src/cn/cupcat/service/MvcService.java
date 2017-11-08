package cn.cupcat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author zxy
 */
public class MvcService {


    /**
     *  登录方法
     * */
    public boolean login(HttpServletRequest req, HttpServletResponse resp){
        boolean flag = false;
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //登录成功
        if (null != username &&  username.trim().equalsIgnoreCase("admin") && null != password && password.trim().equalsIgnoreCase("123456")){
            HttpSession session = req.getSession();
            session.setAttribute("username",username);
            flag = true;

        }
        return flag;
    }

    /**
     *  获取所有的表集合
     * */
    public ArrayList<HashMap<String,Object>> getTableList(){
        ArrayList<HashMap<String,Object>> result = null;

        return result;
    }
    /**
     * 获取指定表的字段集合
     * */
    public ArrayList<HashMap<String,Object>> getColumnList(String tableName){
        ArrayList<HashMap<String,Object>> result = null;

        return result;
    }

    /**
     *  生成代码
     * */
    public boolean generateCode(){
        boolean flag = true;

        return flag;
    }

    /**
     *  下载生成的代码
     * */
    public boolean downloadFile(){
        boolean flag = true;

        return flag;
    }


}
