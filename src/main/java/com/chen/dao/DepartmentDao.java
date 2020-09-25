package com.chen.dao;

import com.chen.entiy.Dept;
import com.chen.entiy.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.dao
 * @Author: ChenZengWen
 * @Description: 描述
 * @Date: 2020/9/23 17:59
 * @Version: 1.0
 */
public class DepartmentDao extends BaseDao {
    public List<Dept> findDepartment() {
        String sql = "select dept_name from dept";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Dept.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Dept findDepartmentById(Integer id) {
        String sql = "select name from dept where id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Dept.class),id);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
