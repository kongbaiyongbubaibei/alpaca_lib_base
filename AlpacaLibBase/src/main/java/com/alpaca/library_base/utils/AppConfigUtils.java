package com.alpaca.library_base.utils;

import com.alpaca.library_base.constants.Constants;
import com.alpaca.library_base.module.AppConfig;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

/**
 * class desc :
 * <p>
 * appCfg接口返回数据的工具类
 *
 * @author : zzh
 * @date : 2019/11/12
 */
public class AppConfigUtils {

    //allwindow字段配置微信号type
    public static final String WINTYPE_4 = "winType9";//许愿池微信
    public static final String WINTYPE_5 = "winType4";//课程-直播课微信
    public static final String WINTYPE_7 = "winType6";//课程-公开课微信
    public static final String WINTYPE_8 = "winType11";//我的-配音社群邀请函
    public static final String WINTYPE_9 = "winType10";//我的-文化社群邀请函
    public static final String WINTYPE_10 = "winType5";//首页-定制学习
    public static final String WINTYPE_11 = "winType8";//我的-公众号
    public static final String WINTYPE_13 = "winType7";//课程-课程
    public static final String MINIPROGRAMPATH = "classWx";//小程序路径

    public static AppConfig appConfig;

    /**
     * 获取allwindow字段下的map集合中指定数据
     * 该数据为app中各个微信号配置
     *
     * @param type 获取指定type
     * @return 微信号配置实体
     */
    public static AppConfig.DataBean.AllWindowData getAllWindowByType(String type) {
        Map<String, AppConfig.DataBean.AllWindowData> map = GsonUtil.fromJson(Constants.AppConfig.APPCONFIG_ALLWINDOW,
                TypeToken.getParameterized(Map.class, String.class, AppConfig.DataBean.AllWindowData.class).getType());
        return map == null ? null : map.get(type);
    }

    public static void saveAppConfig(AppConfig appConfig) {
        AppConfigUtils.appConfig = appConfig;
    }

    public static AppConfig.DataBean getAppConfigData() {
        if (appConfig != null) {
            return appConfig.getData();
        }
        return new AppConfig.DataBean();
    }
}
