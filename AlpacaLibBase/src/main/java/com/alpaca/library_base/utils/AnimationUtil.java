package com.alpaca.library_base.utils;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

/**
 * @文件名: AnimationUtil
 * @功能描述:
 * @Date : 2019/5/6
 * @email:
 * @修改记录:
 */
public class AnimationUtil {
    private static final String TAG = AnimationUtil.class.getSimpleName();

    /**
     * 从控件所在位置移动到控件的底部
     *
     * @return
     */
    public static TranslateAnimation moveToViewBottom() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        mHiddenAction.setDuration(300);
        return mHiddenAction;
    }


    /**
     * 从控件所在位置移动到控件的右边
     *
     * @return
     */
    public static AnimationSet moveToViewRightIn() {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.8f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(700);
        mHiddenAction.setRepeatMode(Animation.RESTART);
        mHiddenAction.setFillAfter(true);
        mHiddenAction.setRepeatCount(0);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.3f);
        alphaAnimation.setDuration(500);
        animationSet.addAnimation(mHiddenAction);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setFillAfter(true);
        return animationSet;
    }
    /**
     * 从控件所在位置移动到控件的左边
     *
     * @return
     */
    public static AnimationSet moveToViewLeftIn() {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -0.7f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(600);
        mHiddenAction.setRepeatMode(Animation.RESTART);
        mHiddenAction.setFillAfter(true);
        mHiddenAction.setRepeatCount(0);
//        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.3f);
//        alphaAnimation.setDuration(500);
        animationSet.addAnimation(mHiddenAction);
//        animationSet.addAnimation(alphaAnimation);
        animationSet.setFillAfter(true);
        return animationSet;
    }
    /**
     * 从控件所在位置移动到控件的左边
     *
     * @return
     */
    public static AnimationSet moveToViewLeftOut() {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -0.7f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(500);
        mHiddenAction.setRepeatMode(Animation.RESTART);
        mHiddenAction.setFillAfter(true);
        mHiddenAction.setRepeatCount(0);
//        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.3f);
//        alphaAnimation.setDuration(500);
        animationSet.addAnimation(mHiddenAction);
//        animationSet.addAnimation(alphaAnimation);
        animationSet.setFillAfter(true);
        return animationSet;
    }

    /**
     * 从控件所在位置移动到控件的右边
     *
     * @return
     */
    public static AnimationSet moveToViewRightOut() {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.8f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(700);
        mHiddenAction.setFillAfter(true);
        mHiddenAction.setRepeatMode(Animation.RESTART);
        mHiddenAction.setRepeatCount(0);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1f);
        alphaAnimation.setDuration(500);
        animationSet.addAnimation(mHiddenAction);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setFillAfter(true);
        return animationSet;
    }


    /**
     * 从控件所在位置移动到控件的右边
     *
     * @return
     */
    public static TranslateAnimation moveToViewRight() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -0.5f,
                Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(1000);
        mHiddenAction.setRepeatMode(Animation.RESTART);
        mHiddenAction.setRepeatCount(Animation.INFINITE);
        return mHiddenAction;
    }


    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return
     */
    public static TranslateAnimation moveToViewLocation() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(100);
        return mHiddenAction;
    }

    public static AlphaAnimation alphaToViewShow() {
        AlphaAnimation mHiddenAction = new AlphaAnimation(0f, 1.0f);
        mHiddenAction.setDuration(200);
        return mHiddenAction;
    }

    public static AlphaAnimation alphaToViewHide() {
        AlphaAnimation mHiddenAction = new AlphaAnimation(1.0f, 0f);
        mHiddenAction.setDuration(200);
        return mHiddenAction;
    }

}
