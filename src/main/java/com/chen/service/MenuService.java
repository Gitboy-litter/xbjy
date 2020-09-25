package com.chen.service;

import com.chen.dao.MenuDao;
import com.chen.entiy.Menu;
import java.util.List;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.service
 * @Author: ChenZengWen
 * @Description: 菜单的service层
 * @Date: 2020/9/22 17:30
 * @Version: 1.0
 */
public class MenuService {
    private MenuDao menuDao = new MenuDao();

    public List<Menu> menusListALL() {
        /**
         * @Method menusListALL
         * @Author ChenZW
         * @Version  1.0
         * @Param []
         * @Description 调用dao层的方法
         * @Return java.util.List<com.chen.entiy.Menu>
         * @Date 2020/9/22 17:32
         *@版权归属于ChenZW
         */
        return menuDao.menusListALL();
    }
}
