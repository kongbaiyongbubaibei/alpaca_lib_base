package com.alpaca.library_base.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;


import com.alpaca.library_base.base.BaseApplication;

import java.io.IOException;

import static android.media.AudioManager.MODE_IN_COMMUNICATION;
import static android.media.AudioManager.MODE_NORMAL;

/**
 * @文件名: PlayerManager
 * @功能描述:
 * @Date: 2018/1/8
 * @email:
 * @修改记录:
 */
public class PlayerManager {
    private String TAG = "BaseAudioActivity";

    /**
     * 外放模式
     */
    public static final int MODE_SPEAKER = 0;
    /**
     * 耳机模式
     */
    public static final int MODE_HEADSET = 1;
    /**
     * 听筒模式
     */
    public static final int MODE_EARPIECE = 2;
    private static PlayerManager playerManager;
    private AudioManager audioManager;
    private MediaPlayer mediaPlayer;
    private PlayCallback callback;
    private Context context;
    private boolean isPause = false;
    private String filePath;
    private int currentMode = MODE_SPEAKER;

    public static PlayerManager getManager() {
        if (playerManager == null) {
            synchronized (PlayerManager.class) {
                playerManager = new PlayerManager();
            }
        }
        return playerManager;
    }

    private PlayerManager() {
        this.context = BaseApplication.getInstance();
        initMediaPlayer();
        initAudioManager();
    }

    /**
     * 初始化播放器
     */
    private void initMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    /**
     * 初始化音频管理器
     */
    private void initAudioManager() {
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(MODE_IN_COMMUNICATION);
        audioManager.setSpeakerphoneOn(true);            //默认为扬声器播放
    }

