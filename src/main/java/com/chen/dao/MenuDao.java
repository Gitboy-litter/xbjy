package com.chen.dao;

import com.chen.entiy.Menu;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.dao
 * @Author: ChenZengWen
 * @Description: 菜单的Dao层
 * @Date: 2020/9/22 17:16
 * @Version: 1.0
 */
public class MenuDao {
    public List<Menu> menusListALL() {
        /**
         * @Method menusListALL
         * @Author ChenZW
         * @Version  1.0
         * @Param []
         * @Description 菜单负责查找所有的菜单数据
         * @Return java.util.List<com.chen.entiy.Menu>
         * @Date 2020/9/22 17:33
         *@版权归属于ChenZW
         */
        String sql = "select * from menu";
        return BaseDao.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Menu.class));
    }
}
