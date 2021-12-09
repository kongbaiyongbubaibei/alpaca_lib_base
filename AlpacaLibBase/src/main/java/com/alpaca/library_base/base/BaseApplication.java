package com.alpaca.library_base.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.alpaca.library_base.BuildConfig;
import com.alpaca.library_base.R;
import com.alpaca.library_base.constants.Constants;
import com.alpaca.library_base.manager.ActivityStack;
import com.alpaca.library_base.manager.MyActivityManager;
import com.alpaca.library_base.net.ApiClient;
import com.alpaca.library_base.net.BaseHttpUrl;
import com.alpaca.library_base.net.HttpUrl;
import com.alpaca.library_base.net.NetSchoolHttpUrl;
import com.alpaca.library_base.net.OkHttpManager;
import com.alpaca.library_base.router.RouterActivityPath;
import com.alpaca.library_base.router.RouterHelper;
import com.alpaca.library_base.utils.CacheUtils;
import com.alpaca.library_base.utils.CustomUncaughtExceptionHandler;
import com.alpaca.library_base.utils.SharedPreferenceUtil;
import com.alpaca.library_base.utils.UserInfoUtils;
import com.alpaca.library_base.widgets.ninegrid.NineGridView;
import com.danikula.videocache.HttpProxyCacheServer;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;

import androidx.annotation.Nullable;
import androidx.multidex.MultiDex;
import okhttp3.OkHttpClient;

public class BaseApplication extends Application {
    //服务器地址
    public static int DEBUG_LOCAL = 0, DEBUG_SERVER = 1, OFFICIAL_SERVER = 2;
    //当前调试模式
    public static int CURRENT_DEBUG_TYPE = BuildConfig.DEBUG ? OFFICIAL_SERVER : OFFICIAL_SERVER;

    public static boolean isDebug = BuildConfig.DEBUG;
    public static boolean is_Google = false;

    private String TAG = "BaseApplication";
    private static BaseApplication myApplication;
    public static boolean is_Exam = false;//审核状态
    public static IWXAPI iwxapi;
    public static Tencent mTencent;
    public static String TOKEN = "";
    public static boolean isNetSchoolToken = false;

    public final static String QQappID = "1111802017";
    public final static String WX_APP_ID = "wx3603de525bd3ee1f";
    private int appCount;
    private HttpProxyCacheServer proxy;


    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;

