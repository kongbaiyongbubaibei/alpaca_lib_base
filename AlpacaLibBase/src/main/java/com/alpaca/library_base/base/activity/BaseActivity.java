package com.alpaca.library_base.base.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.tu.loadingdialog.LoadingDialog;
import com.alpaca.library_base.R;
import com.alpaca.library_base.base.AppManager;
import com.alpaca.library_base.base.presenter.BasePresenter;
import com.alpaca.library_base.base.view.IBaseView;
import com.alpaca.library_base.constants.Constants;
import com.alpaca.library_base.event.OnlineComeEvent;
import com.alpaca.library_base.manager.ActivityStack;
import com.alpaca.library_base.module.AppConfig;
import com.alpaca.library_base.router.RouterActivityPath;
import com.alpaca.library_base.utils.ActivityController;
import com.alpaca.library_base.utils.AppConfigUtils;
import com.alpaca.library_base.utils.CustomSpannableStringBuilder;
import com.alpaca.library_base.utils.ShowFlowDialogUtils;
import com.alpaca.library_base.utils.ShowPopWinowUtil;
import com.alpaca.library_base.utils.SpannableUtil;
import com.alpaca.library_base.utils.ToastUtil;
import com.alpaca.library_base.utils.WxShareUtil;
import com.alpaca.library_base.utils.netstatus.NetType;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jessyan.autosize.internal.CustomAdapt;

public abstract class BaseActivity<T extends BasePresenter> extends MvpBaseActivity<T> implements IBaseView, CustomAdapt {

    private Unbinder unBinder;

    protected BaseActivity mActivity;

    public boolean isRefresh = false;

    protected final String TAG = this.getClass().getSimpleName();

    private LoadingDialog dailog;

