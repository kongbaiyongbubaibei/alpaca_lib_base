package com.alpaca.library_base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * @文件名: TimerUtils
 * @功能描述:
 * @Date : 2018/8/7
 * @email:
 * @修改记录:
 */
public class TimerUtils {
    private static SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    private static SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    private static SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    public final static String FORMAT_STRING = "yyyy-MM-dd";

    public final static String[] REPLACE_STRING = new String[]{"GMT+0800", "GMT+08:00"};

    public final static String SPLIT_STRING = "(中国标准时间)";


    public static long currentTime = System.currentTimeMillis();
    public static int getCurrentMonth(){
        return Integer.parseInt(monthFormat.format(new Date(currentTime)));
    }
    public static int getCurrentYear(){
        return Integer.parseInt(yearFormat.format(new Date(currentTime)));
    }
    public static int getCurrentDay(){
        return Integer.parseInt(dayFormat.format(new Date(currentTime)));
    }
    public static int getCurrentTime(){
        return Integer.parseInt(timeFormat.format(new Date(currentTime)));
    }
    public static long getCurrentTimeDate(){
        return currentTime;
    }

    public static String timeStamp2Date(String time) {
        Long timeLong = Long.parseLong(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");//要转换的时间格式
        Date date;
        try {
            date = sdf.parse(sdf.format(timeLong));
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static Date str2Date(String dateString) {
        try {
            dateString = dateString.split(Pattern.quote(SPLIT_STRING))[0].replace(REPLACE_STRING[0], REPLACE_STRING[1]);
            SimpleDateFormat sf1 = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss z", Locale.US);
            Date date = sf1.parse(dateString);
            return date;
        } catch (Exception e) {
            throw new RuntimeException("时间转化格式错误" + "[dateString=" + dateString + "]" + "[FORMAT_STRING=" + FORMAT_STRING + "]");
        }
    }

    /**获取当前系统时间的年份
     * @return
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }


    /**获取当前系统时间的月份
     * @return
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    /**获取当前系统时间的日
     * @return
     */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }


    /**
     * 获取前n天日期、后n天日期
     *
     * @param distanceDay 前几天 如获取前7天日期则传-7即可；如果后7天则传7
     * @return
     */
    public static String getOldDate(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LogUtil.d("前7天==" + dft.format(endDate));
        return dft.format(endDate);
    }

    /**
     * 判断2个时间大小
     * yyyy-MM-dd HH:mm 格式（自己可以修改成想要的时间格式）
     * @param startTime
     * @param endTime
     * @return
     */
    public static int timeCompare(String startTime, String endTime){
        int i=0;
   //注意：传过来的时间格式必须要和这里填入的时间格式相同
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = dateFormat.parse(startTime);//开始时间
            Date date2 = dateFormat.parse(endTime);//结束时间
            // 1 结束时间小于开始时间 2 开始时间与结束时间相同 3 结束时间大于开始时间
            if (date2.getTime()<date1.getTime()){
                //结束时间小于开始时间
                i= 1;
            }else if (date2.getTime()==date1.getTime()){
                //开始时间与结束时间相同
                i= 2;
            }else if (date2.getTime()>date1.getTime()){
                //结束时间大于开始时间
           i= 3;
            }
        } catch (Exception e) {

        }
        return  i;
    }




}