        //全局异常捕获
        Thread.setDefaultUncaughtExceptionHandler(new CustomUncaughtExceptionHandler());
        RouterHelper.init(this);
        initUserInfos();
        initAppConfig();
        initRetrofit();
        initWXApi();
        NineGridView.setImageLoader(new GlideImageLoader());
        clearCache();
        handlertakePhoto();
        initActivityManager();
        initPtrFrame();

    }

    private void initPtrFrame() {
        //全局设置默认的 Header
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //开始设置全局的基本参数（这里设置的属性只跟下面的MaterialHeader绑定，其他Header不会生效，能覆盖DefaultRefreshInitializer的属性和Xml设置的属性）
                layout.setEnableHeaderTranslationContent(false);
                return new ClassicsHeader(context);
            }
        });
    }

    public HttpProxyCacheServer getProxy() {
        if (proxy == null) {
            return newProxy();
        }
        return proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer.Builder(this)
                .maxCacheSize(1024 * 1024 * 1024)       // 1 Gb for cache
                .build();
    }

    private void initActivityManager() {

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                MyActivityManager.getInstance().addActivity(activity);
                activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                appCount++;
            }

            @Override
            public void onActivityResumed(Activity activity) {
                MyActivityManager.getInstance().setCurrentActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
                appCount--;
                if (appCount == 0) {
                    // 应用切到后台
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                MyActivityManager.getInstance().removeActivity(activity);
            }
        });
    }

    /**
     * 获得当前app运行的Application
     */
    public static BaseApplication getInstance() {
        if (myApplication == null) {
            throw new NullPointerException("please inherit BaseApplication or call setApplication.");
        }
        return myApplication;
    }


    public void initRetrofit() {
        OkHttpClient okHttpClient = OkHttpManager.create();
        ApiClient.create(HttpUrl.getBaseUrl(DEBUG_SERVER), okHttpClient);
        ApiClient.create(HttpUrl.getBaseUrl(OFFICIAL_SERVER), okHttpClient);
        ApiClient.create(BaseHttpUrl.getBaseUrl(DEBUG_LOCAL), okHttpClient);
        ApiClient.create(BaseHttpUrl.getBaseUrl(DEBUG_SERVER), okHttpClient);
        ApiClient.create(BaseHttpUrl.getBaseUrl(OFFICIAL_SERVER), okHttpClient);
        ApiClient.create(NetSchoolHttpUrl.getBaseUrl(DEBUG_SERVER), okHttpClient);
        ApiClient.create(NetSchoolHttpUrl.getBaseUrl(OFFICIAL_SERVER), okHttpClient);
        ApiClient.create("http://res.ytaxx.com/", okHttpClient);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void exitApp() {
        AppManager.getInstance().clear();
        ActivityStack.popAllActivity();
        System.gc();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    private void handlertakePhoto() {
        //尝试修复在部分7.0或以上机型上，选取拍照报FileUriExposedException异常问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.detectFileUriExposure();
        }
    }

    private void initUserInfos() {
        TOKEN = (String) SharedPreferenceUtil.get(this, "token", "");
        Log.i("token", "create: " + BaseApplication.TOKEN);
        if (isDebug) Log.i(TAG, "TOKEN: " + TOKEN);
        UserInfoUtils.getUserInfos(this);
    }

    private void initAppConfig() {
        Constants.AppConfig.DOWNLOAD_TESTOVERICONURL = (String) SharedPreferenceUtil.get(this, Constants.SP.APPCONFIG_DOWNLOAD_ICON, "");
        Constants.AppConfig.APPCONFIG_ALLWINDOW = (String) SharedPreferenceUtil.get(this, Constants.SP.APPCONFIG_ALLWINDOW, "");
    }

    /**
     * 清理缓存，当缓存达到阈值的时候，清理一下
     */
    private void clearCache() {
        try {
            long totalCacheSizeByte = CacheUtils.getTotalCacheSizeByte(this);
            //大于1G的时候删除外部缓存
            if (totalCacheSizeByte > 1024 * 1024 * 1024) {
                CacheUtils.clearExternalCache(this);
            }
        } catch (Exception ignore) {

        }
    }


    public void initWXApi() {
        if (SharedPreferenceUtil.getAgreePrivacyStatus()) {
            iwxapi = WXAPIFactory.createWXAPI(this, WX_APP_ID, true);

            iwxapi.registerApp(WX_APP_ID);
            if (mTencent == null) {
                try {
                    mTencent = Tencent.createInstance(QQappID, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean checkUserLogin() {
        return checkUserLogin(null);
    }

    public static boolean checkUserLogin(Context context) {
        if (!TextUtils.isEmpty(TOKEN)) {
            return true;
        } else {
            if (context != null) {
                ARouter.getInstance().build(RouterActivityPath.Main.PAGER_ONELOGIN).navigation();
            }
            return false;
        }
    }

    private class GlideImageLoader implements NineGridView.ImageLoader {

        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
            RequestOptions options = new RequestOptions()
                    .transform(new CenterCrop(), new RoundedCorners(6))
                    .placeholder(R.drawable.default_2)
                    .error(R.drawable.default_1);
            Glide.with(context)
                    .load(url)
                    .skipMemoryCache(false)
                    .apply(options)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            if (resource instanceof GifDrawable) {
                                //加载一次
                                ((GifDrawable) resource).setLoopCount(-1);
                            }
                            return false;
                        }
                    })
                    .transition(new DrawableTransitionOptions().crossFade())
                    .into(imageView);
        }

        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }

}