package com.alpaca.library_base.utils;

import android.os.Bundle;
import android.util.Log;

import com.alpaca.library_base.base.BaseApplication;
import com.alpaca.library_base.base.activity.MvpBaseActivity;
import com.alpaca.library_base.base.fragment.MvpBaseFragment;
import com.alpaca.library_base.event.ShareSuccEvent;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.greenrobot.eventbus.EventBus;

/**
 * @文件名: qqShareUtils
 * @功能描述:
 * @Date : 2019/3/8
 * @email:
 * @修改记录:
 */
public class qqShareUtils {

    private static String TAG = "qqShareUtils";

    public static void ShareToQQ(final MvpBaseActivity activity, final Bundle params) {
        ThreadManager.getMainHandler().post(new Runnable() {

            @Override
            public void run() {
                if (null != BaseApplication.mTencent) {
                    BaseApplication.mTencent.shareToQQ(activity, params, new IUiListener() {
                        @Override
                        public void onComplete(Object o) {
                            activity.showToast("分享成功");
                            EventBus.getDefault().post(new ShareSuccEvent(ShareSuccEvent.SHARED_TYPE_QQ));
                        }

                        @Override
                        public void onError(UiError uiError) {
                            Log.i(TAG, "onError: " + uiError.errorDetail + uiError.errorMessage + uiError.errorCode);
                            activity.showToast("分享失败");
                        }

                        @Override
                        public void onCancel() {
                            activity.showToast("取消分享");
                        }
                    });
                }
            }
        });
    }

    public static void ShareToQQ(final MvpBaseFragment activity, final Bundle params) {
        ThreadManager.getMainHandler().post(new Runnable() {

            @Override
            public void run() {
                if (null != BaseApplication.mTencent) {
                    BaseApplication.mTencent.shareToQQ(activity.getActivity(), params, new IUiListener() {
                        @Override
                        public void onComplete(Object o) {
                            activity.showToast("分享成功");
                            EventBus.getDefault().post(new ShareSuccEvent(ShareSuccEvent.SHARED_TYPE_QQ));
                        }

                        @Override
                        public void onError(UiError uiError) {
                            Log.i(TAG, "onError: " + uiError.errorDetail + uiError.errorMessage + uiError.errorCode);
                            activity.showToast("分享失败");
                        }

                        @Override
                        public void onCancel() {
                            activity.showToast("取消分享");
                        }
                    });
                }
            }
        });
    }


}
