package com.chen.controller;

import com.alibaba.fastjson.JSONObject;
import com.chen.constants.Constant;
import com.chen.entiy.User;
import com.chen.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.controller
 * @Author: ChenZengWen
 * @Description: 登陆的servlet
 * @Date: 2020/9/22 11:38
 * @Version: 1.0
 */
@WebServlet("/login/*")
public class LoginServlet extends BaseServlet {
    private UserService userService = new UserService();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method login
         * @Author ChenZW
         * @Version 1.0
         * @Param [request, response]
         * @Description 对登陆进行逻辑处理
         * @Return void
         * @Date 2020/9/22 12:10
         *@版权归属于ChenZW
         */

        //验证登陆
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remeberme = request.getParameter("remeberme");
        User byUsername = userService.findByUsername(username);
        String code = request.getParameter("code");
        Object scode = session.getAttribute(Constant.LOGIN_CODE);
        if (!code.equalsIgnoreCase(scode.toString())){
            response.getWriter().write("验证码错误");
            return;
        }
        if (byUsername == null) {
            response.getWriter().write("用户名不存在");
            return;
        } else if (password.equals(byUsername.getPassword())) {
            if ("1".equals(remeberme)) {
                //勾选是否其他登陆，勾选了就存储cookie
                Cookie cookie = new Cookie(Constant.COOKIE_LOGIN, URLEncoder.encode(JSONObject.toJSONString(byUsername), "utf-8"));
                cookie.setPath("/");
                cookie.setMaxAge(7*24*60*60);
                response.addCookie(cookie);
            }
            //登陆成功后存储session
            session.setAttribute(Constant.SESSION_LOGIN, byUsername);
            response.getWriter().write("success");
        } else {
            response.getWriter().write("用户名或者密码不正确");
        }
    }

    public void selectUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method selectUsername
         * @Author ChenZW
         * @Version 1.0
         * @Param [request, response]
         * @Description 通过ajax调用判断是否存在用户名
         * @Return void
         * @Date 2020/9/22 20:16
         *@版权归属于ChenZW
         */
        String username = request.getParameter("username");
        User byUsername = userService.findByUsername(username);
        if (byUsername != null) {
            response.getWriter().write("exit");
        }
    }

    public void selectEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method selectEmail
         * @Author ChenZW
         * @Version 1.0
         * @Param [request, response]
         * @Description 通过ajax调用判断是否存在邮箱
         * @Return void
         * @Date 2020/9/22 20:17
         *@版权归属于ChenZW
         */
        String email = request.getParameter("email");
        User byEmail = userService.findEmail(email);
        if (byEmail != null) {
            response.getWriter().write("exit");
        }
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(password);
        userService.insert(user);
        response.sendRedirect("/html/login/login.jsp");
    }
}
