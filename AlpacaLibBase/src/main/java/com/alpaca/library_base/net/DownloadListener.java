package com.alpaca.library_base.net;

/**
 * @文件名: DownloadListener
 * @功能描述:
 * @Date : 2019/10/24
 * @email:
 * @修改记录:
 */
public interface DownloadListener {

    void onStart();//下载开始

    void onProgress(int progress);//下载进度

    void onFinish(String path);//下载完成

    void onFail(String errorInfo);//下载失败

    void onExist();//文件已存在
}
