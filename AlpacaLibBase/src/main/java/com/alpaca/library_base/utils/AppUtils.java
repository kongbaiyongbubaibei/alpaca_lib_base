package com.alpaca.library_base.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.alpaca.library_base.base.BaseApplication;
import com.alpaca.library_base.constants.Constants;

import java.io.File;
import java.util.List;
import java.util.Random;

import androidx.core.content.FileProvider;

/**
 * @文件名: AppUtils
 * @功能描述:
 * @Date: 2018/1/4
 * @email:
 * @修改记录:
 */
public class AppUtils {

    private static String mdevicetype = "";
    private static String mplatform = "";
    private static String mdeviceId = "";


    public static void installationApk(Context context, File filePaths) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", filePaths);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(filePaths), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    public static void installationApk(Context context, String filePaths) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(filePaths), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static void callPhone(Activity activity, String telStr) {
        if (!TextUtils.isEmpty(telStr)) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("tel:" + telStr));
                activity.startActivity(intent);
            } catch (Exception ex) {
                ex.printStackTrace();
                Toast.makeText(activity, "拨打电话号码失败!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(activity, "电话号码为空!", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isWeixinAvilible(Context context) {
        if (SharedPreferenceUtil.getAgreePrivacyStatus()) {
            final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
            List<PackageInfo> packages = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
            if (packages != null) {
                for (int i = 0; i < packages.size(); i++) {
                    String pn = packages.get(i).packageName;
                    if (pn.equals("com.tencent.mm")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isQQClientAvailable(Context context) {
        if (SharedPreferenceUtil.getAgreePrivacyStatus()) {
            final PackageManager packageManager = context.getPackageManager();
            List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
            if (pinfo != null) {
                for (int i = 0; i < pinfo.size(); i++) {
                    String pn = pinfo.get(i).packageName;
                    if (pn.equals("com.tencent.mobileqq")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static PackageInfo getPackageInfo(Context context) {
        if (SharedPreferenceUtil.getAgreePrivacyStatus()) {
            PackageManager pm = context.getPackageManager();
            try {
                return pm.getPackageInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static ApplicationInfo getApplicationInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static String getChanel(Context context) {
//        return WalleChannelReader.getChannel(context, "sq580");
//    }

    public static String getVersionName(Context context) {

        PackageInfo packageInfo = getPackageInfo(context);
        if (null != packageInfo) {
            return packageInfo.versionName;
        }
        return "";
    }

    public static int getVersionCode(Context context) {

        PackageInfo packageInfo = getPackageInfo(context);
        if (null != packageInfo) {
            return packageInfo.versionCode;
        }
        return 1;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion() {
        try {
            PackageManager manager = BaseApplication.getInstance().getPackageManager();
            PackageInfo info = manager.getPackageInfo(BaseApplication.getInstance().getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getDeviceType(Context context) {

        if (TextUtils.isEmpty(mdevicetype)) {
            getPhoneMes(context);
        }
        return TextUtils.isEmpty(mdevicetype) ? "" : mdevicetype;
    }


    public static String getPlatform(Context context) {
        if (TextUtils.isEmpty(mplatform)) {
            getPhoneMes(context);
        }
        return TextUtils.isEmpty(mplatform) ? "" : mplatform;
    }

    public static String getDeviceId(Context context) {
        if (TextUtils.isEmpty(mdeviceId)) {
            getPhoneMes(context);
        }
        return TextUtils.isEmpty(mdeviceId) ? "" : mdeviceId;
    }

    @SuppressLint("MissingPermission")
    public static void getPhoneMes(Context context) {
        try {
            TelephonyManager mTm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            String mtype = Build.MODEL; // 手机型号
            String mtyb = Build.BRAND;//手机品牌
            mdevicetype = mtyb + "-" + mtype;

            String platform = Build.VERSION.RELEASE;//手机Android系统版本
            String display = Build.DISPLAY;//手机系统名称
            mplatform = "Android版本：" + platform + " 系统名称：" + display;

            mdeviceId = mTm.getDeviceId();//手机设备IME
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getApkDir() {
        String path = Constants.APK_DIR;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return path;
    }

    public static String getPictureDri() {
        String path = Constants.PICTURE_PATH;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return path;
    }

    public static void openWeb(Context context, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        try {
            Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            it.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
            context.startActivity(it);
        } catch (Exception e) {
            e.printStackTrace();
            Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(it);
        }

    }

    public static void goRate(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName) || context == null) {
            return;
        }
        String market = "market://details?id=" + packageName;
        Uri uri = Uri.parse(market);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            if (goToMarket.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(goToMarket);
            }
        } catch (Exception e) {
            String url = "http://play.google.com/store/apps/details?id=" + packageName;
            openWeb(context, url);
        }
    }

    public static String getDefaultUUID() {
        String uuid = (String) SharedPreferenceUtil.getNotClear(BaseApplication.getInstance(), "user_UUID", "");
        if (TextUtils.isEmpty(uuid)) {
            uuid = String.format("alpaca-%d-%d", System.currentTimeMillis(), new Random().nextInt(100000));
            SharedPreferenceUtil.putNotClear(BaseApplication.getInstance(), "user_UUID", uuid);
        }
        return uuid;
    }
}
