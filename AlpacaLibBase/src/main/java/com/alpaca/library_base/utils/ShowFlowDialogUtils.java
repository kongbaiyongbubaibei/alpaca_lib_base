package com.alpaca.library_base.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alpaca.library_base.R;
import com.alpaca.library_base.module.AppConfig;
import com.alpaca.library_base.widgets.ExpandBottomSheetDialog;
import com.umeng.analytics.MobclickAgent;

import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;

/**
 * class desc :
 * <p>
 * 所有的导流弹窗都放在这里统一管理
 *
 * @author : zzh
 * date : 2020/3/13
 */
public class ShowFlowDialogUtils {

    private static ExpandBottomSheetDialog dialog;


    /**
     * 显示通用的导流弹窗
     *
     * @param context       上下文
     * @param tips          左上角tips
     * @param title         左上角大标题内容
     * @param copyWxContent 复制微信号文案
     * @param addWxDesc     添加微信号步骤指引文案
     * @param addDrawable   添加微信号步骤指引图片
     * @param wxCode        微信号
     * @param listener      点击事件回调
     */
    public static void showCommonDialog(Context context, String tips, CustomSpannableStringBuilder title, String copyWxContent,
                                        String addWxDesc, @DrawableRes int addDrawable, String wxCode,
                                        OnWxCopyListener listener, Boolean isTencent) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = new ExpandBottomSheetDialog(context);
        dialog.setContentView(R.layout.dialog_flow_common);
        ImageView ivHeadBg = dialog.findViewById(R.id.iv_head_bg);
        TextView tvTips = dialog.findViewById(R.id.tv_tips);
        TextView tvHead = dialog.findViewById(R.id.tv_head);
        TextView tvWxTitle = dialog.findViewById(R.id.tv_wx_title);
        TextView tvWxDesc = dialog.findViewById(R.id.tv_wx_desc);
        ImageView ivWxCover = dialog.findViewById(R.id.iv_wx_cover);
        TextView tvCopyJump = dialog.findViewById(R.id.tv_copy_jump);
        TextView tvOpenWx = dialog.findViewById(R.id.tv_open_wx);

        tvTips.setText(tips);
        tvHead.setText(title);
        tvWxTitle.setText(copyWxContent);
        tvWxDesc.setText(addWxDesc);
        ivWxCover.setImageResource(addDrawable);

        tvWxTitle.setOnClickListener(v -> {
            copy(context, wxCode);
            showSecondDialog(context, isTencent);
            if (listener != null) {
                listener.onWxCopy();
            }
            dialog.dismiss();
        });

        tvCopyJump.setOnClickListener(v -> {
            copy(context, wxCode);
            showSecondDialog(context, isTencent);
            if (listener != null) {
                listener.onWxCopy();
            }
            dialog.dismiss();
        });

        tvOpenWx.setOnClickListener(v -> {
            openWx(context);
            if (listener != null) {
                listener.onWxCopy();
            }
            dialog.dismiss();
        });

