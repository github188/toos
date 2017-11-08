package cn.cupcat.controller;

import cn.cupcat.model.ResponseResult;
import cn.cupcat.service.MvcService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zxy
 */
public class MvcController extends HttpServlet {
    /**
     * service对象
     */
    private MvcService mvcService = new MvcService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();

        if (servletPath.equalsIgnoreCase("/login.do")) {
            boolean flag = mvcService.login(req, resp);
            ResponseResult responseResult = new ResponseResult();
            //登录成功
            if (flag) {
                responseResult.setErrorCode(200);
                responseResult.setErrorMessage("登录成功！");
            } else {
                responseResult.setErrorCode(300);
                responseResult.setErrorMessage("登录失败，用户名或者密码错误！");
            }
            writeResponse(resp, responseResult);

        } else if (servletPath.equalsIgnoreCase("/getTableList")) {


        } else if (servletPath.equalsIgnoreCase("")) {

        } else if (servletPath.equalsIgnoreCase("")) {

        } else if (servletPath.equalsIgnoreCase("")) {

        } else {

        }

    }

    /**
     * 响应ajax请求
     */
    public void writeResponse(HttpServletResponse resp, Object object) {
        resp.setContentType("application/json;charset=utf-8");
        try {
            JSONObject jsonObject = JSONObject.fromObject(object);
            PrintWriter writer = resp.getWriter();
            writer.print(jsonObject);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
