package com.alpaca.library_base.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * @文件名: MateDataUtils
 * @功能描述:
 * @Date : 2019/10/15
 * @email:
 * @修改记录:
 */
public class MateDataUtils {
    static String TAG = "MateDataUtils";

    public static String getChannelCode(Context context) {

        String code = getMetaData(context, "UMENG_CHANNEL");

        if (code != null) {

            return code;

        }

        return "huawei";

    }


    private static String getMetaData(Context context, String key) {
        if (SharedPreferenceUtil.getAgreePrivacyStatus()) {
            try {
                ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
                        context.getPackageName(), PackageManager.GET_META_DATA);
                Object value = ai.metaData.get(key);
                if (value != null) {
                    return value.toString();
                }
            } catch (Exception e) {
            }
            return null;
        } else {
            return "default";
        }
    }

}