        dialog.show();
    }

    /**
     * 显示通用的导流弹窗
     *
     * @param context       上下文
     * @param tips          左上角tips
     * @param title         左上角大标题内容
     * @param copyWxContent 复制微信号文案
     * @param addWxDesc     添加微信号步骤指引文案
     * @param addDrawable   添加微信号步骤指引图片
     * @param wxCode        微信号
     */
    public static void showCommonDialog(Context context, String tips, CustomSpannableStringBuilder title, String copyWxContent,
                                        String addWxDesc, @DrawableRes int addDrawable, String wxCode) {
        showCommonDialog(context, tips, title, copyWxContent, addWxDesc, addDrawable, wxCode, null, false);
    }

    public static void showCommonDialog(Context context, String tips, CustomSpannableStringBuilder title, String copyWxContent,
                                        String addWxDesc, @DrawableRes int addDrawable, String wxCode, OnWxCopyListener listener) {
        showCommonDialog(context, tips, title, copyWxContent, addWxDesc, addDrawable, wxCode, listener, false);
    }

    public interface OnWxCopyListener {
        void onWxCopy();
    }

    private static void copy(Context activity, String wxNum) {
        ClipboardManager cm = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData mClipData = ClipData.newPlainText("Label", wxNum);
        cm.setPrimaryClip(mClipData);
    }

    private static void copyWxAndOpen(Context activity, String wxNum) {
        if (AppUtils.isWeixinAvilible(activity)) {
            copy(activity, wxNum);
            Intent lan = activity.getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (lan != null) {
                intent.setComponent(lan.getComponent());
                activity.startActivity(intent);
            }
        } else {
            Toast.makeText(activity, "您还未安装微信", Toast.LENGTH_SHORT).show();
        }
    }

    private static void openWx(Context activity) {
        if (AppUtils.isWeixinAvilible(activity)) {
            Intent lan = activity.getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (lan != null) {
                intent.setComponent(lan.getComponent());
                activity.startActivity(intent);
            }
        } else {
            Toast.makeText(activity, "您还未安装微信", Toast.LENGTH_SHORT).show();
        }
    }

    private static void showSecondDialog(Context activity, Boolean isTencent) {
        Dialog dialog = new Dialog(activity, R.style.BaseDialogStyle);
        dialog.setContentView(R.layout.dialog_choose_common);
        TextView tvCancle = dialog.findViewById(R.id.dialog_back);
        TextView tvPost = dialog.findViewById(R.id.dialog_commit);
        TextView tvContent = dialog.findViewById(R.id.dialog_tv);
        if (isTencent) {
            tvContent.setText("已复制公众号账号，请前往微信粘贴添加");
        } else {
            tvContent.setText("已复制微信账号，请前往微信粘贴添加");
        }
        tvPost.setOnClickListener(v -> {
            openWx(activity);
            dialog.dismiss();
        });
        tvCancle.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }


    public static void showOrderDialog(Activity context, boolean isOnlien, String onlineTitle, itemOnClick click) {
        Activity activity = context;
        Dialog dialog = new Dialog(activity, R.style.transparentDialog);
        View view = View.inflate(activity, R.layout.dialog_order_online, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = DensityUtil.dip2px(activity, 255);
        layoutParams.height = DensityUtil.dip2px(activity, 424);
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setAttributes(layoutParams);

        dialog.show();

        ImageView ivClose = dialog.findViewById(R.id.iv_close);
        RelativeLayout rlSuccess = dialog.findViewById(R.id.rl_success);
        TextView tvOnlineContent = dialog.findViewById(R.id.tv_online_content);
        TextView tvTitle = dialog.findViewById(R.id.tv_dialog_title);
        TextView tvCommit = dialog.findViewById(R.id.tv_dialog_commit);
        ivClose.setOnClickListener(v -> dialog.dismiss());
        if (isOnlien) {
            tvTitle.setText("预约提醒");
            tvCommit.setText("立即前往");
            rlSuccess.setVisibility(View.GONE);
            tvCommit.setOnClickListener(v -> {
                dialog.dismiss();
                MobclickAgent.onEvent(context,"Classroom_Home_reservegotoLive");
                if (click != null) {
                    click.onClick(v);
                }
            });
            tvOnlineContent.setVisibility(View.VISIBLE);
            String content = String.format("您预约的公开课“%s”正在开播，可在APP内点击经验-直播课直接观看直播。", onlineTitle);
            String key = "经验-直播课";
            CustomSpannableStringBuilder titleBuilder = SpannableUtil.changeTextColor(
                    Color.parseColor("#0059ff"), content, key);
            tvOnlineContent.setText(titleBuilder);
        } else {
            tvTitle.setText("预约成功");
            tvCommit.setText("联系老师");
            tvOnlineContent.setVisibility(View.GONE);
            rlSuccess.setVisibility(View.VISIBLE);
            tvCommit.setOnClickListener(v -> {
                dialog.dismiss();
                showFlowDialog(context);
            });
        }
    }

    public static void showOrderDialog(Fragment context, boolean isOnlien, String onlineTitle, itemOnClick click) {
        Fragment fragment = context;
        Dialog dialog = new Dialog(fragment.getContext(), R.style.BaseDialogStyle);
        View view = View.inflate(fragment.getContext(), R.layout.dialog_order_online, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = DensityUtil.dip2px(fragment.getContext(), 255);
        layoutParams.height = DensityUtil.dip2px(fragment.getContext(), 424);
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setAttributes(layoutParams);

        dialog.show();

        ImageView ivClose = dialog.findViewById(R.id.iv_close);
        RelativeLayout rlSuccess = dialog.findViewById(R.id.rl_success);
        TextView tvOnlineContent = dialog.findViewById(R.id.tv_online_content);
        TextView tvTitle = dialog.findViewById(R.id.tv_dialog_title);
        TextView tvCommit = dialog.findViewById(R.id.tv_dialog_commit);
        ivClose.setOnClickListener(v -> dialog.dismiss());
        if (isOnlien) {
            tvTitle.setText("预约提醒");
            tvCommit.setText("立即前往");
            rlSuccess.setVisibility(View.GONE);
            tvOnlineContent.setVisibility(View.VISIBLE);
            tvCommit.setOnClickListener(v -> {
                MobclickAgent.onEvent(context.getContext(),"Classroom_Home_reservegotoLive");
                dialog.dismiss();
                if (click != null) {
                    click.onClick(v);
                }
            });
            String content = String.format("您预约的公开课“%s”正在开播，可在APP内点击经验-直播课直接观看直播。", onlineTitle);
            String key = "经验-直播课";
            CustomSpannableStringBuilder titleBuilder = SpannableUtil.changeTextColor(
                    Color.parseColor("#0059ff"), content, key);
            tvOnlineContent.setText(titleBuilder);
        } else {
            tvTitle.setText("预约成功");
            tvCommit.setText("联系老师");
            tvOnlineContent.setVisibility(View.GONE);
            rlSuccess.setVisibility(View.VISIBLE);
            tvCommit.setOnClickListener(v -> {
                dialog.dismiss();
                showFlowDialog(context.getContext());
            });
        }
    }

    public interface itemOnClick {
        void onClick(View view);
    }

    public static void showFlowDialog(Context context) {
        AppConfig.DataBean.AllWindowData winType7 = AppConfigUtils.getAllWindowByType(AppConfigUtils.WINTYPE_7);
        String wxCode7 = winType7 == null ? "" : winType7.getWinWeixin();

        CustomSpannableStringBuilder titleBuilder = SpannableUtil.changeTextColor(
                Color.parseColor("#ffff33"), "咨询课程顾问老师\n" +
                        "了解最佳学习计划", "最佳学习计划");
        ShowFlowDialogUtils.showCommonDialog(context, "预约课程", titleBuilder,
                "备考顾问微信号：" + wxCode7,
                "1.打开微信选择“+”菜单中“添加好友”\n2.粘贴微信号即可搜索并添加顾问",
                R.drawable.intro_0325, wxCode7);
    }



}
