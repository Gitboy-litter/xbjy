package com.chen.dao;

import com.chen.entiy.Dept;
import com.chen.entiy.Page;
import com.chen.entiy.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.dao
 * @Author: ChenZengWen
 * @Description: UserDao层，作为连接使用mysql
 * @Date: 2020/9/22 10:33
 * @Version: 1.0
 */
public class UserDao {
    public User findByUsername(String username) {
        String sql = "select * from user where username=?";
        try {
            return BaseDao.jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User findEmail(String email) {
        String sql = "select email from user where email=?";
        try {
            return BaseDao.jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
        } catch (DataAccessException e) {
//            e.printStackTrace();
            return null;
        }
    }

    public List<Dept> findDepartment() {
        String sql = "select * from dept";
        return BaseDao.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Dept.class));
    }

    public void insert(User user) {
        BaseDao.jdbcTemplate.update("insert into user values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getQqOpenid(),
                user.getWxOpenid(),
                user.getRealName(),
                user.getAge(),
                user.getPhone(),
                user.getGender(),
                user.getDesc(),
                new Date(),
                new Date(),
                user.getPic(),
                user.getLook(),
                user.getIsSecret(),
                user.getDeptName(),
                user.getDeptId()
        );
    }

    public void insertUser(User user){
        BaseDao.jdbcTemplate.update("insert into user (username, email, real_name, age, phone, gender, register_time, dept_name, dept_id) VALUES (?,?,?,?,?,?,?,?,?,?)",
                new BeanPropertyRowMapper<>(User.class),
                user.getUsername(),
                user.getEmail(),
                user.getRealName(),
                user.getAge(),
                user.getPhone(),
                user.getGender(),
                user.getRegisterTime(),
//                user.getPic(),
                user.getDeptName(),
                user.getDeptId()
        );
    }

    public User findById(Integer id) {
        String sql = "select * from user where id=?";
        try {
            return BaseDao.jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updatePic(Integer id,String pic) {
        String sql = "update user set pic=? where id=?";
             BaseDao.jdbcTemplate.update(sql, pic,id);
    }


    public Integer findUserCount(String name) {
        try {
            return BaseDao.jdbcTemplate.queryForObject("SELECT " +
                    "count(*) " +
                    "FROM " +
                    "user where username like ?", Integer.class, "%" + name + "%");
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> findUser(String username, Page page) {
        return BaseDao.jdbcTemplate.query("SELECT " +
                        "u.username," +
                        "u.email," +
                        "u.age," +
                        "u.gender," +
                        "u.login_time," +
                        "u.real_name," +
                        "u.dept_id ," +
                        "u.id ," +
                        "d.NAME AS deptName " +
                        "FROM " +
                        "USER u " +
                        "LEFT JOIN dept d ON u.dept_id = d.id WHERE u.username like ? LIMIT ?,?",
                new BeanPropertyRowMapper<>(User.class),
                "%" + username + "%",
                (page.getPageCurren() - 1) * page.getPageSize(),
                page.getPageSize());
    }

    public void deletbyUsername(Integer id) {
        BaseDao.jdbcTemplate.update("delete from user where  id=?", id);
    }

    public void updateUser(User user) {
        BaseDao.jdbcTemplate.update("update  user set username=?,email=?,gender=?,age=?,dept_name=?,real_name =?,dept_id=? where id=?",
                user.getUsername(),
                user.getEmail(),
                user.getGender(),
                user.getAge(),
                user.getDeptName(),
                user.getRealName(),
                user.getDeptId(),
                user.getId());
    }


    public List<User> findUserByDeptId(Integer id) {
            return BaseDao.jdbcTemplate.query("SELECT " +
                    "* " +
                    "FROM " +
                    "user where dept_id = ?", new BeanPropertyRowMapper<>(User.class),id);
    }
}
