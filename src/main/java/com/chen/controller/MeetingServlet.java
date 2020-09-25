package com.chen.controller;

import com.chen.constants.Constant;
import com.chen.entiy.Dept;
import com.chen.entiy.Meeting;
import com.chen.entiy.Page;
import com.chen.entiy.User;
import com.chen.service.DepartmentService;
import com.chen.service.MeetingService;
import com.chen.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.controller
 * @Author: ChenZengWen
 * @Description: 描述
 * @Date: 2020/9/25 10:38
 * @Version: 1.0
 */
@WebServlet("/meeting/*")
public class MeetingServlet extends BaseServlet {
    private MeetingService meetingService = new MeetingService();
    private DepartmentService departmentService = new DepartmentService();

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method list
         * @Author ChenZW
         * @Version 1.0
         * @Param [request, response]
         * @Description 查找所有的会议
         * @Return void
         * @Date 2020/9/25 10:39
         *@版权归属于ChenZW
         */
        String titleName = request.getParameter("titleName");
        if (titleName == null) {
            titleName = "";
        }
        Page<Meeting> page = new Page<>();
        page.setPagecount(meetingService.findMeetingCount(titleName));
        page.setPageTotal(page.getPageTotal());
        String pageCurrent = request.getParameter("pageCurrent");
        if (pageCurrent != null && pageCurrent != "") {
            page.setPageCurren(Integer.valueOf(pageCurrent));
        }
        List<Meeting> meetingList = meetingService.findMeetingList(titleName, page);
        page.setData(meetingList);
        request.setAttribute("page", page);
        request.setAttribute("titlename", titleName);
        request.getRequestDispatcher("/html/meet/meetlist.jsp").forward(request, response);
    }


    public void addmeeting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method addmeeting
         * @Author ChenZW
         * @Version 1.0
         * @Param [request, response]
         * @Description 发布会议
         * @Return void
         * @Date 2020/9/25 12:26
         *@版权归属于ChenZW
         */
        Meeting meeting = new Meeting();
        Map parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(meeting, parameterMap);
            Dept departmentById = departmentService.findDepartmentById(meeting.getDeptId());
            meeting.setDeptName(departmentById.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        meetingService.insertMeeting(meeting);
        request.getRequestDispatcher("/meeting/list").forward(request, response);
    }

    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method detail
         * @Author ChenZW
         * @Version 1.0
         * @Param [request, response]
         * @Description 会议详细信息
         * @Return void
         * @Date 2020/9/25 15:49
         *@版权归属于ChenZW
         */
        //创建map，用于把数据返回给前段
        HashMap<String, String> hashMap = new HashMap<>();
        //会议id
        String id = request.getParameter("id");
        //得到当前用户id
        Integer loginId = loginUser.getId();
        //通过会议id得到meeting
        Meeting meetingById = meetingService.findMeetingById(Integer.valueOf(id));
        //应该加入会议的人数
        int should = meetingById.getMakeUser().split(",").length;
        hashMap.put("should", should + "");
        //已经参加会议的用户id
        List<Integer> userId = meetingService.findUserId(Integer.valueOf(id));
        if (userId == null) {
            hashMap.put("arrival", "0");
        } else {
            hashMap.put("arrival", userId.size() + "");
        }
        //判断是否需要参加会议
        boolean contains = meetingById.getMakeUser().contains(loginId + "");
        if (contains) {
            //需要参加会议
            if (userId.contains(loginId)) {
                //已经参加会议了
                hashMap.put("flag", "2");
            } else {
                //还没有参加会议
                hashMap.put("flag", "3");
            }
        } else {
            //不需要参加会议
            hashMap.put("flag", "1");
        }
        request.setAttribute("meetingmap", hashMap);
        request.setAttribute("meeting", meetingById);
        request.getRequestDispatcher("/html/meet/detail.jsp").forward(request, response);
    }

    public void joinMeeting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method joinMeeting
         * @Author ChenZW
         * @Version 1.0
         * @Param [request, response]
         * @Description 加入会议
         * @Return void
         * @Date 2020/9/25 16:44
         *@版权归属于ChenZW
         */
        String meetingId = request.getParameter("id");
        Integer loginUserId = loginUser.getId();
        //参加会议
        meetingService.insertMeetingJoin(Integer.valueOf(meetingId), loginUserId);
        request.setAttribute("id", meetingId);
        request.getRequestDispatcher("/meeting/detail").forward(request, response);
    }

    public void deleteJoinMeeting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * @Method deleteJoinMeeting
         * @Author ChenZW
         * @Version 1.0
         * @Param [request, response]
         * @Description 退出会议
         * @Return void
         * @Date 2020/9/25 16:52
         *@版权归属于ChenZW
         */
        String meetingId = request.getParameter("id");
        Integer loginUserId = loginUser.getId();
        //退出会议
        meetingService.deleteMeetingJoin(Integer.valueOf(meetingId), loginUserId);
        request.setAttribute("id", meetingId);
        request.getRequestDispatcher("/meeting/detail").forward(request, response);
    }
}
