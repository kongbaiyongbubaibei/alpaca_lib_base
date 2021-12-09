package com.alpaca.library_base.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import com.alpaca.library_base.R;
import com.alpaca.library_base.base.BaseApplication;
import com.alpaca.library_base.constants.Constants;

import java.util.HashMap;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

/**
 * @文件名: NotificationsUtils
 * @功能描述:
 * @Date : 2020/5/25
 * @email:
 * @修改记录:
 */
public class NotificationsUtils {

    //静音通知
    public static final String QUIET_CHANNEL_ID = "YTForegroundServiceQuietChannel";
    public static final String CHANNEL_ID = "YTForegroundServiceChannel";
    private int mNotifyId;
    private static NotificationsUtils mInstance;
    // NotificationmManager:是状态栏通知的管理类，负责发通知、清楚通知等。
    private NotificationManager mManager;
    // 定义mMap来保存Notification对象
    private HashMap<Integer, Notification> mMap;

    public NotificationsUtils() {
        mManager = (NotificationManager) BaseApplication.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
        mMap = new HashMap<>();
        createNotificationChannel();
    }

    public static NotificationsUtils getInstance() {
        if (mInstance == null) {
            synchronized (NotificationsUtils.class) {
                mInstance = new NotificationsUtils();
            }
        }
        return mInstance;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 传入参数：通道ID，通道名字，通道优先级（类似曾经的 builder.setPriority()）
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    BaseApplication.getInstance().getString(R.string.notification_channel_name),
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            // 配置通知渠道的属性
            serviceChannel.setDescription("GeiLi");
            // 设置通知出现时的闪灯（如果 android 设备支持的话）
            serviceChannel.enableLights(true);
            serviceChannel.setLightColor(Color.RED);
            // 设置通知出现时的震动（如果 android 设备支持的话）
            serviceChannel.enableVibration(false);
            serviceChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mManager.createNotificationChannel(serviceChannel);

            // 传入参数：通道ID，通道名字，通道优先级（类似曾经的 builder.setPriority()）
            @SuppressLint("WrongConstant")
            NotificationChannel quietServiceChannel = new NotificationChannel(
                    QUIET_CHANNEL_ID,
                    BaseApplication.getInstance().getString(R.string.notification_channel_name),
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            // 设置通知出现时声音，默认通知是有声音的
            quietServiceChannel.enableVibration(false);
            quietServiceChannel.setSound(null, null);
            mManager.createNotificationChannel(quietServiceChannel);
        }
    }

    public boolean isNotificationEnabled(Context context) {
        return NotificationManagerCompat.from(context.getApplicationContext()).areNotificationsEnabled();
    }

    public static void openPush(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //这种方案适用于 API 26, 即8.0（含8.0）以上可以用
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, activity.getPackageName());
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, activity.getApplicationInfo().uid);
            activity.startActivity(intent);
        } else {
            toPermissionSetting(activity);
        }
    }

    /**
     * 跳转到权限设置
     *
     * @param activity
     */
    public static void toPermissionSetting(Activity activity) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            toSystemConfig(activity);
        } else {
            try {
                toApplicationInfo(activity);
            } catch (Exception e) {
                e.printStackTrace();
                toSystemConfig(activity);
            }
        }
    }

    /**
     * 应用信息界面
     *
     * @param activity
     */
    public static void toApplicationInfo(Activity activity) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        localIntent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        activity.startActivity(localIntent);
    }

    /**
     * 系统设置界面
     *
     * @param activity
     */
    public static void toSystemConfig(Activity activity) {
        try {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void showNotify(String title, String msg) {
        showNotify(title, msg, null, null);
    }

    /**
     * 普通通知
     *
     * @param title  标题
     * @param msg    通知内容
     * @param cls    跳转页面
     * @param bundle 跳转页面所传参数
     */
    public void showNotify(String title, String msg, Class<?> cls, Bundle bundle) {
        Context context = BaseApplication.getInstance();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        Intent intent = null;
        if (cls != null) {
            //将要跳转的界面
            intent = new Intent(context, cls);
            if (bundle != null) {
                intent.putExtra(Constants.EXTRA_DATA_BUNDLE, bundle);
            }
        }
        //点击后消失
        builder.setAutoCancel(true);
        //设置通知栏消息标题的头像
        builder.setSmallIcon(R.drawable.notification_small_icon);
        builder.setLargeIcon(BitmapFactory.decodeResource(BaseApplication.getInstance().getResources(), R.mipmap.ic_launcher));
        //设置通知铃声
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        builder.setContentTitle(title);
        builder.setContentText(msg);
        if (intent != null) {
            //利用PendingIntent来包装我们的intent对象,使其延迟跳转
            PendingIntent intentPend = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            builder.setContentIntent(intentPend);
        }
        if (mNotifyId > 100) {
            mNotifyId = 0;
        }

        mManager.notify(mNotifyId, builder.build());
        mMap.put(mNotifyId, builder.build());// 存入mMap中
        mNotifyId++;
    }

}
