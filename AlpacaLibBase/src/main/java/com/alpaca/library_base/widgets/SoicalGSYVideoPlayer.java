package com.alpaca.library_base.widgets;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.alpaca.library_base.R;
import com.alpaca.library_base.constants.Constants;
import com.alpaca.library_base.event.VideoProgressEvent;
import com.alpaca.library_base.utils.DensityUtil;
import com.alpaca.library_base.utils.LogUtil;
import com.alpaca.library_base.utils.SharedPreferenceUtil;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.Arrays;

import moe.codeest.enviews.ENDownloadView;

import static com.shuyu.gsyvideoplayer.utils.GSYVideoType.enableMediaCodecTexture;

/**
 * class desc :
 *
 * @author : zzh
 * @date : 2019/11/18
 */
public class SoicalGSYVideoPlayer extends StandardGSYVideoPlayer implements VideoAllCallBack, Serializable {

    private Dialog speedChooseDialog;
    private String TAG = SoicalGSYVideoPlayer.class.getSimpleName();
    private onStatusListener listener;


    public void setListener(onStatusListener listener) {
        this.listener = listener;
    }

    public SoicalGSYVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public SoicalGSYVideoPlayer(Context context) {
        super(context);
    }

    public SoicalGSYVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        isShowControlView = false;
        super.init(context);
        post(() -> {
            if (!mIfCurrentIsFullscreen) {
                int screenWidth = getMeasuredWidth();
                float height = 9.0F / 16F * screenWidth;
                if (height > screenWidth) {
                    height = (float) screenWidth;
                }
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.height = (int) height;
                layoutParams.width = screenWidth;
                setLayoutParams(layoutParams);
            }
            getFullscreenButton().setOnClickListener(view -> {
                if (GSYVideoManager.backFromWindowFull(context)) {
                    return;
                }
                startWindowFullscreen(context, true, true);
            });
        });
        findViewById(R.id.mVoice).setOnClickListener(view -> {
            if (GSYVideoManager.instance().isNeedMute()) {
                GSYVideoManager.instance().setNeedMute(false);
            } else {
                GSYVideoManager.instance().setNeedMute(true);
            }
        });
        mShowSocial = true;
        setLooping(true);
        mCache = true;
        enableMediaCodecTexture();
        findViewById(R.id.iv_gsy_more).setOnClickListener(v -> showItemMoreDialog());
        setVideoAllCallBack(this);
        hideAllWidget();
    }

    public void setSpeedSignViewVisible(boolean isShow) {
        findViewById(R.id.mSpeedSign).setVisibility(isShow ? VISIBLE : GONE);
    }


    public void setShowSocial(boolean social) {
        mShowSocial = social;
        GSYVideoManager.instance().setNeedMute(false);
        findViewById(R.id.mVoice).setVisibility(GONE);
    }

    public void setShowSocial(boolean social, boolean needMute) {
        mShowSocial = social;
        GSYVideoManager.instance().setVolume(needMute);
        findViewById(R.id.mVoice).setVisibility(VISIBLE);
    }

    public void initVericalLayout(Context context) {
        post(() -> {
            if (!mIfCurrentIsFullscreen) {
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.height = DensityUtil.dip2px(mContext, 301);
                layoutParams.width = DensityUtil.dip2px(mContext, 168);
                setLayoutParams(layoutParams);
            }
            getFullscreenButton().setOnClickListener(view -> {
                if (GSYVideoManager.backFromWindowFull(context)) {
                    return;
                }
                startWindowFullscreen(context, true, true);
            });
        });
    }

    public void initFullLayout(Context context) {
        post(() -> {
            if (!mIfCurrentIsFullscreen) {
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                setLayoutParams(layoutParams);
            }
            getFullscreenButton().setOnClickListener(view -> {
                if (GSYVideoManager.backFromWindowFull(context)) {
                    return;
                }
                startWindowFullscreen(context, true, true);
            });
        });
    }

    public void initHorizontalLayout(Context context) {
        post(() -> {
            if (!mIfCurrentIsFullscreen) {
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.height = (DensityUtil.getScreenWidth(context) * 4) / 7;
                layoutParams.width = DensityUtil.getScreenWidth(context);
                setLayoutParams(layoutParams);
            }
            getFullscreenButton().setOnClickListener(view -> {
                if (GSYVideoManager.backFromWindowFull(context)) {
                    return;
                }
                startWindowFullscreen(context, true, true);
            });
        });
    }

    @Override
    protected void touchDoubleUp() {
    }


    @Override
    public int getLayoutId() {
        return R.layout.view_gsymediaplayer_controller_social;
    }

    @Override
    protected boolean isShowNetConfirm() {
        return false;
    }

    /******************* 下方两个重载方法，在播放开始前不屏蔽封面，不需要可屏蔽 ********************/
    @Override
    public void onSurfaceUpdated(Surface surface) {
        super.onSurfaceUpdated(surface);
        if (mThumbImageViewLayout != null && mThumbImageViewLayout.getVisibility() == VISIBLE) {
            mThumbImageViewLayout.setVisibility(INVISIBLE);
        }
    }

    @Override
    protected void setViewShowState(View view, int visibility) {
        if (view == mThumbImageViewLayout && visibility != VISIBLE) {
            return;
        }
        if (view == mBottomProgressBar && visibility == VISIBLE) {
            return;
        }
        super.setViewShowState(view, visibility);
    }

    @Override
    public void onSurfaceAvailable(Surface surface) {
        super.onSurfaceAvailable(surface);

        if (GSYVideoType.getRenderType() != GSYVideoType.TEXTURE) {
            if (mThumbImageViewLayout != null && mThumbImageViewLayout.getVisibility() == VISIBLE) {
                mThumbImageViewLayout.setVisibility(INVISIBLE);
            }
        }
    }


    @Override
    public void onNeedMute(boolean isMute) {
//        MobclickAgent.onEvent(mContext,"jcliebiao_shipin_shengyin");
        if (isMute) {
            ((ImageView) findViewById(R.id.mVoice)).setImageResource(R.drawable.hljc_icon_jy);
        } else {
            ((ImageView) findViewById(R.id.mVoice)).setImageResource(R.drawable.hljc_icon_lb);
        }
    }


    public interface OnClickStartIconListener {
        void onClick();
    }

    private OnClickStartIconListener onClickStartIconListener;

    public void setOnClickStartIconListener(OnClickStartIconListener onClickStartIconListener) {
        this.onClickStartIconListener = onClickStartIconListener;
    }

    //状态回调------------

    @Override
    public void onStartPrepared(String url, Object... objects) {
        if (mShowSocial) {
            GSYVideoManager.instance().setNeedMute(GSYVideoManager.instance().isNeedMute());
            ((ImageView) findViewById(R.id.mVoice)).setVisibility(VISIBLE);
        }
        ((ImageView) findViewById(R.id.mVoice)).setVisibility(GONE);
    }

    @Override
    public void onPrepared(String url, Object... objects) {
        LogUtil.d(TAG, "onPrepared url:" + url);
        if (mShowSocial) {
            GSYVideoManager.instance().setNeedMute(GSYVideoManager.instance().isNeedMute());
            ((ImageView) findViewById(R.id.mVoice)).setVisibility(VISIBLE);
        }

    }

    @Override
    public void onClickStartIcon(String url, Object... objects) {
        if (onClickStartIconListener != null) {
            onClickStartIconListener.onClick();
        }
    }

    @Override
    public void onClickStartError(String url, Object... objects) {

    }

    @Override
    public void onClickStop(String url, Object... objects) {

    }

    @Override
    public void onClickStopFullscreen(String url, Object... objects) {

    }

    @Override
    public void onClickResume(String url, Object... objects) {

    }

    @Override
    public void onClickResumeFullscreen(String url, Object... objects) {

    }

    @Override
    public void onClickSeekbar(String url, Object... objects) {

    }

    @Override
    public void onClickSeekbarFullscreen(String url, Object... objects) {

    }

    @Override
    public void onAutoComplete(String url, Object... objects) {
        LogUtil.d(TAG, "onAutoComplete");
    }

    @Override
    public void onEnterFullscreen(String url, Object... objects) {
    }

    @Override
    public void onQuitFullscreen(String url, Object... objects) {

    }

    @Override
    public void onQuitSmallWidget(String url, Object... objects) {

    }

    @Override
    public void onEnterSmallWidget(String url, Object... objects) {

    }

    @Override
    public void onTouchScreenSeekVolume(String url, Object... objects) {

    }

    @Override
    public void onTouchScreenSeekPosition(String url, Object... objects) {

    }

    @Override
    public void onTouchScreenSeekLight(String url, Object... objects) {

    }

    @Override
    public void onPlayError(String url, Object... objects) {

    }

    @Override
    public void onClickStartThumb(String url, Object... objects) {

    }

    @Override
    public void onClickBlank(String url, Object... objects) {
        if (onClickStartIconListener != null) {
            onClickStartIconListener.onClick();
        }
    }

    @Override
    public void onClickBlankFullscreen(String url, Object... objects) {

    }

    @Override
    public void onUIStatusViewShow(boolean isShow) {
        if (listener != null) {
            listener.statusViewShow(isShow);
        }
    }

    public interface onStatusListener {
        void statusViewShow(boolean isShow);
    }


    /**
     * 右上角更多按钮
     */
    private void showItemMoreDialog() {
        Dialog dialog = new Dialog(getContext());
        View view = View.inflate(getContext(), R.layout.dialog_dub_failarmy_work_more1, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        if (window == null) return;
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setBackgroundDrawable(null);
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setAttributes(layoutParams);

        dialog.show();

        view.findViewById(R.id.tv_cancel).setOnClickListener(v -> dialog.dismiss());

        float speend = (float) SharedPreferenceUtil.get(getContext(), Constants.SP.DUB_VIDEO_SPEED, 1.0f);
        ((TextView) view.findViewById(R.id.tv_speed)).setText("X" + speend);
        //速度
        view.findViewById(R.id.ll_speed).setOnClickListener(v -> {
            showSpeedChangeDialog(speend);
            dialog.dismiss();
        });
    }

    //显示播放速度修改的dialog
    private void showSpeedChangeDialog(float speed) {
        if (speedChooseDialog != null && speedChooseDialog.isShowing()) {
            speedChooseDialog.dismiss();
        }
        speedChooseDialog = new Dialog(getContext());
        View view = View.inflate(getContext(), R.layout.dialog_dub_failarmy_work_speed, null);
        speedChooseDialog.setContentView(view);

        Window window = speedChooseDialog.getWindow();
        if (window == null) return;
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setBackgroundDrawable(null);
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setAttributes(layoutParams);
        speedChooseDialog.show();

        view.findViewById(R.id.tv_cancel).setOnClickListener(v -> speedChooseDialog.dismiss());

        float[] speedArr = {0.5f, 0.8f, 1.0f, 1.5f, 2.0f};
        int position = Arrays.binarySearch(speedArr, speed);

        SeekBar seekBar = view.findViewById(R.id.sb_speed);
        seekBar.setProgress(position);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                SharedPreferenceUtil.put(getContext(), Constants.SP.DUB_VIDEO_SPEED, speedArr[i]);
                GSYVideoManager.instance().setSpeed(speedArr[i], true);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        super.onProgressChanged(seekBar, progress, fromUser);
        LogUtil.d(TAG, "onProgressChanged");
    }

    @Override
    protected void onClickUiToggle() {
        if (onClickStartIconListener != null) {
            onClickStartIconListener.onClick();
        }
//        if (mShowSocial) {
//            if (isInPlayingState()) {
//                if (onClickStartIconListener != null) {
//                    onClickStartIconListener.onClick();
//                }
//            }
//            return;
//        }
    }

    @Override
    protected void changeUiToNormal() {
        Debuger.printfLog("changeUiToNormal");

        setViewShowState(mTopContainer, INVISIBLE);
        setViewShowState(mBottomContainer, INVISIBLE);
        setViewShowState(mStartButton, INVISIBLE);
        setViewShowState(mLoadingProgressBar, GONE);
        setViewShowState(mThumbImageViewLayout, VISIBLE);
        setViewShowState(mBottomProgressBar, INVISIBLE);
        setViewShowState(mLockScreen, GONE);

        updateStartImage();
        if (mLoadingProgressBar instanceof ENDownloadView) {
            ((ENDownloadView) mLoadingProgressBar).reset();
        }
    }

    @Override
    protected void changeUiToPreparingShow() {
        Debuger.printfLog("changeUiToPreparingShow");

        setViewShowState(mTopContainer, INVISIBLE);
        setViewShowState(mBottomContainer, INVISIBLE);
        setViewShowState(mStartButton, INVISIBLE);
        setViewShowState(mLoadingProgressBar, VISIBLE);
        setViewShowState(mThumbImageViewLayout, INVISIBLE);
        setViewShowState(mBottomProgressBar, INVISIBLE);
        setViewShowState(mLockScreen, GONE);

        if (mLoadingProgressBar instanceof ENDownloadView) {
            ENDownloadView enDownloadView = (ENDownloadView) mLoadingProgressBar;
            if (enDownloadView.getCurrentState() == ENDownloadView.STATE_PRE) {
                ((ENDownloadView) mLoadingProgressBar).start();
            }
        }
    }

    @Override
    protected void changeUiToPreparingShowNoBottom() {
        Debuger.printfLog("changeUiToPreparingShow");

        setViewShowState(mTopContainer, INVISIBLE);
        setViewShowState(mBottomContainer, INVISIBLE);
        setViewShowState(mStartButton, INVISIBLE);
        setViewShowState(mLoadingProgressBar, INVISIBLE);
        setViewShowState(mThumbImageViewLayout, INVISIBLE);
        setViewShowState(mBottomProgressBar, INVISIBLE);
        setViewShowState(mLockScreen, GONE);

        if (mLoadingProgressBar instanceof ENDownloadView) {
            ENDownloadView enDownloadView = (ENDownloadView) mLoadingProgressBar;
            if (enDownloadView.getCurrentState() == ENDownloadView.STATE_PRE) {
                ((ENDownloadView) mLoadingProgressBar).start();
            }
        }
    }

    @Override
    public void onAutoCompletion() {
        super.onAutoCompletion();
        EventBus.getDefault().post(new VideoProgressEvent(100, getCurrentPosition(), getDuration()));
    }

    @Override
    protected void changeUiToPlayingShow() {
        Debuger.printfLog("changeUiToPlayingShow");

        setViewShowState(mTopContainer, INVISIBLE);
        setViewShowState(mBottomContainer, INVISIBLE);
        setViewShowState(mStartButton, INVISIBLE);
        setViewShowState(mLoadingProgressBar, GONE);
        setViewShowState(mThumbImageViewLayout, INVISIBLE);
        setViewShowState(mBottomProgressBar, INVISIBLE);
        setViewShowState(mLockScreen, GONE);

        if (mLoadingProgressBar instanceof ENDownloadView) {
            ((ENDownloadView) mLoadingProgressBar).reset();
        }
        updateStartImage();
    }

    @Override
    protected void changeUiToPauseShow() {
        Debuger.printfLog("changeUiToPauseShow");

        setViewShowState(mTopContainer, INVISIBLE);
        setViewShowState(mBottomContainer, INVISIBLE);
        setViewShowState(mStartButton, INVISIBLE);
        setViewShowState(mLoadingProgressBar, GONE);
        setViewShowState(mThumbImageViewLayout, INVISIBLE);
        setViewShowState(mBottomProgressBar, INVISIBLE);
        setViewShowState(mLockScreen, GONE);

        if (mLoadingProgressBar instanceof ENDownloadView) {
            ((ENDownloadView) mLoadingProgressBar).reset();
        }
        updateStartImage();
        updatePauseCover();
    }

    @Override
    protected void changeUiToPlayingBufferingShow() {
        Debuger.printfLog("changeUiToPlayingBufferingShow");

        setViewShowState(mTopContainer, INVISIBLE);
        setViewShowState(mBottomContainer, INVISIBLE);
        setViewShowState(mStartButton, INVISIBLE);
        setViewShowState(mLoadingProgressBar, GONE);
        setViewShowState(mThumbImageViewLayout, INVISIBLE);
        setViewShowState(mBottomProgressBar, INVISIBLE);
        setViewShowState(mLockScreen, GONE);

        if (mLoadingProgressBar instanceof ENDownloadView) {
            ENDownloadView enDownloadView = (ENDownloadView) mLoadingProgressBar;
            if (enDownloadView.getCurrentState() == ENDownloadView.STATE_PRE) {
                ((ENDownloadView) mLoadingProgressBar).start();
            }
        }
    }

    @Override
    protected void changeUiToCompleteShow() {
        LogUtil.d(TAG, "changeUiToCompleteShow");
        Debuger.printfLog("changeUiToCompleteShow");

        setViewShowState(mTopContainer, INVISIBLE);
        setViewShowState(mBottomContainer, INVISIBLE);
        setViewShowState(mStartButton, INVISIBLE);
        setViewShowState(mLoadingProgressBar, GONE);
        setViewShowState(mThumbImageViewLayout, VISIBLE);
        setViewShowState(mBottomProgressBar, INVISIBLE);
        setViewShowState(mLockScreen, GONE);

        if (mLoadingProgressBar instanceof ENDownloadView) {
            ((ENDownloadView) mLoadingProgressBar).reset();
        }
        updateStartImage();
    }

    @Override
    protected void setProgressAndTime(int progress, int secProgress, int currentTime, int totalTime, boolean forceChange) {
        super.setProgressAndTime(progress, secProgress, currentTime, totalTime, forceChange);
        LogUtil.d(TAG, "setProgress:" + progress);
        EventBus.getDefault().post(new VideoProgressEvent(progress, getCurrentPosition(), getDuration()));
    }
}
