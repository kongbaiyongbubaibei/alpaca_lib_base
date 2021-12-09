package com.alpaca.library_base.net;

import com.alpaca.library_base.base.BaseApplication;

/**
 * @文件名: HttpUrl
 * @功能描述:
 * @Date: 2018/1/4
 * @email:
 * @修改记录:
 */
public class NetSchoolHttpUrl {

    public static boolean HCADETS = false;
    /**
     * 主机地址常量保存类
     */
    public static class BaseURL {
        public static String BASE_URL = getBaseUrl(BaseApplication.CURRENT_DEBUG_TYPE);
    }

    public static String getBaseUrl(int current) {
        //"http://192.168.9.46:8858/"王顶本地

        //"https://tapijp.ytaxx.com/"测试地址
        //"https://jp.ytaxx.com/"正式地址
//        String[] baseUrls = {"http://192.168.43.238:8850/", "http://test.ke.ytaxx.com/", "http://test.ke.ytaxx.com/"};
        String[] baseUrls = {"http://192.168.43.238:8850/", "http://test.ke.ytaxx.com/", "http://kt.ytaxx.com/"};

        return baseUrls[current];
    }
}