    protected void initExtraData() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unBinder = ButterKnife.bind(this);
        ActivityStack.pushActivity(this);
        MobclickAgent.onEvent(this, "viewDidAppear", TAG);
        mActivity = this;
        if (autoInitSystemBar()) {
            initSystemBar(true);
        }
        initExtraData();
        initView();
        EventBus.getDefault().register(this);
        initData();
    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 712;
    }

    public void initSystemBar(Boolean isLight, int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);

            View decor = window.getDecorView();
            int ui = decor.getSystemUiVisibility();
            if (isLight) {
                ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            decor.setSystemUiVisibility(ui);
        }
    }


    public void initSystemBar(Boolean isLight) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (isLight) {
                window.setStatusBarColor(getResources().getColor(R.color.white));
            } else {
                window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
            }

            View decor = window.getDecorView();
            int ui = decor.getSystemUiVisibility();
            if (isLight) {
                ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            decor.setSystemUiVisibility(ui);
        }
    }

    /**
     * 是否自动开启默认状态栏颜色配置
     */
    public boolean autoInitSystemBar() {
        return true;
    }

    /**
     * 设置透明状态栏
     */
    public void setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    protected void onDestroy() {
        ActivityStack.popActivity(this);
        unBinder.unbind();
        if (presenter != null) {
            presenter.onDestory();
        }
        EventBus.getDefault().unregister(this);
        clearAfterDestroy();
        super.onDestroy();
    }

    protected void clearAfterDestroy() {
    }


    public void showNormal() {
        if (dailog != null) {
            dailog.dismiss();
        }
    }
    public void setLoadingText(String text) {
        if (dailog == null) return;
        if (!TextUtils.isEmpty(text)) {
            TextView tvMessage = dailog.findViewById(R.id.tipTextView);
            if (tvMessage != null) {
                tvMessage.setText(text);
            }
//            dailog = ShowPopWinowUtil.initDialog(getContext(), text);
        }
    }

    public void showError(String error) {

    }

    public void showLoading() {
        if (dailog == null) dailog = ShowPopWinowUtil.initDialog(getContext());
        dailog.show();
    }

    public void showEmpty() {

    }

    public void reload() {

    }

    public void showToast(String msg) {
        ToastUtil.showToastShort(this, msg);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onPause() {
        super.onPause();
        isRefresh = true;
    }

    /**
     * 重写 getResource 方法，防止系统字体影响
     */
    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        if (resources != null) {
            Configuration configuration = new Configuration();
            //禁止app字体大小跟随系统字体大小调节
            //所有属性都恢复默认值，包括字体大小等属性
            configuration.setToDefaults();
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
        return resources;
    }

    public BaseActivity getContext() {
        return this;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void netError(NetType netType) {
        if (AppManager.getInstance().getForwardActivity().getClass().getName().equals(this.getClass().getName())) {
            if (netType.equals(NetType.NONE)) {
                ShowPopWinowUtil.showNewChooseDialog(this, "网络连接出了问题\n请检查您的网络连接\n或者允许" + getResources().getString(R.string.app_name) + "访问网络数据");
            }
        }
    }

    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);

        }
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

    public void setOnlineStatus(int status) {
        if (Constants.User.USER_ONLINE != null && Constants.User.USER_ONLINE.getData() != null) {
            Constants.User.USER_ONLINE.getData().setOnlineStatus(status);
        } else {
            showToast("未获得当前直播状态");
        }
    }

    public void setOnlineOrder(boolean isOrder) {
        if (Constants.User.USER_ONLINE != null && Constants.User.USER_ONLINE.getData() != null) {
            Constants.User.USER_ONLINE.getData().setIsAppointment(isOrder);
        } else {
            showToast("未获得当前直播状态");
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

    public int getOnlineStatus() {
        if (Constants.User.USER_ONLINE != null && Constants.User.USER_ONLINE.getData() != null) {
            return Constants.User.USER_ONLINE.getData().getOnlineStatus();
        } else {
            showToast("获得直播信息失败");
            return 2;
        }
    }

    /**
     * 直播即将开始通知
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onlineCome(OnlineComeEvent onlineComeEvent) {
        if (ActivityController.getCurrentActivity().getClass().getName().equals(this.getClass().getName())) {
            ShowFlowDialogUtils.showOrderDialog(getContext(), true, getOnlineTitle(), view -> openOnline());
        }
    }

    public void UEvent(String key, String value) {
        MobclickAgent.onEvent(getContext(), key, value);
    }

    public void UEvent(String key) {
        MobclickAgent.onEvent(getContext(), key);
    }

    public void showFlowDialog() {
        AppConfig.DataBean.AllWindowData winType7 = AppConfigUtils.getAllWindowByType(AppConfigUtils.WINTYPE_7);
        String wxCode7 = winType7 == null ? "" : winType7.getWinWeixin();
        String miniPath = winType7 == null ? "" : winType7.getMiniprogramPath();
        if (!TextUtils.isEmpty(miniPath)) {
            WxShareUtil.openMiniProgram(miniPath);
        } else {
            String popContent = "加入魔鬼训练营\n全套视频+名师代练";
            String keyPopContent = "全套视频+";
            CustomSpannableStringBuilder titleBuilder = SpannableUtil.changeTextColor(
                    Color.parseColor("#ffff33"), popContent, keyPopContent);
            ShowFlowDialogUtils.showCommonDialog(getContext(), "全套解锁", titleBuilder,
                    "备考顾问微信号：" + wxCode7,
                    "1.打开微信选择“+”菜单中“添加好友”\n2.粘贴微信号即可搜索并添加顾问",
                    R.drawable.intro_20200313, wxCode7);
        }

    }

    public void showNotCancelLoading() {
        if (dailog == null) dailog = ShowPopWinowUtil.initDialog(getContext());
        dailog.setCancelable(false);
        dailog.show();
    }


    public void onEvent(String eventName, String str) {
        MobclickAgent.onEvent(getContext(), eventName, str);
    }

    public void onEvent(String eventName) {
        MobclickAgent.onEvent(getContext(), eventName);
    }
}
