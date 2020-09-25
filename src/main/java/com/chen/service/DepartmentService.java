package com.chen.service;

import com.chen.dao.DepartmentDao;
import com.chen.entiy.Dept;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.service
 * @Author: ChenZengWen
 * @Description: 描述
 * @Date: 2020/9/25 14:01
 * @Version: 1.0
 */
public class DepartmentService {
   private DepartmentDao departmentDao=new DepartmentDao();
    public Dept findDepartmentById(Integer id) {
       return departmentDao.findDepartmentById(id);
    }
}
