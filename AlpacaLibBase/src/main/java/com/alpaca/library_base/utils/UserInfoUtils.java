package com.alpaca.library_base.utils;

import android.content.Context;
import android.text.TextUtils;

import com.alpaca.library_base.constants.Constants;
import com.alpaca.library_base.module.UserDetailBean;

import java.util.Date;

import static com.alpaca.library_base.utils.TimeUtil.FORMAT_YEAR_MONTH_DAY;

/**
 * class desc :
 *
 * @author : zzh
 * @date : 2019/10/30
 */
public class UserInfoUtils {

    private static UserDetailBean.DataBean userInfo;

    public static void saveUserInfos(Context context, UserDetailBean.DataBean userInfo, String userName, String userIcon, String userUid, int userSex, long time, int member, int oldMember, long endTime, int likeCount, int workCount) {
        UserInfoUtils.userInfo = userInfo;

        if (!TextUtils.isEmpty(userName)) {
            SharedPreferenceUtil.put(context, Constants.SP.USER_NAME, userName);
            Constants.User.USER_NAME = userName;
        }
        if (!TextUtils.isEmpty(userIcon)) {
            SharedPreferenceUtil.put(context, Constants.SP.USER_ICON, userIcon);
            Constants.User.USER_ICON = userIcon;
        }
        if (!TextUtils.isEmpty(userUid)) {
            SharedPreferenceUtil.put(context, Constants.SP.USER_UID, userUid);
            Constants.User.USER_UID = userUid;
        }
        if (userInfo != null) {
            SharedPreferenceUtil.put(context, Constants.SP.USER_CREAT, time);
            Constants.User.USER_CREATTIME = time;
        }
        Constants.User.ISMEMBERS = member;
        Constants.User.ISOLDMEMBERS = oldMember;
        Constants.User.USER_VIP_TIME = TimeUtil.dateToStr(new Date(endTime), FORMAT_YEAR_MONTH_DAY);
        Constants.User.WORKCOUNT = workCount;
        Constants.User.LIKECOUNT = likeCount;
        SharedPreferenceUtil.put(context, Constants.SP.USER_SEX, userSex);
        Constants.User.USER_SEX = userSex;
    }

    public static UserDetailBean.DataBean getUserInfo() {
        if (userInfo != null) {
            return userInfo;
        }
        return new UserDetailBean.DataBean();
    }

    public static void getUserInfos(Context context) {
        Constants.User.USER_NAME = (String) SharedPreferenceUtil.get(context, Constants.SP.USER_NAME, "");
        Constants.User.USER_ICON = (String) SharedPreferenceUtil.get(context, Constants.SP.USER_ICON, "");
        Constants.User.USER_UID = (String) SharedPreferenceUtil.get(context, Constants.SP.USER_UID, "");
        Constants.User.USER_SEX = (int) SharedPreferenceUtil.get(context, Constants.SP.USER_SEX, 0);
    }
}
