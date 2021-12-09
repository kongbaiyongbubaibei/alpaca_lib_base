package com.alpaca.library_base.utils;

import com.alpaca.library_base.constants.Constants;
import com.alpaca.library_base.module.WeChatBean;
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
public class WxConfigUtils {

    //allwindow字段配置微信号type
    public static final String MINIPROGRAMPATH = "classWx";//小程序路径
    public static final String BRUSH_WX = "brush_vx";//笔刷
    public static final String ABOUT = "about_android";//约稿图片

    public static WeChatBean weChatBean;

    /**
     * 获取allwindow字段下的map集合中指定数据
     * 该数据为app中各个微信号配置
     *
     * @param type 获取指定type
     * @return 微信号配置实体
     */
    public static WeChatBean.DataBean.AllWindowData getAllWindowByType(String type) {
        Map<String, WeChatBean.DataBean.AllWindowData> map = GsonUtil.fromJson(Constants.AppConfig.WXCONFIG_ALLWINDOW,
                TypeToken.getParameterized(Map.class, String.class, WeChatBean.DataBean.AllWindowData.class).getType());
        return map == null ? null : map.get(type);
    }

    public static void saveWxConfig(WeChatBean weChatBean) {
        WxConfigUtils.weChatBean = weChatBean;
    }

    public static WeChatBean.DataBean getAppConfigData() {
        if (weChatBean != null) {
            return weChatBean.getData();
        }
        return new WeChatBean.DataBean();
    }
}
