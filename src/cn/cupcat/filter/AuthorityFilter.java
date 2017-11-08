package cn.cupcat.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 同一字符编码
 * @author zxy
 */
public class AuthorityFilter implements Filter {


    /**
     *  过滤用户有没有登录
     * */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        HttpSession session = null;
        if (servletResponse instanceof HttpServletResponse ){
            response = (HttpServletResponse)servletResponse;
        }
        if(servletRequest instanceof HttpServletRequest){
            request = (HttpServletRequest)servletRequest;
            String servletPath = request.getServletPath();
            if (! servletPath.equalsIgnoreCase("/login.do")){
                session = request.getSession();
                Object username = session.getAttribute("username");
                if (username == null || ((String)username).equalsIgnoreCase("admin")){
                    response.sendRedirect("/login.jsp");
                    return;
                }
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
