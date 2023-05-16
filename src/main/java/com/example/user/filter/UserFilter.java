package com.example.user.filter;

import com.example.user.pojo.entity.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "UserFilter", urlPatterns = {"admin.html","search.html","add.html"})
public class UserFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        //获取session信息，检查是否登录
        User user = (User) session.getAttribute("user");
        if (user == null) {
            //未登录过，返回登录页
            resp.sendRedirect("login.html");
        } else {
            //说明已登录
            filterChain.doFilter(req, resp);
        }

    }

    @Override
    public void destroy() {

    }
}