    //播放速度
    public void changeplayerSpeed(float speed) { // this checks on API 23 and up6.0以上
        if (mediaPlayer==null){
            initMediaPlayer();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(speed));
            } else {
                if (mediaPlayer != null) {
                    try {
                        mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(speed));
                        mediaPlayer.pause();
                    } catch (IllegalStateException e) {
                        Log.i(TAG, "changeplayerSpeed: " + e.getMessage() + "\n" + e.getLocalizedMessage());
                    }
                }
            }
        }
    }

    /**
     * 播放回调接口
     */
    public interface PlayCallback {
        /**
         * 音乐准备完毕
         */
        void onPrepared();

        /**
         * 音乐播放完成
         */
        void onComplete();

        /**
         * 音乐停止播放
         */
        void onStop();

        /**
         * 音乐播放错误
         */
        void onError();
    }

    public void setCallback(PlayCallback callback) {
        this.callback = callback;
    }

    public PlayCallback getCallback() {
        return callback;
    }

    /**
     * 播放音乐
     *
     * @param path     音乐文件路径
     * @param callback 播放回调函数
     */
    public void play(String path, final PlayCallback callback) {
        if (mediaPlayer==null){
            initMediaPlayer();
        }
//        Log.i(TAG, "play: "+path);
        this.filePath = path;
        this.callback = callback;
        play(path);
    }

    public void setSpeed(float speed) {
        if (mediaPlayer==null){
            initMediaPlayer();
        }
        if (mediaPlayer.isPlaying()) {
            playerManager.changeplayerSpeed(speed);
        } else {
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    playerManager.changeplayerSpeed(speed);
                }
            });
        }
    }

    /**
     * 播放音乐
     *
     * @param path 音乐文件路径
     */
    public void play(String path) {
        if (mediaPlayer==null){
            initMediaPlayer();
        }
        this.filePath = path;
        if (path != null && path.length() > 0) {
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {//播放准备
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        callback.onPrepared();

                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {//播放完成
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        isPause = true;
                        callback.onComplete();
                        resetPlayMode();
                    }
                });
//            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//                @Override
//                public boolean onError(MediaPlayer mp, int what, int extra) {
//                    callback.onError();
//                    return true;
//                }
//            });
            } catch (IOException e) {
                e.printStackTrace();
                this.callback.onError();
            }
        }
    }

    /**
     * 播放音乐
     *
     * @param path 音乐文件路径
     */
    public void play(String path, float speed) {
        if (mediaPlayer==null){
            initMediaPlayer();
        }
        this.filePath = path;
        if (path != null && path.length() > 0) {
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepareAsync();
                playerManager.changeplayerSpeed(speed);
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {//播放准备
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        callback.onPrepared();

                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {//播放完成
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        isPause = true;
                        callback.onComplete();
                        resetPlayMode();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                this.callback.onError();
            }
        }
    }

    /**
     * 播放音乐
     *
     * @param path 音乐文件路径
     */
    public void play(String path, float speed, boolean beginPlaye) {
        if (mediaPlayer==null){
            initMediaPlayer();
        }
        this.filePath = path;
        if (path != null && path.length() > 0) {
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepareAsync();
                playerManager.changeplayerSpeed(speed);
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {//播放准备
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        callback.onPrepared();
                        resetPlayMode();
                        if (beginPlaye) {
                            mediaPlayer.start();
                        }
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {//播放完成
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        isPause = true;
                        callback.onComplete();
                        resetPlayMode();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                this.callback.onError();
            }
        }
    }


    /**
     * 播放音乐
     *
     * @param path 音乐文件路径
     */
    public void play(String path, float speed, int i) {
        if (mediaPlayer==null){
            initMediaPlayer();
        }
        this.filePath = path;
        if (path != null && path.length() > 0) {
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepareAsync();
                playerManager.changeplayerSpeed(speed);
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {//播放准备
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        callback.onPrepared();
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {//播放完成
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        isPause = true;
                        callback.onComplete();
                        resetPlayMode();
                    }
                });
//                mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//                @Override
//                public boolean onError(MediaPlayer mp, int what, int extra) {
//                    callback.onError();
//                    return true;
//                }
//            });
            } catch (IOException e) {
                e.printStackTrace();
                this.callback.onError();
            }
        }
    }





    public void play(String path, final boolean p) {
        if (mediaPlayer==null){
            initMediaPlayer();
        }
        this.filePath = path;
        if (path != null && path.length() > 0) {
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {//播放准备
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        callback.onPrepared();
                        resetPlayMode();
                        if (p) {
                            mediaPlayer.start();
                        }
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {//播放完成
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        isPause = true;
                        callback.onComplete();
                        resetPlayMode();
                    }
                });
//            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//                @Override
//                public boolean onError(MediaPlayer mp, int what, int extra) {
//                    callback.onError();
//                    return true;
//                }
//            });
            } catch (IOException e) {
                e.printStackTrace();
                this.callback.onError();
            }
        }
    }


    public void startPlay() {
        if (mediaPlayer==null){
            return;
        }
        mediaPlayer.start();
    }

    /**
     * 播放音乐
     *
     * @param isSingle 是否单曲循环
     */
    public void setSingleStyle(boolean isSingle) {
        if (isSingle) {
            mediaPlayer.setLooping(true);
        } else {
            mediaPlayer.setLooping(false);
        }
    }

    public boolean isPause() {
        return isPause;
    }

    public void pause() {
        if (mediaPlayer==null){
            return;
        }
        if (isPlaying()) {
            isPause = true;
            mediaPlayer.pause();
        }
    }

    public void resume() {
        if (mediaPlayer==null){
            return;
        }
            if (isPause) {
                isPause = false;
                mediaPlayer.start();
            }
    }

    /**
     * 播放指定时间     毫秒
     */
    public void playToOffset(int offset) {
        if (mediaPlayer==null){
            return;
        }
        mediaPlayer.seekTo(offset);
        mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mp) {
                // TODO: 2018/1/8 跳转到指定时间 成功会调用此方法 失败是否要建立一个计时器失败做操作
            }
        });
    }

    public int getCurrentTime() {
        if (mediaPlayer==null){
            return 0;
        }
        return mediaPlayer.getCurrentPosition();
    }


    public int getTimeLong() {
        if (mediaPlayer==null){
            return 0;
        }
        return mediaPlayer.getDuration();
    }

    /**
     * 获取当前播放模式
     *
     * @return
     */
    public int getCurrentMode() {
        return currentMode;
    }

    /**
     * 切换到听筒模式
     */
    public void changeToEarpieceMode() {
        currentMode = MODE_EARPIECE;
        audioManager.setSpeakerphoneOn(false);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
//                    audioManager.getStreamMaxVolume(MODE_IN_COMMUNICATION), AudioManager.FX_KEY_CLICK);
//        } else {
//            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
//                    audioManager.getStreamMaxVolume(AudioManager.MODE_IN_CALL), AudioManager.FX_KEY_CLICK);
//        }
    }

    /**
     * 切换到耳机模式
     */
    public void changeToHeadsetMode(boolean booth) {
        currentMode = MODE_HEADSET;
//        currentMode = MODE_HEADSET;
        audioManager.setMode(MODE_IN_COMMUNICATION);
        audioManager.stopBluetoothSco();
        if (booth) {
            audioManager.setBluetoothScoOn(true);
        } else {
            audioManager.setBluetoothScoOn(false);
        }
        audioManager.setSpeakerphoneOn(false);
    }

    /**
     * 切换到外放模式
     */
    public void changeToSpeakerMode() {
        currentMode = MODE_NORMAL;
        audioManager.setSpeakerphoneOn(true);
        audioManager.stopBluetoothSco();
        audioManager.setBluetoothScoOn(false);
    }

    /**
     * 切换到蓝牙音箱
     */
    public void changeToHeadset() {
        audioManager.setMode(AudioManager.MODE_NORMAL);
        audioManager.stopBluetoothSco();
        audioManager.setSpeakerphoneOn(false);
        audioManager.setBluetoothA2dpOn(true);
        audioManager.setStreamSolo(AudioManager.STREAM_MUSIC, true);
        audioManager.setRouting(AudioManager.MODE_NORMAL, AudioManager.ROUTE_BLUETOOTH_A2DP, AudioManager.ROUTE_BLUETOOTH);
    }

    public void resetPlayMode() {
        if (audioManager.isWiredHeadsetOn()) {//检测是否连接耳机线
            changeToHeadsetMode(false);
        } else if (audioManager.isBluetoothA2dpOn()) {
            changeToHeadset();
        } else {
            changeToSpeakerMode();
        }
    }

    /**
     * 调大音量
     */
    public void raiseVolume() {
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (currentVolume < audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)) {
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_RAISE, AudioManager.FX_FOCUS_NAVIGATION_UP);
        }
    }

    /**
     * 调小音量
     */
    public void lowerVolume() {
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (currentVolume > 0) {
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_LOWER, AudioManager.FX_FOCUS_NAVIGATION_UP);
        }
    }

    /**
     * 停止播放
     */
    public void stop() {
        if (mediaPlayer==null){
            return ;
        }
        if (isPlaying()) {
            try {
                mediaPlayer.stop();
                callback.onStop();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    public void clear() {
        if (mediaPlayer==null){
            return ;
        }
        if (isPlaying()) {
            mediaPlayer.stop();
        }
    }

    /**
     * 是否正在播放
     *
     * @return 正在播放返回true, 否则返回false
     */
    public boolean isPlaying() {
        if (mediaPlayer==null){
            return false;
        }
        try {
            return mediaPlayer != null && mediaPlayer.isPlaying();
        } catch (Exception e) {
            return false;
        }

    }

    public void release() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                try {
                    mediaPlayer.stop();
                } catch (Exception e) {
                }
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
