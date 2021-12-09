package com.alpaca.library_base.utils;

import com.alpaca.library_base.event.UploadPicturesSuccessEvent;
import com.alpaca.library_base.event.UploadSinglePicturesSuccessEvent;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @文件名: QiNiuUtils
 * @功能描述:
 * @Date : 2020/11/16
 * @email:
 * @修改记录:
 */
public class QiNiuUtils {

    /**
     * 描述： 七牛上传多图
     *
     * @param imagePath 图片的本地路径
     * @param picSize 上传图片总数量
     * @param mHandler Handler
     * @author JayGengi
     * @date 2018/7/27 0027 下午 5:26
     */
    public static ArrayList<String> resultImagePath = new ArrayList<>();

    public static void getUpimg(String token, String imagePath, int picSize) {

        new Thread() {
            private SimpleDateFormat sdf;
            private UploadManager uploadManager;

            public void run() {
                // 图片上传到七牛 重用 uploadManager
                uploadManager = new UploadManager();
                sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                // 设置图片名字
                String key = "icon_" + sdf.format(new Date()) + imagePath;
                uploadManager.put(imagePath, key, token,
                        new UpCompletionHandler() {
                            @Override
                            public void complete(String key, ResponseInfo info,
                                                 JSONObject res) {
                                // 七牛返回的文件名
                                resultImagePath.add("http://res.ytaxx.com/" + key);
                                //将七牛返回图片的文件名添加到list集合中
                                if (picSize == resultImagePath
                                        .size()) {
                                    EventBus.getDefault().post(new UploadPicturesSuccessEvent(resultImagePath));
                                }
                            }
                        }, null);
            }
        }.start();
    }

    public static void getUpSingleImage(String token, String imagePath) {

        new Thread() {
            private SimpleDateFormat sdf;
            private UploadManager uploadManager;

            public void run() {
                // 图片上传到七牛 重用 uploadManager
                uploadManager = new UploadManager();
                sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                // 设置图片名字
                String key = "icon_" + sdf.format(new Date()) + imagePath;
                uploadManager.put(imagePath, key, token,
                        new UpCompletionHandler() {
                            @Override
                            public void complete(String key, ResponseInfo info,
                                                 JSONObject res) {
                                // 七牛返回的文件名
                                String path = "http://res.ytaxx.com/" + key;
                                //将七牛返回图片的文件名添加到list集合中
                                EventBus.getDefault().post(new UploadSinglePicturesSuccessEvent(path));
                            }
                        }, null);
            }
        }.start();
    }

    public static void clearList() {
        resultImagePath.clear();
    }

}
