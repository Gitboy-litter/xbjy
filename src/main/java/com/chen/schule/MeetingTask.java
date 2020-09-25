package com.chen.schule;

import com.chen.entiy.Meeting;
import com.chen.service.MeetingService;

import java.util.TimerTask;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.schule
 * @Author: ChenZengWen
 * @Description: 描述
 * @Date: 2020/9/25 14:12
 * @Version: 1.0
 */
public class MeetingTask extends TimerTask {
    private MeetingService meetingService = new MeetingService();
    private boolean isrunning = false;

    @Override
    public void run() {
        if (!isrunning) {
            isrunning = true;
            meetingService.updateStatus();
            isrunning = false;
        }
    }
}
