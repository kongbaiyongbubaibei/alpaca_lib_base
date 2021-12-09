package com.alpaca.library_base.net;


import com.alpaca.library_base.constants.Constants;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @文件名: UrlInerceptor
 * @功能描述:
 * @Date : 2020/3/13
 * @email:
 * @修改记录:
 */
public class UrlInerceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取request
        Request request = chain.request();
        //从request中获取原有的HttpUrl实例oldHttpUrl
        okhttp3.HttpUrl oldHttpUrl = request.url();
        //获取request的创建者builder
        Request.Builder builder = request.newBuilder();
        //从request中获取headers，通过给定的键url_name
        List<String> headerValues = request.headers("url_name");
        if (headerValues != null && headerValues.size() > 0) {
            //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
            builder.removeHeader("url_name");
            //匹配获得新的BaseUrl
            String headerValue = headerValues.get(0);
            okhttp3.HttpUrl newBaseUrl = null;
            if ("pte".equals(headerValue)) {
                newBaseUrl = okhttp3.HttpUrl.parse(Constants.Net.PTE_HOST);
            } else {
                newBaseUrl = okhttp3.HttpUrl.parse(BaseHttpUrl.BaseURL.BASE_URL);
            }
            //重建新的HttpUrl，修改需要修改的url部分
            okhttp3.HttpUrl newFullUrl = oldHttpUrl
                    .newBuilder()
                    .scheme(newBaseUrl.scheme())//http协议如：http或者https
                    .host(newBaseUrl.host())//主机地址
                    .port(newBaseUrl.port())//端口
                    .build();
            //重建这个request，通过builder.url(newFullUrl).build()；
            // 然后返回一个response至此结束修改
            return chain.proceed(builder.url(newFullUrl).build());
        } else {
            Request request2 = builder.build();
            return chain.proceed(request2);
        }
    }
}
