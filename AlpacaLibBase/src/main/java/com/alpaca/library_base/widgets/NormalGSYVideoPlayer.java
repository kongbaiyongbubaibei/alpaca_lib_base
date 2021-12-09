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
import android.widget.SeekBar;
import android.widget.TextView;

import com.alpaca.library_base.R;
import com.alpaca.library_base.constants.Constants;
import com.alpaca.library_base.event.UserCustomEvent;
import com.alpaca.library_base.utils.SharedPreferenceUtil;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.ControlViewListener;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;
import com.shuyu.gsyvideoplayer.model.VideoOptionModel;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;


/**
 * class desc :
 *
 * @author : zzh
 * @date : 2019/11/18
 */
public class NormalGSYVideoPlayer extends StandardGSYVideoPlayer implements VideoAllCallBack {

    private Dialog speedChooseDialog;
    private String TAG = "NormalGSYVideoPlayer";

    public NormalGSYVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public NormalGSYVideoPlayer(Context context) {
        super(context);
    }

    public NormalGSYVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private OnVideoSizeChangeListener sizeChangeListener;

    public void setSizeChangeListener(OnVideoSizeChangeListener sizeChangeListener) {
        this.sizeChangeListener = sizeChangeListener;
    }

    @Override
    protected void init(Context context) {
        isShowControlView = true;
        super.init(context);
        VideoOptionModel videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "rtsp_transport", "tcp");
        List<VideoOptionModel> list = new ArrayList<>();
        list.add(videoOptionModel);
        GSYVideoManager.instance().setOptionModelList(list);
        post(() -> {
            if (!mIfCurrentIsFullscreen) {
                int screenWidth = getMeasuredWidth();
                float height = 9.02F / 16F * screenWidth;
                if (height > screenWidth) {
                    height = (float) screenWidth;
                }
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                layoutParams.height = (int) height;
                layoutParams.width = screenWidth + 5;
                if (sizeChangeListener != null) {
                    sizeChangeListener.onVideoSize(layoutParams.width, layoutParams.height);
                }
                setLayoutParams(layoutParams);
            }

            getFullscreenButton().setOnClickListener(view -> {
                if (GSYVideoManager.backFromWindowFull(context)) {
                    return;
                }
                MobclickAgent.onEvent(mContext, "jcxiangqingye_bf_quanping");
                startWindowFullscreen(context, true, true);
            });
        });
        findViewById(R.id.iv_gsy_more).setOnClickListener(v -> showItemMoreDialog());
        findViewById(R.id.mFast).setOnClickListener(v -> {
            if (getDuration() != 0) {
                seekTo(Math.min((getCurrentPositionWhenPlaying() + 15000), getDuration()));
            } else {
                seekTo((getCurrentPositionWhenPlaying() + 15000));
            }
        });
        findViewById(R.id.mSlow).setOnClickListener(v -> {
            seekTo(Math.max((getCurrentPositionWhenPlaying() - 15000), 0));
        });
        setVideoAllCallBack(this);
    }

    public void setShowSocial(boolean social) {
        mShowSocial = social;
    }

    public void setIsShowControlView(boolean isShow) {
        isShowControlView = isShow;
    }


    public void showRePlayBtn(boolean isShow) {
        if (isShow) {
            getStartButton().setVisibility(GONE);
            findViewById(R.id.completed).setVisibility(VISIBLE);
        } else {
            findViewById(R.id.completed).setVisibility(GONE);
        }
    }

    public void setSpeedSignViewVisible(boolean isShow) {
        TextView mSpeed = findViewById(R.id.mSpeedSign);
        if (mSpeed != null) {
            if (isShow) {
                mSpeed.setVisibility(VISIBLE);
            } else {
                mSpeed.setVisibility(GONE);
            }
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.module_view_gsymediaplayer_controller_normal1;
    }

    public void setBackShow() {
        findViewById(R.id.back).setVisibility(VISIBLE);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        super.onProgressChanged(seekBar, progress, fromUser);
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
    public GSYBaseVideoPlayer startWindowFullscreen(Context context, boolean actionBar, boolean statusBar) {
        GSYBaseVideoPlayer gsyBaseVideoPlayer = super.startWindowFullscreen(context, actionBar, statusBar);
        if (gsyBaseVideoPlayer != null) {
            NormalGSYVideoPlayer gsyVideoPlayer = (NormalGSYVideoPlayer) gsyBaseVideoPlayer;
            gsyVideoPlayer.setLockClickListener(mLockClickListener);
            gsyVideoPlayer.setNeedLockFull(isNeedLockFull());
            gsyBaseVideoPlayer.setListener(new ControlViewListener() {
                @Override
                public void onViewShow(boolean isShow) {

                }

                @Override
                public void onVideoLongPressed(boolean isPressed) {
                    if (isPressed) {
                        GSYVideoManager.instance().setSpeed(2.0f, true);
                    } else {
                        GSYVideoManager.instance().setSpeed(1.0f, true);
                    }
                    gsyVideoPlayer.setSpeedSignViewVisible(isPressed);
                }
            });
            initFullUI(gsyVideoPlayer);
            //比如你自定义了返回案件，但是因为返回按键底层已经设置了返回事件，所以你需要在这里重新增加的逻辑
        }
        return gsyBaseVideoPlayer;
    }


    public interface OnVideoSizeChangeListener {
        void onVideoSize(int width, int height);
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

    }

    @Override
    public void onPrepared(String url, Object... objects) {
        float speend = (float) SharedPreferenceUtil.get(getContext(), Constants.SP.DUB_VIDEO_SPEED, 1.0f);
        GSYVideoManager.instance().setSpeed(speend, true);
    }

    @Override
    public void onClickStartIcon(String url, Object... objects) {
        EventBus.getDefault().post(new UserCustomEvent("openClass"));
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
        MobclickAgent.onEvent(mContext, "jcxiangqingye_bf_jindu");
    }

    @Override
    public void onClickSeekbarFullscreen(String url, Object... objects) {

    }

    @Override
    public void onAutoComplete(String url, Object... objects) {

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

    }

    @Override
    public void onClickBlankFullscreen(String url, Object... objects) {

    }

    @Override
    public void onUIStatusViewShow(boolean isShow) {

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
                MobclickAgent.onEvent(mContext, "jcxiangqingye_bf_sudu");
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
    protected void onClickUiToggle() {
        if (mIfCurrentIsFullscreen && mLockCurScreen && mNeedLockFull) {
            setViewShowState(mLockScreen, VISIBLE);
            return;
        }
        if (mCurrentState == CURRENT_STATE_PREPAREING) {
            if (mBottomContainer != null) {
                if (mBottomContainer.getVisibility() == View.VISIBLE) {
                    changeUiToPrepareingClear();
                } else {
                    changeUiToPreparingShow();
                }
            }
        } else if (mCurrentState == CURRENT_STATE_PLAYING) {
            if (mBottomContainer != null) {
                if (mBottomContainer.getVisibility() == View.VISIBLE) {
                    changeUiToPlayingClear();
                } else {
                    changeUiToPlayingShow();
                }
            }
        } else if (mCurrentState == CURRENT_STATE_PAUSE) {
            if (mBottomContainer != null) {
                if (mBottomContainer.getVisibility() == View.VISIBLE) {
                    changeUiToPauseClear();
                } else {
                    changeUiToPauseShow();
                }
            }
        } else if (mCurrentState == CURRENT_STATE_AUTO_COMPLETE) {
            if (mBottomContainer != null) {
                if (mBottomContainer.getVisibility() == View.VISIBLE) {
                    changeUiToCompleteClear();
                } else {
                    changeUiToCompleteShow();
                }
            }
        } else if (mCurrentState == CURRENT_STATE_PLAYING_BUFFERING_START) {
            if (mBottomContainer != null) {
                if (mBottomContainer.getVisibility() == View.VISIBLE) {
                    changeUiToPlayingBufferingClear();
                } else {
                    changeUiToPlayingBufferingShow();
                }
            }
        }
    }

}
