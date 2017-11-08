package cn.cupcat.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 同一字符编码
 * @author zxy
 */
public class EncodeFilter implements Filter {

    /**
     *  过滤编码，同一为UTF-8
     * */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest){
            HttpServletRequest request = (HttpServletRequest)servletRequest;
            //设置编码，防止后台得到中文乱码
            request.setCharacterEncoding("UTF-8");
        }
        if (servletResponse instanceof HttpServletResponse){
            HttpServletResponse response = (HttpServletResponse)servletResponse;
            //设置编码，防止返回客户端乱码
            response.setCharacterEncoding("UTF-8");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
