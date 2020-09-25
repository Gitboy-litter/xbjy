package com.chen.controller;

import com.alibaba.fastjson.JSONObject;
import com.chen.dao.BaseDao;
import com.chen.entiy.Menu;
import com.chen.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.controller
 * @Author: ChenZengWen
 * @Description: 菜单的控制层
 * @Date: 2020/9/22 17:35
 * @Version: 1.0
 */
@WebServlet("/menu/*")
public class MenuServlet extends BaseServlet {
    private MenuService menuServic=new MenuService();
    public void menuListAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method menuListAll
         * @Author ChenZW
         * @Version 1.0
         * @Param [request, response]
         * @Description 对登陆进行逻辑处理
         * @Return void
         * @Date 2020/9/22 12:10
         *@版权归属于ChenZW
         */
        //得到所有的菜单对象
        List<Menu> menus = menuServic.menusListALL();
        //获取一级菜单
        List<Menu> parent = menus.stream().filter(n -> {
            return n.getType().equals("1");
        }).collect(Collectors.toList());
        //获取二级菜单
        List<Menu> son = menus.stream().filter(n -> {
            return n.getType().equals("2");
        }).collect(Collectors.toList());
        //把菜单对象集合存储在map里
        Map<String,List<Menu>> menusMap=new HashMap<>();
        menusMap.put("Parent",parent);
        menusMap.put("Son",son);
        //转化json
        String string = JSONObject.toJSONString(menusMap);
        response.getWriter().write(string);
    }
}
