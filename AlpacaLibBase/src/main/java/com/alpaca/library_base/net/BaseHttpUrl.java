package com.alpaca.library_base.net;

import com.alpaca.library_base.base.BaseApplication;

/**
 * @文件名: HttpUrl
 * @功能描述:
 * @Date: 2018/1/4
 * @email:
 * @修改记录:
 */
public class BaseHttpUrl {

    /**
     * 主机地址常量保存类
     */
    public static class BaseURL {
        public static String BASE_URL = getBaseUrl(BaseApplication.CURRENT_DEBUG_TYPE);
    }

    public static String getBaseUrl(int current) {
        //"http://192.168.9.46:8858/"王顶本地

        //"http://39.100.158.126:8858/"测试地址
        //"https://tapijp.ytaxx.com/", "https://jp.ytaxx.com/"
        String[] baseUrls = {"http://192.168.43.238:8850/", "http://39.100.158.126:8876/", "https://premiere.yangtuoedu.com/"};

        return baseUrls[current];
    }
}
