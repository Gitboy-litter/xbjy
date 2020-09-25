package com.chen.service;

import com.chen.dao.UserDao;
import com.chen.entiy.Dept;
import com.chen.entiy.Page;
import com.chen.entiy.User;

import java.util.List;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.service
 * @Author: ChenZengWen
 * @Description: 描述
 * @Date: 2020/9/22 18:44
 * @Version: 1.0
 */
public class UserService {
    private UserDao userDao = new UserDao();

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public User findEmail(String email) {
        return userDao.findEmail(email);
    }

    public void insert(User user) {
        userDao.insert(user);
    }

    public List<User> findUser(String username, Page page) {
        return userDao.findUser(username, page);
    }

    public Integer findUserCount(String username) {
        return userDao.findUserCount(username);
    }

    public void deletbyUsername(Integer id) {
        userDao.deletbyUsername(id);
    }

    public List<Dept> findDepartment() {
        return userDao.findDepartment();
    }

    public User findById(Integer id) {
        return userDao.findById(id);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void insertUser(User user) {
        userDao.insert(user);
    }
    public void updatePic(Integer id,String pic) {
        userDao.updatePic(id,pic);
    }
    public List<User> findUserByDeptId(Integer id) {
        return userDao.findUserByDeptId(id);
    }
}
