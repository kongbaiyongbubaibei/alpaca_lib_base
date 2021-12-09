package com.alpaca.library_base.utils;


import android.content.SharedPreferences;

import com.alpaca.library_base.base.BaseApplication;

/**
 * author : Shirley
 * date : 2020/05/21
 * description : 数据存储类
 */
public class DataPreferences {

    protected String dbPrefsName = "com.ytcourse.client.netschool.dbs.base";
    protected String userDbPrefsName = "com.ytcourse.client.user.dbs.base";

    protected SharedPreferences dbs;
    protected SharedPreferences userDbs;

    /*** 网校token */
    private final String CourseSchoolToken = "CourseSchoolToken";

    /*** 课程开课倒计时间隔时间 */
    private final String CourseIntervalTime = "CourseIntervalTime";

    /*** 最近一节课程开课时间 */
    private final String CourseLatelyTime = "CourseLatelyTime";

    /*** 是否打开引导页1 */
    private final String IsOpenGuideOne = "IsOpenGuideOne";

    /*** 是否打开引导页2 */
    private final String IsOpenGuideTwo = "IsOpenGuideTwo";

    /*** oaid */
    private final String ANDROID_OAID = "ANDROID_OAID";

    /*** userAgent */
    private final String USER_AGENT = "USER_AGENT";

    /*** 是否第一次打开APP */
    private final String IS_FIRST_OPEN_APP = "IS_FIRST_OPEN_APP";

    /*** 是否第一次打开微信回流 */
    private final String IS_FIRST_OPEN_WX = "IS_FIRST_OPEN_WX";

    /*** 是否第一次看公开课 */
    private final String IS_FIRST_OPEN_CLASS = "IS_FIRST_OPEN_CLASS";

    /*** 播放短视频时可以上下滑动切换 是否已经提醒 */
    private final String IsPlayShotVideoTip = "IsPlayShotVideoTip";

    private final String IsAutoPlayShotVideo = "IsAutoPlayShotVideo";
    //是否静音播放
    private final String IsMutePlay = "IsMutePlay";

    protected static DataPreferences mInstance;

    private DataPreferences() {
        dbs = BaseApplication.getInstance().getSharedPreferences(dbPrefsName, 0);
        userDbs = BaseApplication.getInstance().getSharedPreferences(userDbPrefsName, 0);
    }

    public static DataPreferences getInstance() {
        if (mInstance == null) {
            synchronized (DataPreferences.class) {
                mInstance = new DataPreferences();
            }
        }
        return mInstance;
    }

    public String getCourseSchoolToken() {
        try {
            return userDbs.getString(CourseSchoolToken, "");
        } catch (Throwable e) {
            return "";
        }
    }

    public void setCourseSchoolToken(String token) {
        userDbs.edit().putString(CourseSchoolToken, token).apply();
    }

    public long getCourseIntervalTime() {
        try {
            LogUtil.d("CourseTime", "getCourseIntervalTime:" + dbs.getLong(CourseIntervalTime, 1000 * 60 * 60));
            return userDbs.getLong(CourseIntervalTime, 1000 * 60 * 60);
        } catch (Throwable e) {
            return 1000 * 60 * 60;
        }
    }

    public void setCourseIntervalTime() {
        long courseIntervalTime = 1000 * 60 * 60;
        if (DataPreferences.getInstance().getCourseLatelyTime() > TimeUtil.getCurrentTime()) {
            courseIntervalTime = 1000 * 60;
            if (DataPreferences.getInstance().getCourseLatelyTime() - TimeUtil.getCurrentTime() < 90000) {
                courseIntervalTime = 1000;
            }
        }
        LogUtil.d("CourseTime", "setCourseIntervalTime:" + courseIntervalTime);
        userDbs.edit().putLong(CourseIntervalTime, courseIntervalTime).apply();
    }

