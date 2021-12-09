package com.alpaca.library_base.utils;

import android.Manifest;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDialog;
import com.alpaca.library_base.R;
import com.alpaca.library_base.base.BaseApplication;
import com.alpaca.library_base.base.activity.BaseActivity;
import com.alpaca.library_base.base.activity.MvpBaseActivity;
import com.alpaca.library_base.base.fragment.MvpBaseFragment;
import com.alpaca.library_base.constants.Constants;
import com.alpaca.library_base.event.EditUserIconEvent;
import com.alpaca.library_base.event.EditUserSexEvent;
import com.alpaca.library_base.widgets.SelectDialog;
import com.tencent.connect.share.QQShare;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * @文件名: ShowPopWinowUtil
 * @功能描述:
 * @Date : 2018/5/15
 * @email:
 * @修改记录:
 */
public class ShowPopWinowUtil {
    private static final String TAG = "ShowPopWinowUtil";
    public static int PRACTICE_BACK_POP = 289;
    public static int FINISH_STUDY_GO_PRACTICE = 290;


    public static LoadingDialog initDialogNew(MvpBaseFragment activity, String str) {
        LoadingDialog.Builder dialog = new LoadingDialog.Builder(activity.getActivity())
                .setMessage(str)
                .setCancelable(true)
                .setCancelOutside(true);
        LoadingDialog dialog1 = dialog.create();
        dialog1.getWindow().setDimAmount(0f);
        return dialog1;
    }

    public static LoadingDialog initDialogNew(MvpBaseFragment activity) {
        LoadingDialog.Builder dialog = new LoadingDialog.Builder(activity.getActivity())
                .setMessage("Loading....")
                .setCancelable(true)
                .setCancelOutside(true);
        LoadingDialog dialog1 = dialog.create();
        dialog1.getWindow().setDimAmount(0f);
        return dialog1;
    }

    public static LoadingDialog initDialog(MvpBaseActivity activity, String str) {
        LoadingDialog.Builder dialog = new LoadingDialog.Builder(activity)
                .setMessage(str)
                .setCancelable(true)
                .setCancelOutside(true);
        LoadingDialog dialog1 = dialog.create();
        dialog1.getWindow().setDimAmount(0f);
        return dialog1;
    }

    public static LoadingDialog initDialog(MvpBaseActivity activity) {
        LoadingDialog.Builder dialog = new LoadingDialog.Builder(activity)
                .setMessage("Loading....")
                .setCancelable(true)
                .setCancelOutside(true);
        LoadingDialog dialog1 = dialog.create();
        dialog1.getWindow().setDimAmount(0f);
        return dialog1;
    }


    public static void showEditSexDialog(MvpBaseActivity activity) {
        initSelectSexDialog(activity);
    }

