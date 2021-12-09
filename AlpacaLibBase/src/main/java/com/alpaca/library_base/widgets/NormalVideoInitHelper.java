package com.alpaca.library_base.widgets;

import android.app.Activity;
import android.content.Context;

import com.alpaca.library_base.BuildConfig;
import com.alpaca.library_base.R;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.cache.CacheFactory;
import com.shuyu.gsyvideoplayer.model.VideoOptionModel;
import com.shuyu.gsyvideoplayer.player.IjkPlayerManager;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

import tv.danmaku.ijk.media.exo2.Exo2PlayerManager;
import tv.danmaku.ijk.media.exo2.ExoPlayerCacheManager;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * class desc :
 * <p>
 * GSYMediaPlayer加载帮助类，封装常用API
 * <p>
 * 适用于普通的播放器，如果对播放器有特殊需求，需重写
 *
 * @author : zzh
 * @date : 2019/9/30
 */
public class NormalVideoInitHelper {

    /**
     * 初始化接口，不会自动播放，需要手动调用播放
     *
     * @param customGSYVideoPlayer 播放器
     * @param mActivity            上下文
     */
    public void init(NormalGSYVideoPlayer customGSYVideoPlayer, Activity mActivity) {

        if (BuildConfig.DEBUG) {
            Debuger.enable();
        } else {
            Debuger.disable();
        }

        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_DEFAULT);
        IjkPlayerManager.setLogLevel(IjkMediaPlayer.IJK_LOG_SILENT);

        //exo模式和缓存，修改这两个都要该
//        PlayerFactory.setPlayManager(IjkPlayerManager.class);
        PlayerFactory.setPlayManager(Exo2PlayerManager.class);
        CacheFactory.setCacheManager(ExoPlayerCacheManager.class);

        VideoOptionModel videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "enable-accurate-seek", 1);
        List<VideoOptionModel> list = new ArrayList<>();
        list.add(videoOptionModel);
        GSYVideoManager.instance().setNeedMute(false);
        GSYVideoManager.instance().setOptionModelList(list);
        customGSYVideoPlayer.setShowSocial(false);
        customGSYVideoPlayer.setIsShowControlView(true);
        customGSYVideoPlayer.setShowPauseCover(true);
        customGSYVideoPlayer.setEnlargeImageRes(R.drawable.max_190805);
        customGSYVideoPlayer.setShrinkImageRes(R.drawable.min_190805);
        customGSYVideoPlayer.setDismissControlTime(2000);

        customGSYVideoPlayer.findViewById(R.id.back).setOnClickListener(view -> mActivity.finish());

        customGSYVideoPlayer.getFullscreenButton().setOnClickListener(view -> {
            //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
            customGSYVideoPlayer.startWindowFullscreen(mActivity, true, true);
        });

        customGSYVideoPlayer.setIsTouchWiget(false);
        customGSYVideoPlayer.setRotateViewAuto(false);
        customGSYVideoPlayer.setLockLand(true);
        customGSYVideoPlayer.setAutoFullWithSize(true);
        customGSYVideoPlayer.setShowFullAnimation(false);
        customGSYVideoPlayer.setNeedLockFull(true);

    }

    public void init(StandardGSYVideoPlayer customGSYVideoPlayer, Context context, boolean needMute, int showType) {

        if (BuildConfig.DEBUG) {
            Debuger.enable();
        } else {
            Debuger.disable();
        }

        GSYVideoType.setShowType(showType);
        IjkPlayerManager.setLogLevel(IjkMediaPlayer.IJK_LOG_SILENT);

        //exo模式和缓存，修改这两个都要该
//        PlayerFactory.setPlayManager(IjkPlayerManager.class);
        PlayerFactory.setPlayManager(Exo2PlayerManager.class);
        CacheFactory.setCacheManager(ExoPlayerCacheManager.class);

        List<VideoOptionModel> list = new ArrayList<>();
        VideoOptionModel videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "enable-accurate-seek", 1);
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "buffer_size", 1316);
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "probesize", 10240);
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "packet-buffering", 0);
        list.add(videoOptionModel);
        videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "analyzemaxduration", 100);
        list.add(videoOptionModel);
        GSYVideoManager.instance().setOptionModelList(list);
        if (needMute && customGSYVideoPlayer instanceof SoicalGSYVideoPlayer) {
            ((SoicalGSYVideoPlayer) customGSYVideoPlayer).setShowSocial(true);
        }

        GSYVideoManager.instance().setNeedMute(needMute);
        customGSYVideoPlayer.setShowPauseCover(true);
        customGSYVideoPlayer.setEnlargeImageRes(R.drawable.max_190805);
        customGSYVideoPlayer.setShrinkImageRes(R.drawable.min_190805);
        customGSYVideoPlayer.setDismissControlTime(2000);

        customGSYVideoPlayer.getFullscreenButton().setOnClickListener(view -> {
            //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
            customGSYVideoPlayer.startWindowFullscreen(context, true, true);
        });

        customGSYVideoPlayer.setIsTouchWiget(false);
        customGSYVideoPlayer.setRotateViewAuto(false);
        customGSYVideoPlayer.setLockLand(true);
        customGSYVideoPlayer.setAutoFullWithSize(true);
        customGSYVideoPlayer.setShowFullAnimation(false);
        customGSYVideoPlayer.setNeedLockFull(false);

    }

    public void init(StandardGSYVideoPlayer customGSYVideoPlayer, Context context, boolean needMute, int showType, boolean showSocial) {

        if (BuildConfig.DEBUG) {
            Debuger.enable();
        } else {
            Debuger.disable();
        }

        GSYVideoType.setShowType(showType);
        IjkPlayerManager.setLogLevel(IjkMediaPlayer.IJK_LOG_SILENT);

        //exo模式和缓存，修改这两个都要该
//        PlayerFactory.setPlayManager(IjkPlayerManager.class);
        PlayerFactory.setPlayManager(Exo2PlayerManager.class);
        CacheFactory.setCacheManager(ExoPlayerCacheManager.class);

        VideoOptionModel videoOptionModel = new VideoOptionModel(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "enable-accurate-seek", 1);
        List<VideoOptionModel> list = new ArrayList<>();
        list.add(videoOptionModel);
        GSYVideoManager.instance().setOptionModelList(list);
        if (customGSYVideoPlayer instanceof SoicalGSYVideoPlayer) {
            ((SoicalGSYVideoPlayer) customGSYVideoPlayer).setShowSocial(showSocial, needMute);
        }

//        GSYVideoManager.instance().setNeedMute(needMute);
        customGSYVideoPlayer.setShowPauseCover(true);
        customGSYVideoPlayer.setEnlargeImageRes(R.drawable.max_190805);
        customGSYVideoPlayer.setShrinkImageRes(R.drawable.min_190805);
        customGSYVideoPlayer.setDismissControlTime(2000);

        customGSYVideoPlayer.getFullscreenButton().setOnClickListener(view -> {
            //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
            customGSYVideoPlayer.startWindowFullscreen(context, true, true);
        });

        customGSYVideoPlayer.setIsTouchWiget(false);
        customGSYVideoPlayer.setRotateViewAuto(false);
        customGSYVideoPlayer.setLockLand(true);
        customGSYVideoPlayer.setAutoFullWithSize(true);
        customGSYVideoPlayer.setShowFullAnimation(false);
        customGSYVideoPlayer.setNeedLockFull(false);

    }


}
