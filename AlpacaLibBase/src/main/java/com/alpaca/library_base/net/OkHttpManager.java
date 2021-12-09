package com.alpaca.library_base.net;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.alpaca.library_base.base.BaseApplication;
import com.alpaca.library_base.utils.AppUtils;
import com.alpaca.library_base.utils.DataPreferences;
import com.alpaca.library_base.utils.DeviceIdUtil;
import com.alpaca.library_base.utils.MateDataUtils;
import com.alpaca.library_base.utils.SharedPreferenceUtil;
import com.lahm.library.EasyProtectorLib;
import com.ut.device.UTDevice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * OkHttpClient管理类
 */
public class OkHttpManager {

    //eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzZTE0YmUzYi1kMWQ1LTQ5OGYtYWQxMi02ZTczYjI5ZGU4ZmIiLCJhdWQiOiJ3ZWIiLCJleHAiOjE1NjY5NTkzNjUsImlhdCI6MTU2NDM2NzM2NX0.8LYYJvvBvQB_MDNnWOgMZ9xgqYix4MgsO-Q_wNxugLWxHQ2o_vJHY7nuG_O0ho6j11hv-nVEwfIfVzONoWIhKQ

    private OkHttpManager() {

    }

    public static OkHttpClient create() {
        return create(null);
    }

    public static OkHttpClient create(ProgressListener progressListener) {
        Interceptor interceptor = new HttpCacheInterceptor();
        if (progressListener != null) {
            interceptor = new HttpProgressInterceptor(progressListener);
        }
        File cacheFile = new File(BaseApplication.getInstance().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> {
            if (!TextUtils.isEmpty(message)) {
                if (message.startsWith("[") || message.startsWith("{")) {
                    printJson("LogUtil", message, "");
                }
                Log.i("LogUtil", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache).proxy(Proxy.NO_PROXY)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(interceptor)
                .addInterceptor(new UrlInerceptor())
                .addInterceptor(
                        chain -> {
                            return chain.proceed(getRequest(chain).build());
                        }
                )
                .cache(cache)
                .build();
        return okHttpClient;
    }

    public static Request.Builder getRequest(Interceptor.Chain chain) {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        if (SharedPreferenceUtil.getAgreePrivacyStatus()) {
//            if (!TextUtils.isEmpty(MacUtils.getMac(BaseApplication.getInstance()))) {
//                builder.addHeader("mac", MacUtils.getMac(BaseApplication.getInstance()));
//            }
            if (!TextUtils.isEmpty(DataPreferences.getInstance().getOaid())) {
                builder.addHeader("oaid", DataPreferences.getInstance().getOaid());
            }
            if (!TextUtils.isEmpty(DataPreferences.getInstance().getUserAgent())) {
                builder.addHeader("userAgent", DataPreferences.getInstance().getUserAgent());
            } else {
                builder.addHeader("userAgent", "alpaca");
            }
            if (!TextUtils.isEmpty(DeviceIdUtil.getAndroidId(BaseApplication.getInstance()))) {
                builder.addHeader("androidId", DeviceIdUtil.getAndroidId(BaseApplication.getInstance()));
            }
            if (!TextUtils.isEmpty(NetWorkUtil.GetNetIp())) {
                builder.addHeader("netIp", NetWorkUtil.GetNetIp());
            }
            builder.addHeader("model", TextUtils.isEmpty(Build.BRAND.concat(Build.MODEL)) ? "alpaca" : Build.BRAND.concat(Build.MODEL));
            builder.addHeader("systemVersion", "Android ".concat(String.valueOf(Build.VERSION.RELEASE)));
            builder.addHeader("appVersion", AppUtils.getVersion());
            builder.addHeader("emulator", String.valueOf(EasyProtectorLib.checkIsRunningInEmulator(BaseApplication.getInstance(), null)));
            builder.addHeader("uuid", TextUtils.isEmpty(UTDevice.getUtdid(BaseApplication.getInstance())) ? AppUtils.getDefaultUUID() : UTDevice.getUtdid(BaseApplication.getInstance()));
            builder.addHeader("sourceBazaar", MateDataUtils.getChannelCode(BaseApplication.getInstance()));
        }
        builder.addHeader("clientType", "android");
        builder.addHeader("serviceVersion", "3");

        String token = BaseApplication.TOKEN;
        if (BaseApplication.isNetSchoolToken) {
            token = DataPreferences.getInstance().getCourseSchoolToken();
        }
        if (!TextUtils.isEmpty(token)) {
            builder.addHeader("token", token);
        }
        Log.i("Log", "create: " + builder.build().headers().toString());
        return builder;
    }


    static class HttpCacheInterceptor implements Interceptor {

        @NonNull
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtil.isNetConnected(BaseApplication.getInstance())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                Log.d("Okhttp", "no network");
            }

            Response originalResponse = chain.proceed(request);
            if (NetWorkUtil.isNetConnected(BaseApplication.getInstance())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                        .removeHeader("Pragma")
                        .build();
            }
        }
    }

    static class HttpProgressInterceptor implements Interceptor {
        private ProgressListener mProgressListener;

        public HttpProgressInterceptor(ProgressListener progressListener) {
            mProgressListener = progressListener;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse
                    .newBuilder()
                    .body(new ProgressResponseBody(originalResponse, mProgressListener))
                    .build();
        }
    }

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void printJson(String tag, String msg, String headString) {
        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(4);//最重要的方法,就一行,返回格式化的json字符串,其中的数字4是缩进字符数
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }
        printLine(tag, true);
        message = headString + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            Log.i(tag, "║ " + line);
        }
        printLine(tag, false);
    }

    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.i(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.i(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }
}