    public long getCourseLatelyTime() {
        try {
            return userDbs.getLong(CourseLatelyTime, 0);
        } catch (Throwable e) {
            return 0;
        }
    }

    public void setCourseLatelyTime(long lastBrushHeatTime) {
        LogUtil.d("CourseTime", "setCourseLatelyTime:" + lastBrushHeatTime);
        userDbs.edit().putLong(CourseLatelyTime, lastBrushHeatTime).apply();
    }

    public boolean isOpenGuideOne() {
        try {
            return dbs.getBoolean(IsOpenGuideOne, false);
        } catch (Throwable e) {
            return false;
        }
    }

    public void setOpenGuideOne(boolean isOpenGuideOne) {
        dbs.edit().putBoolean(IsOpenGuideOne, isOpenGuideOne).apply();
    }

    public boolean isOpenGuideTwo() {
        try {
            return dbs.getBoolean(IsOpenGuideTwo, false);
        } catch (Throwable e) {
            return false;
        }
    }

    public void setOpenGuideTwo(boolean isOpenGuideTwo) {
        dbs.edit().putBoolean(IsOpenGuideTwo, isOpenGuideTwo).apply();
    }

    public String getOaid() {
        try {
            return dbs.getString(ANDROID_OAID, "");
        } catch (Throwable e) {
            return "";
        }
    }

    public void setOaid(String oaid) {
        dbs.edit().putString(ANDROID_OAID, oaid).apply();
    }

    public String getUserAgent() {
        try {
            return dbs.getString(USER_AGENT, "");
        } catch (Throwable e) {
            return "";
        }
    }

    public void setUserAgent(String userAgent) {
        dbs.edit().putString(USER_AGENT, userAgent).apply();
    }


    public boolean isFirstOpenApp() {
        try {
            return dbs.getBoolean(IS_FIRST_OPEN_APP, true);
        } catch (Throwable e) {
            return true;
        }
    }

    public void setFirstOpenApp(boolean isFirstOpenApp) {
        dbs.edit().putBoolean(IS_FIRST_OPEN_APP, isFirstOpenApp).apply();
    }

    public boolean isFirstOpenWX() {
        try {
            return dbs.getBoolean(IS_FIRST_OPEN_WX, true);
        } catch (Throwable e) {
            return true;
        }
    }

    public void setFirstOpenWX(boolean isFirstOpenWX) {
        dbs.edit().putBoolean(IS_FIRST_OPEN_WX, isFirstOpenWX).apply();
    }

    public boolean isFirstOpenClass() {
        try {
            return dbs.getBoolean(IS_FIRST_OPEN_CLASS, true);
        } catch (Throwable e) {
            return true;
        }
    }

    public void setFirstOpenClass(boolean isFirstOpenWX) {
        dbs.edit().putBoolean(IS_FIRST_OPEN_CLASS, isFirstOpenWX).apply();
    }

    public boolean isPlayShotVideoTip() {
        try {
            return dbs.getBoolean(IsPlayShotVideoTip, false);
        } catch (Throwable e) {
            return false;
        }
    }

    public void setPlayShotVideoTip(boolean isPlayShotVideoTip) {
        dbs.edit().putBoolean(IsPlayShotVideoTip, isPlayShotVideoTip).apply();
    }

    public boolean isAutoPlayShotVideo() {
        try {
            return dbs.getBoolean(IsAutoPlayShotVideo, true);
        } catch (Throwable e) {
            return false;
        }
    }

    public void setAutoPlayShotVideo(boolean isAutoPlayShotVideo) {
        dbs.edit().putBoolean(IsAutoPlayShotVideo, isAutoPlayShotVideo).apply();
    }

    public boolean isMutePlayVideo() {
        try {
            return dbs.getBoolean(IsMutePlay, true);
        } catch (Throwable e) {
            return false;
        }
    }

    public void setMutePlayVideo(boolean isMutePlay) {
        dbs.edit().putBoolean(IsMutePlay, isMutePlay).apply();
    }

}
