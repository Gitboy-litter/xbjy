package com.chen.controller;

import com.alibaba.fastjson.JSONObject;
import com.chen.constants.Constant;
import com.chen.entiy.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.controller
 * @Author: ChenZengWen
 * @Description: 所有servlet的基类, 通过封装把service服务逻辑继续处理
 * @Date: 2020/9/22 11:33
 * @Version: 1.0
 */
public class BaseServlet extends HttpServlet {
    public User loginUser=new User();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method service
         * @Author ChenZW
         * @Version 1.0
         * @Param [request, response]
         * @Description 这是一个所有的servlet需要的触发逻辑方法
         * @Return void
         * @Date 2020/9/22 11:43
         *@版权归属于ChenZW
         */
        HttpSession session = request.getSession();
        loginUser = (User) session.getAttribute(Constant.SESSION_LOGIN);
        String uri = request.getRequestURI();
        String[] split = uri.split("/");
        String method = split[split.length - 1];
        Class aClass = this.getClass();
        try {
            Method declaredMethod = aClass.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getParam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method getParam
         * @Author ChenZW
         * @Version 1.0
         * @Param [request, response]
         * @Description 对对象的数据处理封装，把Map对象的键值对转换成想要的格式
         * @Return java.util.Map<java.lang.String   ,   java.lang.String>
         * @Date 2020/9/22 18:34
         *@版权归属于ChenZW
         */
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        HashMap<String, String> returnMap = new HashMap<>();
        for (Map.Entry<String, String[]> entry : entries) {
            String[] value = entry.getValue();
            if (value.length > 1) {
                String string = Arrays.toString(value);
                returnMap.put(entry.getKey(), string);
            } else {
                returnMap.put(entry.getKey(), value[0]);
            }
        }
        return returnMap;
    }

    public void writerObjToString(HttpServletResponse response, Object obj) {
        /**
         * @Method writerObjToString
         * @Author ChenZW
         * @Version  1.0
         * @Param [response, obj]
         * @Description 使用工具对元素进行转化json字符串
         * @Return void
         * @Date 2020/9/22 18:36
         *@版权归属于ChenZW
         */
        try {
            // 统一设置编码
            String jsonString = JSONObject.toJSONString(obj);
            response.setContentType("application/json;charset=utf8");
            response.getWriter().write(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
