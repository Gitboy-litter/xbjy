package com.chen.controller;

import com.alibaba.fastjson.JSONObject;
import com.chen.constants.Constant;
import com.chen.entiy.Dept;
import com.chen.entiy.Page;
import com.chen.entiy.User;
import com.chen.entiy.Userfocus;
import com.chen.service.UserFocusService;
import com.chen.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.controller
 * @Author: ChenZengWen
 * @Description: user的servlet类, 对用户进行实现
 * @Date: 2020/9/22 11:36
 * @Version: 1.0
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService service = new UserService();
    private UserFocusService userFocusService = new UserFocusService();

    public void userlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method list
         * @Author ChenZW
         * @Version 1.0
         * @Param [request, response]
         * @Description 查询所有的用户数据
         * @Return void
         * @Date 2020/9/22 11:46
         *@版权归属于ChenZW
         */
        //分页功能
        Page<User> page = new Page<>();
        //模糊查询名字
        String name = request.getParameter("name");
        //获取页面
        String pageCurrent = request.getParameter("pageCurrent");
        if (pageCurrent != null && pageCurrent != "") {
            page.setPageCurren(Integer.valueOf(pageCurrent));
        }
        if (name == null) {
            name = "";
        }
        //得到查询记录
        Integer userCount = service.findUserCount(name);
        page.setPagecount(userCount);
        page.setPageTotal(page.getPageTotal());

        //查看用户关注
        User loginUser = (User) request.getSession().getAttribute(Constant.SESSION_LOGIN);
        List<Userfocus> userFocus = userFocusService.findUserFocus(loginUser.getId());

        List<User> user = service.findUser(name, page);
        page.setData(user);
        request.setAttribute("page", page);
        request.setAttribute("name", name);
        request.setAttribute("userfoucs", userFocus);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/html/user/UserManger.jsp").forward(request, response);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method delete
         * @Author ChenZW
         * @Version 1.0
         * @Param [request, response]
         * @Description 删除用户
         * @Return void
         * @Date 2020/9/24 19:37
         *@版权归属于ChenZW
         */
        //根据id删除
        String id = request.getParameter("id");
        service.deletbyUsername(Integer.valueOf(id));
        request.getRequestDispatcher("/user/userlist").forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method update
         * @Author ChenZW
         * @Version 1.0
         * @Param [request, response]
         * @Description 更新用户信息
         * @Return void
         * @Date 2020/9/24 19:38
         *@版权归属于ChenZW
         */
        //根据id回显信息
        String id = request.getParameter("id");
        User byId = service.findById(Integer.valueOf(id));
        request.setAttribute("updateuser", byId);
        request.getRequestDispatcher("/html/user/UserUpdate.jsp").forward(request, response);
    }

    public void sdept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method sdept
         * @Author ChenZW
         * @Version 1.0
         * @Param [request, response]
         * @Description 得到所有的部门，方便选择
         * @Return void
         * @Date 2020/9/24 19:39
         *@版权归属于ChenZW
         */
        List<Dept> department = service.findDepartment();
        response.getWriter().write(JSONObject.toJSONString(department));
    }

    public void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method insertUser
         * @Author ChenZW
         * @Version 1.0
         * @Param [request, response]
         * @Description 添加用户
         * @Return void
         * @Date 2020/9/24 19:39
         *@版权归属于ChenZW
         */
        try {
            User user = new User();
            BeanUtils.populate(user, request.getParameterMap());
            service.insertUser(user);
            request.getRequestDispatcher("/user/userlist").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectUserById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method selectUserById
         * @Author ChenZW
         * @Version 1.0
         * @Param [request, response]
         * @Description 根据id来查询用户
         * @Return void
         * @Date 2020/9/24 19:39
         *@版权归属于ChenZW
         */
        String id = request.getParameter("id");
        User byId = service.findById(Integer.valueOf(id));
        request.setAttribute("User", byId);
        request.getRequestDispatcher("/html/user/userDetail.jsp").forward(request, response);
    }

    public void updateuser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method updateuser
         * @Author ChenZW
         * @Version 1.0
         * @Param [request, response]
         * @Description 更新用户
         * @Return void
         * @Date 2020/9/24 19:40
         *@版权归属于ChenZW
         */
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
            service.updateUser(user);
            request.getRequestDispatcher("/user/userlist").forward(request, response);
        } catch (Exception e) {
        }
    }

    public void findUserFocus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method findUserFocus
         * @Author ChenZW
         * @Version  1.0
         * @Param [request, response]
         * @Description 查看用户关注
         * @Return void
         * @Date 2020/9/25 17:07
         *@版权归属于ChenZW
         */
        //分页功能
        Page<User> page = new Page<>();
        //模糊查询名字
        String userName = request.getParameter("username");
        //获取页面
        String pageCurrent = request.getParameter("pageCurrent");
        if (pageCurrent != null && pageCurrent != "") {
            page.setPageCurren(Integer.valueOf(pageCurrent));
        }
        if (userName == null) {
            userName = "";
        }
        Integer loginUserId = loginUser.getId();
        //得到查询记录
        Integer userCount = userFocusService.findUserFocusCount(loginUser.getId(),userName);
        page.setPagecount(userCount);
        page.setPageTotal(page.getPageTotal());

        List<User> userFocusList = userFocusService.findUserFocusList(loginUser.getId(),userName,page);
        page.setData(userFocusList);
        request.setAttribute("page", page);
        request.setAttribute("username", userName);
        request.getRequestDispatcher("/html/user/myfocus.jsp").forward(request, response);
    }

    public void deleteFocusById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method deleteFocusById
         * @Author ChenZW
         * @Version  1.0
         * @Param [request, response]
         * @Description 取消关注
         * @Return void
         * @Date 2020/9/25 18:07
         *@版权归属于ChenZW
         */
        String id = request.getParameter("id");
        userFocusService.deleteFocusById(Integer.valueOf(id));
        request.getRequestDispatcher("/user/findUserFocus").forward(request, response);
    }

    public void focusUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method deleteFocusById
         * @Author ChenZW
         * @Version  1.0
         * @Param [request, response]
         * @Description 关注用户
         * @Return void
         * @Date 2020/9/25 18:07
         *@版权归属于ChenZW
         */
        String id = request.getParameter("id");
        if (Integer.valueOf(id).equals(loginUser.getId())){
            response.getWriter().write("不可以关注自己");
        }else {
            userFocusService.insertFocus(Integer.valueOf(id),loginUser.getId());
            response.getWriter().write("关注成功");
        }
    }
}
