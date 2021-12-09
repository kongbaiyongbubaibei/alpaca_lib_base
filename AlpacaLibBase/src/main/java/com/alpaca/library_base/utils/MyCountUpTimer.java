package com.alpaca.library_base.utils;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/**
 * @文件名: MyCountDownTimer
 * @功能描述:
 * @Date:  2018/1/10
 * @email:
 * @修改记录:
 */
public abstract class MyCountUpTimer {
    /**
     * Millis since epoch when alarm should stop.
     */
    private final long mMillisInFuture;

    /**
     * The interval in millis that the user receives callbacks
     */
    private final long mCountUpInterval;

    private long mStopTimeInFuture;

    /**
     * boolean representing if the timer was cancelled
     */
    private boolean mCancelled = false;

    private boolean isPause = false;

    private long mPauseTimeInFuture;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public MyCountUpTimer(long millisInFuture, long countDownInterval) {
        mMillisInFuture = millisInFuture;
        mCountUpInterval = countDownInterval;
    }

    /**
     * Cancel the countdown.
     */
    public synchronized final void cancel() {
        mCancelled = true;
        mPauseTimeInFuture = 0;
        mHandler.removeMessages(MSG);
    }

    /**
     * Start the countdown.
     */
    public synchronized final MyCountUpTimer start() {
        mCancelled = false;
        if (mMillisInFuture <= 0) {
            onFinish();
            return this;
        }
        mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture;
        mHandler.sendMessage(mHandler.obtainMessage(MSG));
        return this;
    }

    public synchronized final void pause(){
        isPause = true;
    }


    public synchronized final void resume(){
        isPause = false;
    }

    /**
     * Callback fired on regular interval.
     *
     * @param millisUntilFinished The amount of time until finished.
     */
    public abstract void onTick(long millisUntilFinished);

    /**
     * Callback fired when the time is up.
     */
    public abstract void onFinish();


    private static final int MSG = 1;


    // handles counting down
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            synchronized (MyCountUpTimer.this) {
                if (mCancelled) {
                    return;
                }

                if (isPause) {
                    mPauseTimeInFuture = SystemClock.elapsedRealtime();
                    return;
                }
                final long millisLeft = mStopTimeInFuture - (SystemClock.elapsedRealtime() - mPauseTimeInFuture);

                if (millisLeft <= 0) {
                    onFinish();
                } else if (millisLeft < mCountUpInterval) {
                    // no tick, just delay until done
                    sendMessageDelayed(obtainMessage(MSG), millisLeft);
                } else {
                    long lastTickStart = SystemClock.elapsedRealtime();
                    onTick(mMillisInFuture - millisLeft);
                    // take into account user's onTick taking time to execute
                    long delay = lastTickStart + mCountUpInterval - SystemClock.elapsedRealtime();

                    // special case: user's onTick took more than interval to
                    // complete, skip to next interval
                    while (delay < 0) delay += mCountUpInterval;

                    sendMessageDelayed(obtainMessage(MSG), delay);
                }
            }
        }
    };
}

