package com.chen.service;

import com.chen.Utils.DateUtil;
import com.chen.dao.MeetingDao;
import com.chen.entiy.Meeting;
import com.chen.entiy.Page;
import com.chen.enums.MeetingEnum;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.service
 * @Author: ChenZengWen
 * @Description: 描述
 * @Date: 2020/9/25 10:37
 * @Version: 1.0
 */
public class MeetingService {
    private MeetingDao meetingDao = new MeetingDao();

    public List<Meeting> findMeetingList(String title, Page page) {
        return meetingDao.findMeetingList(title, page);
    }

    public Integer findMeetingCount(String title) {
        return meetingDao.findMeetingCount(title);
    }

    public void insertMeeting(Meeting meeting) {
        meeting.setPublishDate(DateUtil.datetostr(new Date()));
        meeting.setStatus(MeetingEnum.NO_START.getValue());
        meeting.setMakeUser(Arrays.toString(meeting.getMakeUsers()));
        meetingDao.insertMeeting(meeting);
    }

    public void updateStatus() {
        List<Meeting> meetingAll = meetingDao.findMeetingAll();
        for (Meeting meeting : meetingAll) {
            long startTime = DateUtil.strtodate(meeting.getStartTime()).getTime();
            long endTime = DateUtil.strtodate(meeting.getEndTime()).getTime();
            long nowTime = new Date().getTime();
            if (startTime > nowTime) {
                //未开始
            } else if (startTime <= nowTime && nowTime <= endTime) {
                //进行中
                meeting.setStatus(MeetingEnum.MEETING.getValue());
                meetingDao.updateStatus(meeting);
            } else {
                //结束了
                meeting.setStatus(MeetingEnum.END.getValue());
                meetingDao.updateStatus(meeting);
            }
        }
    }

    public Meeting findMeetingById(Integer id) {
        return meetingDao.findMeetingById(id);
    }

    public List<Integer> findUserId(Integer id) {
        return meetingDao.findUserId(id);
    }

    public void insertMeetingJoin(Integer meetingId, Integer userId) {
        meetingDao.insertMeetingJoin(meetingId, userId);
    }

    public void deleteMeetingJoin(Integer meetingId, Integer userId) {
        meetingDao.deleteMeetingJoin(meetingId, userId);
    }
}
