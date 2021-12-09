package com.alpaca.library_base.base.fragment;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;

import com.alpaca.library_base.base.presenter.MvpPresenter;
import com.alpaca.library_base.base.view.IMvpBaseView;
import com.alpaca.library_base.utils.PermissionHelper;
import com.alpaca.library_base.utils.ToastUtil;
import com.umeng.analytics.MobclickAgent;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.fragment.app.Fragment;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class MvpBaseFragment<T extends MvpPresenter> extends Fragment implements IMvpBaseView {

    protected T presenter;
    protected ConcurrentHashMap<String, String> mValueMap = new ConcurrentHashMap<>();
    protected CompositeDisposable compositeDisposable; //管理事件订阅
    protected ArrayMap<String, Disposable> disposableMap;
    public MyHandler mHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new MyHandler(this);
        presenter = createPresenter();
        MobclickAgent.onEvent(getContext(), "viewDidAppear", this.getClass().getSimpleName());
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
    }

    protected abstract T createPresenter();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    /**
     * 从上个页面取得传递参数的值
     *
     * @param name
     * @return
     */
    public String getValueFromPrePage(String name) {
        return mValueMap.get(name);
    }

    /**
     * 将上个页面传递过来的参数值全部放到valueMap 中
     */
    public void initValueFromPrePage() {
        Bundle extBundle = getArguments() != null ? getArguments() : null;
        if (extBundle != null) {
            Iterator<String> it = extBundle.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                String value = extBundle.getString(key);
                mValueMap.put(key, value);
            }
        }

    }

    /**
     * 添加事件监听处理到 事件管理类
     *
     * @param disposable 上流事件
     */
    protected void addSubscription(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    /**
     * 添加事件监听处理到 事件管理类
     *
     * @param tag        标识符
     * @param disposable 上流事件
     */
    protected void addSubscription(String tag, Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        if (disposableMap == null) {
            disposableMap = new ArrayMap<>();
        }
        disposableMap.put(tag, disposable);
        compositeDisposable.add(disposable);
    }


    /**
     * RxJava取消注册，避免内存泄露
     * 取消以后就只能重新新建一个了
     */
    protected void onUnsubscribe() {
        if (compositeDisposable != null) {
            // Using clear will clear all, but can accept new disposable
//            compositeDisposable.clear();
            // Using dispose will clear all and set isDisposed = true, so it will not accept any new disposable
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
        if (disposableMap != null && disposableMap.size() > 0) {
            disposableMap.clear();
        }
    }

    /**
     * 根据标识符移除Disposable
     *
     * @param tags 标识符
     */
    protected void removeDisposableByTag(String... tags) {
        if (disposableMap != null && disposableMap.size() > 0) {
            for (String tag : tags) {
                if (disposableMap.containsKey(tag)) {
                    compositeDisposable.remove(disposableMap.get(tag));
                    disposableMap.remove(tag);
                }
            }
        }
    }



    public void showToast(String msg) {
        ToastUtil.showToastShort(getContext(), msg);
    }

    protected void handleMessage(Message msg) {
    }
    public static class MyHandler extends android.os.Handler {

        private WeakReference<MvpBaseFragment> mWeakReference;

        public MyHandler(MvpBaseFragment weakReference) {
            super(Looper.getMainLooper());
            mWeakReference = new WeakReference<>(weakReference);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            MvpBaseFragment baseCompatFragment = mWeakReference.get();
            if (null != baseCompatFragment) {
                baseCompatFragment.handleMessage(msg);
            }
        }
    }

}
