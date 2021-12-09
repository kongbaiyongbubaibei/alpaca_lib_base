package com.alpaca.library_base.widgets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

/**
 * @文件名: GradientTextView
 * @功能描述:
 * @Date : 2021/7/21
 * @email:
 * @修改记录:
 */
public class GradientTextView extends androidx.appcompat.widget.AppCompatTextView {


    public GradientTextView(Context context) {
        super(context);
    }

    public GradientTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        TextPaint paint = getPaint();
        int[] colors = {Color.parseColor("#ff7f97"), Color.parseColor("#b776cb"), Color.parseColor("#6f6cff")};
        float[] position = {0f, 0.5f, 1.0f};
        LinearGradient mLinearGradient = new LinearGradient(0, 0, w, 0,
                colors, position, Shader.TileMode.CLAMP);
        paint.setShader(mLinearGradient);
    }

}
