package com.alpaca.library_base.utils;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

/**
 * @文件名: TimerUtil
 * @功能描述:
 * @Date:  2018/1/12
 * @email:
 * @修改记录:
 */
public class TimerUtil {
    private final int MSG_TYPE_START = 0;
    private final int MSG_TYPE_PAUSE = 1;
    private final int MSG_TYPE_STOP = 2;
    private int passedTime ;
    private TimeCallBack timeCallBack;

    public TimerUtil() {
        passedTime = 0;
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_TYPE_START:
                    handler.postDelayed(recordTime,1000);
                    break;
                case MSG_TYPE_PAUSE:
                    handler.removeMessages(MSG_TYPE_START);
                    break;
                case MSG_TYPE_STOP:
                    passedTime = 0;
                    if (timeCallBack != null){
                        timeCallBack.onTime(passedTime);
                    }
                    handler.removeCallbacks(recordTime);
                    handler.removeMessages(MSG_TYPE_START);
                    break;
            }
        }
    };

    private Runnable recordTime = new Runnable() {
        @Override
        public void run() {
            passedTime++;
            if (timeCallBack != null){
                timeCallBack.onTime(passedTime);
            }
            handler.sendEmptyMessage(MSG_TYPE_START);
        }
    };

    public void startCount(){
        handler.sendEmptyMessage(MSG_TYPE_START);
    }
    public void pauseCount(){
        handler.sendEmptyMessage(MSG_TYPE_PAUSE);
    }
    public void stopCount(){
        handler.sendEmptyMessage(MSG_TYPE_STOP);
//        handler.removeCallbacksAndMessages(null);
    }
    public interface TimeCallBack{
        void onTime(int passedTime);
    }
    public void setTimeCallBack(TimeCallBack timeCallBack){
        this.timeCallBack = timeCallBack;
    }

    public void cancelCount(){
        handler.removeCallbacksAndMessages(null);
    }
}