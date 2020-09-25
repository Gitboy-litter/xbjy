package com.chen.enums;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.enums
 * @Author: ChenZengWen
 * @Description: 描述
 * @Date: 2020/9/25 12:28
 * @Version: 1.0
 */
public enum  MeetingEnum {
    NO_START(0),
    MEETING(1),
    END(2),
    ;
   private Integer value;

    MeetingEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }}
