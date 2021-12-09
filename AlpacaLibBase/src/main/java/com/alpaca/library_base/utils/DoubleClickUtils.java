package com.alpaca.library_base.utils;

/**
 * @文件名: DoubleClickUtils
 * @功能描述:
 * @Date : 2021/4/21
 * @email:
 * @修改记录:
 */
public class DoubleClickUtils {
    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 700;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }
}
