package com.alpaca.library_base.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

public class MediaPlayerUtils {

    private MediaPlayer mPlayer;

    public void playFromRawFile(Context mContext, int soundResouceId) {
        //1.获取模式
        AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        final int ringerMode = am.getRingerMode();
        //2.普通模式可以呼叫普通模式： AudioManager.RINGER_MODE_NORMAL 静音模式：AudioManager.RINGER_MODE_VIBRATE 震动模式：AudioManager.RINGER_MODE_SILENT
//        if (ringerMode == AudioManager.RINGER_MODE_NORMAL) {
        try {
            if (mPlayer == null) {
                mPlayer = new MediaPlayer();
            }
            AssetFileDescriptor file = mContext.getResources().openRawResourceFd(soundResouceId);
            try {
                mPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
                file.close();
                if (!mPlayer.isPlaying()) {
                    mPlayer.prepare();
                    mPlayer.start();
//                        mPlayer.setLooping(true);//循环播放
                }
            } catch (IOException e) {
                mPlayer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
//        }

    }


    /**
     * 循环播放
     *
     * @param mContext
     * @param soundResouceId
     */
    public void startVoice(Context mContext, int soundResouceId) {
        if (mPlayer != null && mPlayer.isPlaying()) {
            return;
        }
        mPlayer = MediaPlayer.create(mContext, soundResouceId);
        mPlayer.start();
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (mPlayer == null) {
                    return;
                }
                mPlayer.start();
                mPlayer.setLooping(true);
            }
        });
    }


    /**
     * 结束播放来电和呼出铃声
     */
    public void stopPlayFromRawFile() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.stop();
            mPlayer.release();
        }
        mPlayer = null;
    }
}
