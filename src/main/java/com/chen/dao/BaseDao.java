package com.chen.dao;

import com.chen.Utils.DbUtil;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.dao
 * @Author: ChenZengWen
 * @Description: 描述
 * @Date: 2020/9/22 17:25
 * @Version: 1.0
 */
public class BaseDao  {
    public static JdbcTemplate jdbcTemplate=new JdbcTemplate(DbUtil.getDataSource());
}
