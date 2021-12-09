package com.alpaca.library_base.manager;

import android.app.Activity;
import android.os.Build;

import java.util.Stack;

public final class ActivityStack {


    private final static Stack<Activity> activityStack = new Stack<>();

    public static Stack<Activity> getActivityStack() {
        return activityStack;
    }

    /**
     * 添加 Activity
     *
     * @param activity
     */
    public final static void pushActivity(Activity activity) {
        activityStack.add(activity);
    }


    public final static boolean haveSimpleNameActivity(Activity activity, String name) {
        if (activity == null) {
            return false;
        }
        final int size = activityStack.size();
        Activity temp;
        for (int i = size - 1; i >= 0; i--) {
            temp = activityStack.get(i);
            if ("name".equals(temp.getClass().getSimpleName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 移除Activity
     *
     * @param activity
     */
    public final static void popActivity(Activity activity) {

        if (activity == null) {
            return;
        }
        final int size = activityStack.size();
        Activity temp;
        for (int i = size - 1; i >= 0; i--) {
            temp = activityStack.get(i);
            if (activity == temp) {
                activityStack.remove(i);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (!activity.isDestroyed()) {
                        activity.finish();
                    }
                } else {
                    activity.finish();
                }
                break;
            }
        }
    }

    public final static boolean haveRadioActivity(Activity activity) {
        if (activity == null) {
            return false;
        }
        final int size = activityStack.size();
        Activity temp;
        for (int i = size - 1; i >= 0; i--) {
            temp = activityStack.get(i);
            if ("RedioIndexActivity".equals(temp.getClass().getSimpleName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 移除所有Activity
     */
    public final static void popAllActivity() {
        for (Activity activity : activityStack) {
            activity.finish();
        }
        activityStack.clear();
    }


    /**
     * 回退至指定的Activity
     *
     * @param cls
     */
    public final static void popToSpecifyActivity(Class<? extends Activity> cls) {
        int size = activityStack.size();
        Activity temp;
        while (size > 0) {
            size--;
            temp = activityStack.pop();
            if (cls.getName().equals(temp.getClass().getName())) {
                break;
            } else {
                temp.finish();
            }
        }
    }

    /**
     * 回退至首页
     *
     */
    public final static void popToMainActivity() {
        int size = activityStack.size();
        Activity temp;
        while (size > 0) {
            size--;
            temp = activityStack.pop();
            if ("com.ytcourse.client.ui.main.MainActivity".equals(temp.getClass().getName())) {
                break;
            } else {
                temp.finish();
            }
        }
    }
}
