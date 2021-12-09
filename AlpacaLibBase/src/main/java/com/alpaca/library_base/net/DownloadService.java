package com.alpaca.library_base.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @文件名: DownloadService
 * @功能描述:
 * @Date : 2019/10/24
 * @email:
 * @修改记录:
 */
public interface DownloadService {
    /**
     * 文件下载
     *
     * @return
     */
    @Streaming
    @GET
    Call<ResponseBody> download(@Url() String url);
}
