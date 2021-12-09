package com.alpaca.library_base.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.alpaca.library_base.utils.LogUtil;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * author : Shirley
 * date : 2020/6/3 10:48
 * description :
 */
public class CustomLinearLayoutManager extends LinearLayoutManager {
    private static final String TAG = CustomLinearLayoutManager.class.getName();
    private boolean isScrollEnabled = true;

    public CustomLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomLinearLayoutManager(Context context) {
        super(context);
    }
    public CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }
    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }
    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            LogUtil.e(TAG,"RecyclerView # " + e.getMessage());
        }
    }
}