    private static void initSelectSexDialog(final MvpBaseActivity activity) {
        List<String> names = new ArrayList<>();
        names.add("女生");
        names.add("男生");
        showDialog(activity, new SelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        EventBus.getDefault().post(new EditUserSexEvent(1));
                        break;
                    case 0:
                        EventBus.getDefault().post(new EditUserSexEvent(2));
                        break;
                    default:
                        break;
                }

            }
        }, names, "请选择你的性别");
    }

    public static SelectDialog showDialog(MvpBaseActivity activity, SelectDialog.SelectDialogListener selectDialogListener, List<String> names, String title) {
        SelectDialog dialog = new SelectDialog(activity, R.style
                .transparentFrameWindowStyle,
                selectDialogListener, names, title);
        if (!activity.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }


    public static void showTakePhoto(MvpBaseActivity activity) {
        PermissionHelper.runOnPermissionGranted(activity, () -> {
                    initSelectDialog(activity);
                }, () -> {
                    activity.showToast("请给予对应权限");
                }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA);

    }

    private static void initSelectDialog(final MvpBaseActivity activity) {
        List<String> names = new ArrayList<>();
        names.add("拍照");
        names.add("相册");
        showDialog(activity, new SelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        takePhotos(activity);
                        break;
                    case 1:
                        pickPhotos(activity);
                        break;
                    default:
                        break;
                }

            }
        }, names);
    }


    public static SelectDialog showDialog(MvpBaseActivity activity, SelectDialog.SelectDialogListener selectDialogListener, List<String> names) {
        SelectDialog dialog = new SelectDialog(activity, R.style
                .transparentFrameWindowStyle,
                selectDialogListener, names);
        if (!activity.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    private static void takePhotos(Context context) {
        GalleryFinal.init(new ImagePickerUtils().initImagePicker(context));
        GalleryFinal.openCamera(2, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, final List<PhotoInfo> resultList) {
                if (resultList != null && resultList.size() > 0) {
                    EventBus.getDefault().post(new EditUserIconEvent(resultList.get(0).getPhotoPath()));
                }
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });
    }

    private static void pickPhotos(Context context) {
        GalleryFinal.init(new ImagePickerUtils().initImagePicker(context));
        GalleryFinal.openGalleryMuti(1, 1, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, final List<PhotoInfo> resultList) {
                if (resultList != null && resultList.size() > 0) {
                    EventBus.getDefault().post(new EditUserIconEvent(resultList.get(0).getPhotoPath()));
                }
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });
    }


    public static void showShareLink(final BaseActivity activity, final String path, final String title, final String uurl, final int drawAble) {
        final PopupWindow popupWindowPic = new PopupWindow(activity);
        popupWindowPic.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindowPic.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        View view0 = LayoutInflater.from(activity).inflate(R.layout.layout_popupwindow_style23, null);
        popupWindowPic.setContentView(view0);
        popupWindowPic.setBackgroundDrawable(new ColorDrawable(0x33000000));
        popupWindowPic.setOutsideTouchable(false);
        popupWindowPic.setFocusable(true);
        View wechat = view0.findViewById(R.id.rl_wechat);
        View qq = view0.findViewById(R.id.rl_qq);
        View circle = view0.findViewById(R.id.rl_circle);
        View net = view0.findViewById(R.id.rl_net);
        View url = view0.findViewById(R.id.rl_copy);
        TextView back = view0.findViewById(R.id.tv_cancel);
        RelativeLayout rlBack = view0.findViewById(R.id.test_share_back);
//        if (System.currentTimeMillis() < 1601620149000l) {
//            wechat.setVisibility(View.GONE);
//            circle.setVisibility(View.GONE);
//        }

        if (BaseApplication.is_Google) {
            net.setVisibility(View.GONE);
        }
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowPic.dismiss();
            }
        };
        back.setOnClickListener(listener);
        rlBack.setOnClickListener(listener);
        View rootview = LayoutInflater.from(activity).inflate(R.layout.activity_lib, null);
        popupWindowPic.showAtLocation(rootview, Gravity.BOTTOM, 0, HuaWeiBottomUtils.getNavigationBarHeight(activity));
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IsInstallWeChatOrAliPay.isQQClientAvailable(activity)) {
                    PackageManager pm = activity.getPackageManager();
                    ApplicationInfo pkgInfo = null;
                    try {
                        pkgInfo = pm.getApplicationInfo(activity.getPackageName(), 0);
                        Bundle params = new Bundle();
                        params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
                        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, path);
                        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, Constants.APK_LOGO_PATH);
                        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, uurl);
                        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, pkgInfo.loadLabel(pm).toString());
                        params.putString(QQShare.SHARE_TO_QQ_ARK_INFO, Constants.APK_SHARE_PATH);
                        qqShareUtils.ShareToQQ(activity, params);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    activity.showToast("您的设备还未安装该软件");
                }
                popupWindowPic.dismiss();
            }
        });
        wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IsInstallWeChatOrAliPay.isWeixinAvilible(activity)) {
                    WxShareUtil.shareUrl(activity, title, uurl, path, drawAble, "");
                } else {
                    activity.showToast("您的设备还未安装该软件");
                }
                popupWindowPic.dismiss();
            }
        });
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IsInstallWeChatOrAliPay.isWeixinAvilible(activity)) {
                    WxShareUtil.shareUrl(activity, title, uurl, path, 2);
                } else {
                    activity.showToast("您的设备还未安装该软件");
                }
                popupWindowPic.dismiss();
            }
        });
        net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(path);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
                popupWindowPic.dismiss();
            }
        });
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(path);
                activity.showToast("复制成功，可以发给朋友们了");
                popupWindowPic.dismiss();
            }
        });

    }

    public static void showShareLink(final MvpBaseFragment activity, final String path, final String title, final String uurl, final int drawAble) {
        final PopupWindow popupWindowPic = new PopupWindow(activity.getContext());
        popupWindowPic.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindowPic.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        View view0 = LayoutInflater.from(activity.getContext()).inflate(R.layout.layout_popupwindow_style23, null);
        popupWindowPic.setContentView(view0);
        popupWindowPic.setBackgroundDrawable(new ColorDrawable(0x33000000));
        popupWindowPic.setOutsideTouchable(false);
        popupWindowPic.setFocusable(true);
        View wechat = view0.findViewById(R.id.rl_wechat);
        View qq = view0.findViewById(R.id.rl_qq);
        View circle = view0.findViewById(R.id.rl_circle);
        View net = view0.findViewById(R.id.rl_net);
        View url = view0.findViewById(R.id.rl_copy);
        TextView back = view0.findViewById(R.id.tv_cancel);
        RelativeLayout rlBack = view0.findViewById(R.id.test_share_back);
//        if (System.currentTimeMillis() < 1601620149000l) {
//            wechat.setVisibility(View.GONE);
//            circle.setVisibility(View.GONE);
//        }

        if (BaseApplication.is_Google) {
            net.setVisibility(View.GONE);
        }
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowPic.dismiss();
            }
        };
        back.setOnClickListener(listener);
        rlBack.setOnClickListener(listener);
        View rootview = LayoutInflater.from(activity.getContext()).inflate(R.layout.activity_lib, null);
        popupWindowPic.showAtLocation(rootview, Gravity.BOTTOM, 0, HuaWeiBottomUtils.getNavigationBarHeight(activity.getContext()));
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IsInstallWeChatOrAliPay.isQQClientAvailable(activity.getContext())) {
                    PackageManager pm = activity.getActivity().getPackageManager();
                    ApplicationInfo pkgInfo = null;
                    try {
                        pkgInfo = pm.getApplicationInfo(activity.getActivity().getPackageName(), 0);
                        Bundle params = new Bundle();
                        params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
                        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, path);
                        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, Constants.APK_LOGO_PATH);
                        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, uurl);
                        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, pkgInfo.loadLabel(pm).toString());
                        params.putString(QQShare.SHARE_TO_QQ_ARK_INFO, Constants.APK_SHARE_PATH);
                        qqShareUtils.ShareToQQ(activity, params);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    activity.showToast("您的设备还未安装该软件");
                }
                popupWindowPic.dismiss();
            }
        });
        wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IsInstallWeChatOrAliPay.isWeixinAvilible(activity.getContext())) {
                    WxShareUtil.shareUrl(activity.getContext(), title, uurl, path, drawAble, "");
                } else {
                    activity.showToast("您的设备还未安装该软件");
                }
                popupWindowPic.dismiss();
            }
        });
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IsInstallWeChatOrAliPay.isWeixinAvilible(activity.getContext())) {
                    WxShareUtil.shareUrl(activity.getContext(), title, uurl, path, 2);
                } else {
                    activity.showToast("您的设备还未安装该软件");
                }
                popupWindowPic.dismiss();
            }
        });
        net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(path);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                activity.startActivity(intent);
                popupWindowPic.dismiss();
            }
        });
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) activity.getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(path);
                activity.showToast("复制成功，可以发给朋友们了");
                popupWindowPic.dismiss();
            }
        });

    }


    public static void showNewChooseDialog(final MvpBaseActivity activity, String str) {
        View rootview = LayoutInflater.from(activity).inflate(R.layout.activity_lib, null);
        final PopupWindow popupWindow = new PopupWindow(activity);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        View contentView = LayoutInflater.from(activity).inflate(R.layout.layout_popupwindow_style10, null);
        TextView tvPost = contentView.findViewById(R.id.dialog_right);
        TextView tvCanncle = contentView.findViewById(R.id.dialog_left);
        TextView tvContent = contentView.findViewById(R.id.dialog_tv);
        tvContent.setText(str);
        tvCanncle.setText("设置");
        tvPost.setText("好");
        popupWindow.setContentView(contentView);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x66000000));
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, HuaWeiBottomUtils.getNavigationBarHeight(activity));
        tvCanncle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                popupWindow.dismiss();
            }
        });
        tvPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public static PopupWindow ShowLoginOutDialog(final MvpBaseActivity activity, final View view, String content, String cancle, String commit, onClickListener listener) {
        final PopupWindow popupWindow = new PopupWindow(activity);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        View view0 = LayoutInflater.from(activity).inflate(R.layout.layout_popupwindow_style10, null);
        popupWindow.setContentView(view0);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x99000000));
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        RelativeLayout rlBack = view0.findViewById(R.id.rl_back);
        TextView tvBack = view0.findViewById(R.id.dialog_left);
        TextView tvComit = view0.findViewById(R.id.dialog_right);
        TextView tvContent = view0.findViewById(R.id.dialog_tv);
        tvContent.setText(content);
        tvBack.setText(cancle);
        tvComit.setText(commit);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        tvComit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick();
                }
                popupWindow.dismiss();
            }
        });
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        view.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, HuaWeiBottomUtils.getNavigationBarHeight(activity));
            }
        });
        return popupWindow;
    }

    public interface onClickListener {
        void onClick();
    }


}
