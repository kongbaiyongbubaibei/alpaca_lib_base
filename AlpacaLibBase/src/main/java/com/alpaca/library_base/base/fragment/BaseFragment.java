package com.alpaca.library_base.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.tu.loadingdialog.LoadingDialog;
import com.alpaca.library_base.base.presenter.BasePresenter;
import com.alpaca.library_base.base.view.IBaseView;
import com.alpaca.library_base.constants.Constants;
import com.alpaca.library_base.module.OnlineStatusBean;
import com.alpaca.library_base.router.RouterActivityPath;
import com.alpaca.library_base.utils.ShowPopWinowUtil;
import com.alpaca.library_base.utils.ToastUtil;
import com.alpaca.library_base.utils.netstatus.NetType;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jessyan.autosize.internal.CustomAdapt;


public abstract class BaseFragment<T extends BasePresenter> extends MvpBaseFragment<T> implements IBaseView, CustomAdapt {

    protected final String TAG = this.getClass().getSimpleName();

    private Unbinder unBinder;

    public boolean isViewCreated;
    public boolean hasLoaded;
    public boolean isRefresh = false;

    protected Context mContext;

    public Activity mActivity;

    protected LoadingDialog dailog;


    static {
        //设置VectorDrawable兼容支持，否则会闪退
        AppCompatDelegate
                .setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
        mContext = getContext();
    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 712;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unBinder = ButterKnife.bind(this, view);
        initView(view);
        EventBus.getDefault().register(this);
        isViewCreated = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((!hasLoaded && isViewCreated) || isRefresh) {
            hasLoaded = true;
            isRefresh = false;
            fetchData();
        }
    }

    @Override
    public void onDestroy() {
        clearAfterDestroy();
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }
        EventBus.getDefault().unregister(this);
        hasLoaded = false;
        isViewCreated = false;
    }

    @Override
    public void onDestroyView() {
        if (presenter != null) {
            presenter.onDestory();
        }
        super.onDestroyView();
    }

    protected void clearAfterDestroy() {
    }

    @Override
    public void showNormal() {
        if (dailog != null) {
            dailog.dismiss();
        }
    }

    @Override
    public void showError(String err) {

    }

    @Override
    public void showLoading() {
        if (dailog == null) dailog = ShowPopWinowUtil.initDialogNew(this);
        dailog.show();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showToastShort(getContext(), msg);
    }

    public abstract int getLayoutId();

    protected abstract void initView(View view);

    protected abstract void fetchData();

    public void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    public String getLexiconName() {
        if (Constants.User.USER_LEXICON != null && !TextUtils.isEmpty(Constants.User.USER_LEXICON.getName())) {
            return Constants.User.USER_LEXICON.getName();
        } else {
            showToast("获得词库信息失败，请切换词库后重试");
            return "";
        }
    }

    public int getLexiconId() {
        if (Constants.User.USER_LEXICON != null) {
            return Constants.User.USER_LEXICON.getId();
        } else {
            showToast("获得词库信息失败，请切换词库后重试");
            return 0;
        }
    }

    public OnlineStatusBean.DataBean getOnlineData() {
        if (Constants.User.USER_ONLINE != null && Constants.User.USER_ONLINE.getData() != null) {
            return Constants.User.USER_ONLINE.getData();
        } else {
            showToast("未获得当前直播状态");
            return null;
        }
    }

    public void setOnlineStatus(int status) {
        if (Constants.User.USER_ONLINE != null && Constants.User.USER_ONLINE.getData() != null) {
            Constants.User.USER_ONLINE.getData().setOnlineStatus(status);
        } else {
            showToast("未获得当前直播状态");
        }
    }

    public boolean getOnlineOrder() {
        if (Constants.User.USER_ONLINE != null && Constants.User.USER_ONLINE.getData() != null) {
            return Constants.User.USER_ONLINE.getData().getAppointment() == 1 ? true : false;
        } else {
            showToast("未获得当前直播状态");
            return false;
        }
    }

    public void setOnlineOrder(boolean isOrder) {
        if (Constants.User.USER_ONLINE != null && Constants.User.USER_ONLINE.getData() != null) {
            Constants.User.USER_ONLINE.getData().setIsAppointment(isOrder);
        } else {
            showToast("未获得当前直播状态");
        }
    }

    public int getOnlineStatus() {
        if (Constants.User.USER_ONLINE != null && Constants.User.USER_ONLINE.getData() != null) {
            return Constants.User.USER_ONLINE.getData().getOnlineStatus();
        } else {
            showToast("获得直播信息失败");
            return 2;
        }
    }

    public String getOnlineTitle() {
        if (Constants.User.USER_ONLINE != null && Constants.User.USER_ONLINE.getData() != null) {
            return Constants.User.USER_ONLINE.getData().getTitle();
        } else {
            showToast("未获得当前直播状态");
            return "";
        }
    }

    public String getOnlineUrl() {
        if (Constants.User.USER_ONLINE != null && Constants.User.USER_ONLINE.getData() != null) {
            return Constants.User.USER_ONLINE.getData().getJumpLink();
        } else {
            showToast("未获得当前直播状态");
            return "";
        }
    }

    public void openOnline() {
        if (TextUtils.isEmpty(getOnlineUrl())) {
            showToast("未获得直播间地址");
        }
        Bundle bundle = new Bundle();
        bundle.putString("webUrl", getOnlineUrl());
        bundle.putString("webTitle", getOnlineTitle());
        bundle.putBoolean("showTitle", true);
        bundle.putBoolean("showShareBtn", true);
        ARouter.getInstance().build(RouterActivityPath.Web.PAGER_WEB).with(bundle).navigation();
    }

    public void UEvent(String key, String value) {
        MobclickAgent.onEvent(getContext(), "key", value);
    }

    public void UEvent(String key) {
        MobclickAgent.onEvent(getContext(), "key");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void netError(NetType netType) {
    }

    public void onEvent(String eventName, String str) {
        MobclickAgent.onEvent(getContext(), eventName, str);
    }

    public void onEvent(String eventName) {
        MobclickAgent.onEvent(getContext(), eventName);
    }

}
