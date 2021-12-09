package com.alpaca.library_base.base;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * @文件名: AppManager
 * @功能描述:
 * @Date : 2019/10/23
 * @email:
 * @修改记录:
 */
public class AppManager {

    private static AppManager instance = null;
    private static List<Activity> mActivities = new LinkedList<>();

    private AppManager() {
    }

    public static AppManager getInstance() {
        if (null == instance) {
            synchronized (AppManager.class) {
                if (null == instance) {
                    instance = new AppManager();
                }
            }
        }
        return instance;
    }

    public int size() {
        return mActivities.size();
    }

    public synchronized Activity getForwardActivity() {
        return size() > 0 ? mActivities.get(size() - 1) : null;
    }

    public synchronized void addActivity(Activity activity) {
        mActivities.add(activity);
    }

    public synchronized void removeActivity(Activity activity) {
        if (mActivities.contains(activity)) {
            mActivities.remove(activity);
        }
    }

    public synchronized void clear() {
        for (int i = mActivities.size() - 1; i > -1; i--) {
            Activity activity = mActivities.get(i);
            removeActivity(activity);
            activity.finish();
            i = mActivities.size();
        }
    }

    public synchronized void clearToTop() {
        for (int i = mActivities.size() - 2; i > -1; i--) {
            Activity activity = mActivities.get(i);
            removeActivity(activity);
            activity.finish();
            i = mActivities.size() - 1;
        }
    }

    public static List<Activity> getActivities() {
        return mActivities;
    }
}
