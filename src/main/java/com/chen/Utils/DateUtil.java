package com.chen.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: xbjy
 * @Package: com.chen.Utils
 * @Author: ChenZengWen
 * @Description: 描述
 * @Date: 2020/9/25 12:30
 * @Version: 1.0
 */
public class DateUtil {
    public static Date strtodate(String string){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = sdf.parse(string);
            return parse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String datetostr(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        return format;
    }
}
