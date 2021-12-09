package com.alpaca.library_base.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.alpaca.library_base.base.AppManager;
import com.alpaca.library_base.base.presenter.MvpPresenter;
import com.alpaca.library_base.base.view.IMvpBaseView;
import com.alpaca.library_base.utils.ActivityController;
import com.alpaca.library_base.utils.PermissionHelper;
import com.alpaca.library_base.utils.ToastUtil;
import com.umeng.analytics.MobclickAgent;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class MvpBaseActivity<T extends MvpPresenter> extends AppCompatActivity implements IMvpBaseView {

    protected T presenter;
    public MyHandler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new MyHandler(this);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
        ActivityController.setCurrentActivity(this);
        ActivityController.addActivity(this);
        AppManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        ActivityController.removeActivity(this);
        AppManager.getInstance().removeActivity(this);
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }

    protected void handleMes(Message msg) {
    }

    protected abstract T createPresenter();


    public static class MyHandler extends Handler {

        private final WeakReference<MvpBaseActivity> mActivity;

        public MyHandler(MvpBaseActivity activity) {
            super(Looper.getMainLooper());
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MvpBaseActivity activity = mActivity.get();
            if (activity != null) {
                // do someThing
                activity.handleMes(msg);
            }
        }
    }

    public void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * startActivity then finish
     *
     * @param clazz
     */
    public void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    public void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivity with bundle then finish
     *
     * @param clazz
     * @param bundle
     */
    public void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    public void showToast(String msg) {
        ToastUtil.showToastShort(this, msg);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    public void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);

    }

    public void back() {
    }
}
