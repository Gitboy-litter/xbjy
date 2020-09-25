package com.chen.filter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.chen.constants.Constant;
import com.chen.entiy.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.filter
 * @Author: ChenZengWen
 * @Description: 过滤器，对控制层代码进行处理
 * @Date: 2020/9/22 17:54
 * @Version: 1.0
 */
@WebFilter("/*")
public class ServletFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        if (uri.endsWith("login.jsp")||uri.endsWith("/")) {
            //判断是否是7天免登陆，如果cookie有值就把值存储在session中
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String name = cookie.getName();
                    if (Constant.COOKIE_LOGIN.equals(name)) {
                        String value = cookie.getValue();
                        value = URLDecoder.decode(value, "utf-8");
                        User user = JSONObject.parseObject(value, new TypeReference<User>() {
                        });
                        session.setAttribute(Constant.SESSION_LOGIN, user);
                        request.getRequestDispatcher("/html/user/home.jsp").forward(request, response);
                    }
                }
            }
        } else if (uri.endsWith("js") || uri.endsWith("login") || uri.endsWith("register.jsp") || uri.endsWith("register") || uri.endsWith("css")|| uri.endsWith("map")||uri.endsWith("verifyCode")) {

        } else {
            //判断session是否有值，非法登陆就跳首页
            Object attribute = session.getAttribute(Constant.SESSION_LOGIN);
            if (attribute == null) {
                request.getRequestDispatcher("/html/login/login.jsp").forward(request, response);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
