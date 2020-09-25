package com.chen.dao;

import com.chen.entiy.Meeting;
import com.chen.entiy.Page;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.servlet.annotation.WebServlet;
import java.util.List;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.dao
 * @Author: ChenZengWen
 * @Description: 描述
 * @Date: 2020/9/25 10:33
 * @Version: 1.0
 */
public class MeetingDao extends BaseDao {
    public List<Meeting> findMeetingList(String title, Page page) {
        String sql = "select * from meeting where title like ? limit ?,?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Meeting.class), "%" + title + "%", (page.getPageCurren() - 1) * page.getPageSize(), page.getPageSize());
    }

    public Integer findMeetingCount(String title) {
        String sql = "select count(*) from meeting where title like ? ";
        return jdbcTemplate.queryForObject(sql, Integer.class, "%" + title + "%");
    }

    public void insertMeeting(Meeting meeting) {
        jdbcTemplate.update("insert into meeting (id,dept_id,dept_name, title, content, publish_date, start_time, end_time, status, make_user) values (null ,?,?,?,?,?,?,?,?,?)",
                meeting.getDeptId(),
                meeting.getDeptName(),
                meeting.getTitle(),
                meeting.getContent(),
                meeting.getPublishDate(),
                meeting.getStartTime(),
                meeting.getEndTime(),
                meeting.getStatus(),
                meeting.getMakeUser()
        );
    }

    public List<Meeting> findMeetingAll() {
        String sql = "select * from meeting ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Meeting.class));
    }

    public void updateStatus(Meeting meeting) {
        String sql = "update meeting set status = ? where id=?";
        jdbcTemplate.update(sql, meeting.getStatus(), meeting.getId());
    }

    public Meeting findMeetingById(Integer id) {
        String sql = "select * from meeting where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Meeting.class), id);
    }

    public List<Integer> findUserId(Integer id) {
        /**
         * @Method findUserId
         * @Author ChenZW
         * @Version 1.0
         * @Param [id]
         * @Description 查询需要参加会议的用户的id
         * @Return com.chen.entiy.Meeting
         * @Date 2020/9/25 16:03
         *@版权归属于ChenZW
         */
        return jdbcTemplate.queryForList("select u_id from meeting_join where m_id=?", Integer.class, id);
    }
    public void insertMeetingJoin(Integer meetingId,Integer userId) {
        jdbcTemplate.update("insert into meeting_join (m_id,u_id) values (?,?)",meetingId,userId);
    }
    public void deleteMeetingJoin(Integer meetingId,Integer userId) {
        jdbcTemplate.update("delete from meeting_join where m_id=? and u_id=?",meetingId,userId);
    }
}
