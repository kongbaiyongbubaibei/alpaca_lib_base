package com.alpaca.library_base.manager;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MyActivityManager {
    private static MyActivityManager sInstance = new MyActivityManager();
    private WeakReference<Activity> sCurrentActivityWeakRef;
    private static List<Activity> activityStack = new ArrayList<>();

    private MyActivityManager() {

    }

    public static MyActivityManager getInstance() {
        return sInstance;
    }

    public List<Activity> getActivityStack() {
        return activityStack;
    }

    public Activity  getCurrentActivity() {
        Activity currentActivity = null;
        if (sCurrentActivityWeakRef != null) {
            currentActivity = sCurrentActivityWeakRef.get();
        }
        return currentActivity;
    }

    public void setCurrentActivity(Activity activity) {
        sCurrentActivityWeakRef = new WeakReference<Activity>(activity);
    }

    public void addActivity(Activity aty) {
        activityStack.add(aty);
    }

    public void removeActivity(Activity aty) {
        activityStack.remove(aty);
    }

    /**
     * 获取指定的Activity对象
     */
    public <T extends Activity> T getActivity(Class<T> cla) {
        Activity act = null;
        int leng = activityStack.size();
        for (int i = 0; i < leng; i++) {
            if (activityStack.get(i).getClass().getSimpleName()
                    .equals(cla.getSimpleName())) {
                act = activityStack.get(i);
            }
        }
        return (T) act;
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            if (activityStack.contains(activity)) {
                activityStack.remove(activity);
                activity.finish();
            }
        }
    }

    public <T extends Activity> T  finishActivity(Class<T> cla) {
        finishActivity(getActivity(cla));
        return getActivity(cla);
    }
    /**
     * 结束所有Activity
     */
    public <T extends Activity> T finishOtherActivity(Class<T> cla) {
        List<Activity> list = new ArrayList<>(activityStack);
        for (int i = 0, size = list.size(); i < size; i++) {
            if (null != list.get(i)) {
                if (!list.get(i).getClass().getSimpleName().equals(cla.getSimpleName())) {
                    list.get(i).finish();
                }
            }
        }
        list.clear();
        return getActivity(cla);
    }
    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public void finishAllActivityExceptCurrent() {
        for (int i = 0, size = activityStack.size(); i < size - 1; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public final boolean haveSimpleNameActivity(Activity activity, String name) {
        if (activity == null) {
            return false;
        }
        final int size = activityStack.size();
        Activity temp;
        for (int i = size - 1; i >= 0; i--) {
            temp = activityStack.get(i);
            if (name.equals(temp.getClass().getSimpleName())) {
                return true;
            }
        }
        return false;
    }
}
