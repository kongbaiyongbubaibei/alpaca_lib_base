package com.alpaca.library_base.utils;

import android.content.Context;

import androidx.recyclerview.widget.LinearSmoothScroller;

/**
 * @文件名: TopLinearSmoothScroller
 * @功能描述:
 * @Date : 2020/11/4
 * @email:
 * @修改记录:
 */
public class TopLinearSmoothScroller extends LinearSmoothScroller {
    public TopLinearSmoothScroller(Context context) {
        super(context);
    }

    @Override
    public int getVerticalSnapPreference() {
        return SNAP_TO_START;
    }
}
