package com.chen.Listerner;

import com.chen.schule.MeetingTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;
import java.util.Timer;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.Listerner
 * @Author: ChenZengWen
 * @Description: 描述
 * @Date: 2020/9/25 14:11
 * @Version: 1.0
 */
@WebListener
public class ApplicationListerner implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Timer timer=new Timer();
        MeetingTask meetingTask=new MeetingTask();
        timer.schedule(meetingTask,new Date(),1000*3);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
