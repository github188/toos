package cn.cupcat.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 外部访问地址过滤器,进行转换
 */
@WebFilter(filterName="MyPayFilter",urlPatterns="*.do")
public class MyPayFilter implements Filter {

  public MyPayFilter() {

  }


  public void destroy() {

  }


  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    try{

        String requestURI = req.getRequestURI();
    	String params[] = requestURI.split("/");
        String newUrl = "/" + params[1] + "/login.jsp?m=" + params[2] + "&c=" + params[3] + "&a=" + params[4].replace(".do", "");
        if (params[4].equals("payNotifyUrl")){
            String inputLine;
            String notifyXml = "";
            try {
                while ((inputLine = request.getReader().readLine()) != null){
                    notifyXml += inputLine;
                }
                request.getReader().close();
            } catch (Exception e) {
                System.out.println("xml获取失败：" + e);
                e.printStackTrace();
            }
            newUrl+="&wxpay="+notifyXml;
        }

        StringBuffer url = req.getRequestURL();
        String tempContextUrl = url.delete(url.length() - req.getRequestURI().length(), url.length()).toString();
//        System.out.println("转发路径==="+tempContextUrl+newUrl);
        request.getRequestDispatcher(tempContextUrl+newUrl).forward(request, response);
    	return;
    }catch (Exception e) {
    	chain.doFilter(request, response);
	}
  }


  public void init(FilterConfig fConfig) throws ServletException {
  }

}