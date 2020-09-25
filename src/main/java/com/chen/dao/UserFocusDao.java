package com.chen.dao;

import com.chen.entiy.Page;
import com.chen.entiy.User;
import com.chen.entiy.Userfocus;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.dao
 * @Author: ChenZengWen
 * @Description: 用户关注
 * @Date: 2020/9/24 19:36
 * @Version: 1.0
 */
public class UserFocusDao extends BaseDao {
    public List<Userfocus> findUserFocus(Integer id) {
        return jdbcTemplate.query("select * from userfocus where user_id = ?", new BeanPropertyRowMapper<>(Userfocus.class), id);
    }

    public void deleteFocusById(Integer id) {
         jdbcTemplate.update("delete from userfocus where user_focus_id=?", id);
    }

    public void insertFocus(Integer id,Integer userId) {
        jdbcTemplate.update("insert into userfocus (user_id, user_focus_id) values (?,?)", userId,id);
    }


    public List<User> findUserFocusList(Integer id, String username, Page page) {
        String sql = "SELECT " +
                "u.* " +
                "FROM " +
                "user u," +
                "userfocus uf " +
                "WHERE " +
                "uf.user_focus_id = u.id  " +
                "AND uf.user_id =? " +
                "AND u.username LIKE ? " +
                "LIMIT ?,?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class),
                id,
                "%" + username + "%",
                (page.getPageCurren()-1)*page.getPageSize(),
                page.getPageSize());
    }

    public Integer findUserFocusCount(Integer id,String name) {
        String sql = "SELECT " +
                "count(*)" +
                "FROM" +
                "`user` u, " +
                "userfocus uf  " +
                "WHERE " +
                "uf.user_focus_id = u.id " +
                "AND uf.user_id = ? " +
                "AND u.username LIKE ? ";

        try {
            return BaseDao.jdbcTemplate.queryForObject(sql, Integer.class, id,"%" + name + "%");
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
