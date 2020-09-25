package com.chen.service;

import com.chen.dao.UserFocusDao;
import com.chen.entiy.Page;
import com.chen.entiy.User;
import com.chen.entiy.Userfocus;

import java.util.List;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.service
 * @Author: ChenZengWen
 * @Description: 描述
 * @Date: 2020/9/24 19:46
 * @Version: 1.0
 */
public class UserFocusService {
    private UserFocusDao userFocusDao = new UserFocusDao();

    public List<Userfocus> findUserFocus(Integer id) {
        return userFocusDao.findUserFocus(id);
    }

    public List<User> findUserFocusList(Integer id, String username, Page page) {
        return userFocusDao.findUserFocusList(id, username, page);
    }
    public Integer findUserFocusCount(Integer id,String name) {
        return userFocusDao.findUserFocusCount(id,name);
    }
    public void deleteFocusById(Integer id) {
        userFocusDao.deleteFocusById(id);
    }
    public void insertFocus(Integer id,Integer userId) {
        userFocusDao.insertFocus(id,userId);
    }
}
