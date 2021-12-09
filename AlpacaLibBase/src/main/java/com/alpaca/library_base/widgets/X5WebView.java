package com.alpaca.library_base.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.webkit.WebView;


/**
 * @文件名: X5WebView
 * @功能描述:
 * @Date : 2021/2/21
 * @email:
 * @修改记录:
 */
public class X5WebView extends WebView {

    private OnDrawListener mOnDrawListener;

    public void setOnDrawListener(OnDrawListener onDrawListener) {
        mOnDrawListener = onDrawListener;
    }


    public X5WebView(Context context) {
        super(context);

    }

    public X5WebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mOnDrawListener != null) {
            mOnDrawListener.onDrawCallBack();
        }
    }

    public interface OnDrawListener {
         void onDrawCallBack();
    }

}
