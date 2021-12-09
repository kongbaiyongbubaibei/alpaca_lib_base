package com.alpaca.library_base.base.presenter;


import com.alpaca.library_base.base.view.IMvpBaseView;

import java.lang.ref.WeakReference;

import androidx.collection.ArrayMap;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class MvpPresenter<T extends IMvpBaseView> implements IMvpPresenter{

    private WeakReference<T> weakReference;

    protected T mView;
    protected CompositeDisposable compositeDisposable; //管理事件订阅
    protected ArrayMap<String, Disposable> disposableMap;

    public MvpPresenter(T mView) {
        this.mView = mView;
    }

    public void attachView(T view){
        weakReference = new WeakReference<>(view);
    }

    public boolean isViewAttached() {
        return weakReference != null && weakReference.get() != null;
    }

    public T getView() {
        return weakReference.get();
    }

    public void detachView(){
        if (isViewAttached()) {
            weakReference.clear();
            weakReference = null;
        }
    }

    @Override
    public void onDestory() {
        onUnsubscribe();
    }

    @Override
    public void onStop() {

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


}
