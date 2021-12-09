package com.alpaca.library_base.widgets;

import android.content.Context;
import android.view.View;

import com.alpaca.library_base.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import androidx.annotation.NonNull;

/**
 * class desc :
 * <p>
 * 弹出直接展开的BottomSheetDialog
 * 去掉了背景色，折叠效果
 *
 * @author : zzh
 * @date : 2020/1/2
 */
public class ExpandBottomSheetDialog extends BottomSheetDialog {

    public ExpandBottomSheetDialog(@NonNull Context context) {
        super(context);
    }

    public ExpandBottomSheetDialog(@NonNull Context context, int theme) {
        super(context, theme);
    }

    protected ExpandBottomSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        View bottomSheetView = getDelegate().findViewById(R.id.design_bottom_sheet);
        //去掉背景色
        bottomSheetView.setBackgroundColor(
                getContext().getResources().getColor(android.R.color.transparent));

        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView);
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            //去掉折叠的高度
            bottomSheetBehavior.setPeekHeight(0);
            //状态改变但是背景色不消失问题
            bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN
                            || newState == BottomSheetBehavior.STATE_COLLAPSED) {
                        dismiss();
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                }
            });
        }
    }
}
