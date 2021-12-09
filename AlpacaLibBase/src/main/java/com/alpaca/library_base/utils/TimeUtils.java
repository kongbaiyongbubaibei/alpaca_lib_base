package com.alpaca.library_base.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @文件名: TimeUtils
 * @功能描述:
 * @Date : 2018/7/18
 * @email:
 * @修改记录:
 */
public class TimeUtils {
    public static String getFormatHMS(long time) {
        time = time / 1000;//总秒数
        int s = (int) (time % 60);//秒
        int m = (int) (time / 60);//分
        int h = (int) (time / 3600);//秒
        if (h == 0) {
            return String.format("%02d:%02d", m, s);
        } else {
            m = m - (h * 60);
        }
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时";
    }

    public static String getWeek(long timemill,String[] weekDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timemill);
        int weekPosition = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDays[weekPosition];
    }
}
